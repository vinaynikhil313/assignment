package com.vinaypabba.service3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service3Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Service3Controller.class);

    @PostMapping(value = "/concatenate", consumes = {"application/json"}, produces = {"application/text"})
    public ResponseEntity<String> concatenate(@RequestBody UserName userName) {
        LOGGER.info("Concatenating username fields - {}", userName);
        return ResponseEntity.ok(userName.getName() + " " + userName.getSurname());
    }

}
