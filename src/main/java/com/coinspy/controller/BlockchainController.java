//package com.coinspy.controller;
//
//import com.coinspy.dto.ExplorerPairCreated;
//import com.coinspy.dto.ExplorerTransactionInfo;
//import com.coinspy.entity.BlockEntity;
//import com.coinspy.entity.Blockchain;
//import com.coinspy.entity.UserEntity;
//import com.coinspy.repository.BlockRepository;
//import com.coinspy.repository.UserRepository;
//import com.coinspy.service.PolygonscanService;
//import com.coinspy.useful.Parser;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping(path = "/api/v1/blockchain")
//public class BlockchainController {
//
//    public final PolygonscanService polygonscanService;
//    public final BlockRepository blockRepository;
//    public final UserRepository userRepository;
//    public final String correctAddress;
//
//    @Autowired
//    public BlockchainController(PolygonscanService polygonscanService,
//                                BlockRepository blockRepository,
//                                UserRepository userRepository,
//                                @Value("${polygon.contract.address}") String correctAddress) {
//        this.polygonscanService = polygonscanService;
//        this.blockRepository = blockRepository;
//        this.userRepository = userRepository;
//        this.correctAddress = correctAddress;
//    }
//
//    @GetMapping(path = "/{transactionHash}")
//    public ResponseEntity<UserEntity> saveBillByTransactionHash(@PathVariable("transactionHash") String transactionHash)  {
//        ExplorerTransactionInfo transactionInfo = polygonscanService.getTransactionInfo(transactionHash);
//
//        if (!transactionInfo.getResult().getTo().equalsIgnoreCase(correctAddress))
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//
//        List<String> values = decodeInput(transactionInfo.getResult().getInput());
//
//        String telegramUserId = values.get(0);
//        int days = Integer.parseInt(values.get(2));
//        List<Long> groups = values.subList(4, values.size()).stream()
//                .map(Long::parseLong)
//                .collect(Collectors.toList());
//
//        Optional<UserEntity> existingUser = userRepository.findByTelegramUserId(telegramUserId);
//
//        if (existingUser.isPresent()){
//            UserEntity userEntity = existingUser.get();
//
//            Map<Long, LocalDateTime> groupsMap = userEntity.getGroups();
//
//            groupsMap.forEach((key, value) -> {
//                if (groups.contains(key)) groupsMap.replace(key, value.plusDays(days));
//                else groupsMap.put(key, LocalDateTime.now().plusDays(days));
//            });
//
//            userEntity.setGroups(groupsMap);
//
//            return new ResponseEntity<>(userRepository.save(userEntity), HttpStatus.OK);
//        }
//
//        Map<Long, LocalDateTime> groupMap =  groups
//                .stream()
//                .collect(Collectors.toMap(aLong -> aLong, aLong -> LocalDateTime.now().plusDays(days)));
//
//        UserEntity userEntity = new UserEntity(telegramUserId, groupMap);
//
//        return new ResponseEntity<>(userRepository.save(userEntity), HttpStatus.OK);
//    }
//
//    private List<String> decodeInput(String input){
//        return Parser.parseInputFromExplorer(input, true)
//                .stream()
//                .map(Parser::hexToDec)
//                .collect(Collectors.toList());
//    }
//
//    private String createdTopics(Blockchain blockchain, String address, String topic){
//        Optional<BlockEntity> byBlockchain = blockRepository.findByBlockchain(blockchain);
//
//        ExplorerPairCreated lastTopics = polygonscanService.getLastTopics(byBlockchain.get().getBlockNumber(), address, topic);
//
//        lastTopics.getResult();
//    }
//
//
//    //1.
//    //2.
//    //3.
//}
////{"jsonrpc":"2.0","id":1,"result":{"blockHash":"0x84f29bd68487d09e150a11a96d236e4d06c35e3dab8fd3dd77d1383c2906282c","blockNumber":"0x1119ee3","from":"0x3800f659f96d7c4086fca5e945c41985b8cc206a","gas":"0x2fae9","gasPrice":"0xd7348eb0","hash":"0x04df988a7b3880ee47648db32ddaaa20bc0342b33c5abb5e61edf584fddee80a","input":"0xf493a72000000000000000000000000000000000000000000000000000000000000030390000000000000000000000000000000000000000000000000000000000000060000000000000000000000000000000000000000000000000000000000000001f0000000000000000000000000000000000000000000000000000000000000003000000000000000000000000000000000000000000000000000000000000007b00000000000000000000000000000000000000000000000000000000000001c80000000000000000000000000000000000000000000000000000000000000315","nonce":"0x5","to":"0x186245d26892c42e54e11a2546acb63eb2e4eb63","transactionIndex":"0x51","value":"0xb2d05e00","type":"0x0","v":"0x135","r":"0x49677c39396085d75c9723d1053c97ed94b76b90580c5594d39705d9ff5461c0","s":"0x526dfb4a4a2a6fcba386656ceb268b0c2b92b860614c6058aa79a94b1b89726"}}
//
//
