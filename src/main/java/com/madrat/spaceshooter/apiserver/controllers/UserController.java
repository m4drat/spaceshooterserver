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
        // initial check for structure
        if (user.isValid()) {
            // check for fields
            String checkResult = user.checkFields();
            if (checkResult.equals(ErrorCode.OK)) {
                try {
                    // User already exists
                    if (userRepository.existsByclientuuid(user.getClientuuid()) && userRepository.existsByserveruuid(user.getServeruuid())) {
                        System.out.println("[+] Updating info for user Entity: " + user.toString());
                        userRepository.setScoreByClientUUIDbyServerUUID(user.getScore(), user.getServeruuid(), user.getClientuuid());
                        return ResponseEntity.ok(new SimpleResponse("updated", ErrorCode.OK));
                    } else {
                        System.out.println("[+] Creating user Entity: " + user.toString());
                        userRepository.save(user);
                        return ResponseEntity.ok(new SimpleResponse("created", ErrorCode.OK));
                    }
                } catch (javax.persistence.RollbackException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleResponse("duplicate entry", ErrorCode.ERR_DUP));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleResponse("unexpected error", ErrorCode.ERR_UNEXPECTED));
                }
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleResponse("incorrect format", checkResult));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SimpleResponse("Cannot parse your request (check field names)", ErrorCode.ERR_PARSE));
    }
}
