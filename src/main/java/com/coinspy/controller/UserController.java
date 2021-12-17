package com.coinspy.controller;

import com.coinspy.dto.User;
import com.coinspy.entity.UserEntity;
import com.coinspy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(path = "/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") String id){
        Optional<UserEntity> userData = userRepository.findById(id);

        return userData
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/user/{telegramUserId}")
    public ResponseEntity<UserEntity> getUsersByTelegramUserId(@PathVariable("telegramUserId") String telegramUserId) {
        Optional<UserEntity> userData = userRepository.findByTelegramUserId(telegramUserId);

        return userData
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(path = "/user")
    public ResponseEntity<List<UserEntity>> getUsers() {
        try {
            List<UserEntity> users = new ArrayList<>(userRepository.findAll());

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/user")
    public ResponseEntity<UserEntity> addUser(@RequestBody User user){
        try {
            UserEntity userTemp = userRepository.insert(new UserEntity(user.getTelegramUserId(), user.getChannels()));
            return new ResponseEntity<>(userTemp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new UserEntity(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/user")
    public ResponseEntity<UserEntity> updateUserChannels(@RequestBody User user) {
        Optional<UserEntity> userData = userRepository.findByTelegramUserId(user.getTelegramUserId());

        if (userData.isPresent()) {
            var userEntity = userData.get();
            userEntity.setGroups(user.getChannels());
            return new ResponseEntity<>(userRepository.save(userEntity), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(path = "/user/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable("id") String id, @RequestBody UserEntity user) {
        Optional<UserEntity> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            var userTemp = userData.get();
            userTemp.setTelegramUserId(user.getTelegramUserId());
            userTemp.setGroups(user.getGroups());
            return new ResponseEntity<>(userRepository.save(userTemp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/user/{id}")
    public ResponseEntity<HttpStatus> removeUserById(@PathVariable("id") String id){
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
