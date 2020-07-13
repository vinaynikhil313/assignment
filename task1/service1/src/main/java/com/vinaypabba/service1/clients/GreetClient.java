package com.vinaypabba.service1.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("greet-service")
public interface GreetClient {

    @GetMapping(value = "/greet")
    ResponseEntity<String> getMessage();

}
