package com.madrat.spaceshooter.apiserver.controllers;

import com.madrat.spaceshooter.apiserver.database.UserRepository;
import com.madrat.spaceshooter.apiserver.resourcereprs.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/scoreboard", method = RequestMethod.GET)
    public List<User> scoreboard(@RequestParam(value="count", defaultValue="10") int count) {
        Pageable limit = PageRequest.of(0, count);
        return userRepository.findAllByOrderByScoreDesc(limit);
    }

    @PostMapping("/updatescore")
    public ResponseEntity updateScore(@RequestBody User user) {
        if (user.isValid()) {
            String checkResult = user.checkFields();
            if (checkResult.equals(ErrorCode.OK)) {
                try {
                    userRepository.save(user);
                    return ResponseEntity.ok(new SimpleResponse("answer", ErrorCode.OK));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleResponse("duplicate entry", ErrorCode.DUP));
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleResponse("incorrect format", checkResult));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleResponse("Cannot parse your request (check field names)", ErrorCode.ERR_PARSE));
    }
}
