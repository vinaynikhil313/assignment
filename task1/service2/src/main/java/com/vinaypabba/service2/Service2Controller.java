package com.vinaypabba.service2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service2Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Service2Controller.class);

    @GetMapping(path = "/greet", produces = {"application/text"})
    public ResponseEntity<String> getMessage() {
        LOGGER.info("Received a greeting request.");
        return ResponseEntity.ok("Hello");
    }

}
