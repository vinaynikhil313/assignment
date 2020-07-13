package com.vinaypabba.service1;

import com.netflix.client.ClientException;
import com.vinaypabba.service1.clients.ConcatClient;
import com.vinaypabba.service1.clients.GreetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Service1Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Service1Controller.class);

    private final GreetClient greetClient;

    private final ConcatClient concatClient;

    public Service1Controller(GreetClient greetClient, ConcatClient concatClient) {
        this.greetClient = greetClient;
        this.concatClient = concatClient;
    }

    @GetMapping(path = "/health", produces = {"application/text"})
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("Up");
    }

    @PostMapping(value = "/greetUser", consumes = {"application/json"}, produces = {"application/text"})
    public ResponseEntity<String> sayHello(@RequestBody @Valid @NotNull(message = "Name object is required") UserName userName) {
        LOGGER.info("Preparing greeting message.");
        String greeting = greetClient.getMessage().getBody();
        LOGGER.info("Fetched greeting - {}", greeting);
        String name = concatClient.concatenate(userName).getBody();
        LOGGER.info("Fetched concatenated name - {}", name);
        return ResponseEntity.ok(greeting + " " + name);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ClientException.class)
    public Map<String, String> handleClientException(
            ClientException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Client - " + ex.getErrorMessage().split(":")[1].trim() + " not registered with Eureka server");
        return errors;
    }

}
