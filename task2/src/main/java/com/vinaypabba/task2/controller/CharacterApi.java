package com.vinaypabba.task2.controller;

import com.vinaypabba.task2.dao.Character;
import com.vinaypabba.task2.dao.CharacterRequest;
import com.vinaypabba.task2.data.entity.CharacterEntity;
import com.vinaypabba.task2.service.CharacterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@RestController
@RequestMapping("/api/character")
public class CharacterApi {

    private final CharacterService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterApi.class);

    public CharacterApi(CharacterService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}", produces = {"application/json"})
    public ResponseEntity<CharacterEntity> getById(@PathVariable @Valid @PositiveOrZero(message = "Please enter a valid character id (number)") Integer id) {
        LOGGER.info("Received id - {}", id);
        return ResponseEntity.ok(service.getCharacterById(id));
    }

    @GetMapping(path = {"", "/"}, produces = {"application/json"})
    public ResponseEntity<List<Character>> getAll() {
        LOGGER.info("Fetching all the character");
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping(path = "/add", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<CharacterEntity> addCharacter(@RequestBody CharacterRequest request) {
        LOGGER.info("Adding new character - {}", request.getName());
        return ResponseEntity.ok(service.save(request));
    }

}
