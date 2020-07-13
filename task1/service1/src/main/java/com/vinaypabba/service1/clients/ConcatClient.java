package com.vinaypabba.service1.clients;

import com.vinaypabba.service1.UserName;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient("concatenate-service")
public interface ConcatClient {

    @PostMapping(value = "/concatenate")
    ResponseEntity<String> concatenate(UserName userName);

}
