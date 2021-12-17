package com.coinspy.controller;

import com.coinspy.entity.CodeEntity;
import com.coinspy.entity.Type;
import com.coinspy.repository.CodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/code")
public class CodeController {
    private final CodeRepository codeRepository;

    @Autowired
    public CodeController(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CodeEntity> getCodeById(@PathVariable("id") String id){
        Optional<CodeEntity> codeData = codeRepository.findById(id);

        return codeData
                .map(code -> new ResponseEntity<>(code, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<CodeEntity>> getAllCodes(@RequestParam(required = false) Type type) {
        try {
            List<CodeEntity> codes = new ArrayList<>();

            if (type == null)
                codes.addAll(codeRepository.findAll());
            else
                codes.addAll(codeRepository.findByType(type));

            if (codes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(codes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<CodeEntity> addCode(@RequestBody CodeEntity code){
        if (codeRepository.findAll().stream()
                .map(CodeEntity::getByteCode)
                .anyMatch(e -> e.equals(code.getByteCode())))
            return new ResponseEntity<>(new CodeEntity(), HttpStatus.CONFLICT);

        try {
            CodeEntity codeTemp = codeRepository.insert(code);
            return new ResponseEntity<>(codeTemp, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CodeEntity(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<CodeEntity> updateTutorial(@PathVariable("id") String id, @RequestBody CodeEntity code) {
        Optional<CodeEntity> codeData = codeRepository.findById(id);

        if (codeData.isPresent()) {
            var codeTemp = codeData.get();
            codeTemp.setByteCode(code.getByteCode());
            codeTemp.setType(code.getType());
            return new ResponseEntity<>(codeRepository.save(codeTemp), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> removeCodeById(@PathVariable("id") String id){
        try {
            codeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
