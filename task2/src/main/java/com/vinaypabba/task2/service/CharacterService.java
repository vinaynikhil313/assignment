package com.vinaypabba.task2.service;

import com.vinaypabba.task2.aspect.LogMethodParam;
import com.vinaypabba.task2.aspect.LogMethodParam.*;
import com.vinaypabba.task2.dao.Character;
import com.vinaypabba.task2.dao.CharacterRequest;
import com.vinaypabba.task2.data.CharacterRepo;
import com.vinaypabba.task2.data.entity.CharacterEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CharacterService {

    private final CharacterRepo repo;

    public CharacterService(CharacterRepo repo) {
        this.repo = repo;
    }

    @LogMethodParam(logLevel = LoggerLevel.DEBUG)
    public CharacterEntity getCharacterById(int id) {
        Optional<CharacterEntity> ch = repo.findById(id);
        return ch.orElse(null);
    }

    @LogMethodParam(logLevel = LoggerLevel.INFO)
    public List<Character> getAll() {
        return fetchRelationships(repo.findAll());
    }

    @LogMethodParam(logLevel = LoggerLevel.INFO)
    public CharacterEntity save(CharacterRequest request) {
        return repo.save(request.toCharacterEntity());
    }

    private List<Character> fetchRelationships(List<CharacterEntity> characterEntities) {
        Map<Integer, Character> idObjectMapping = characterEntities.stream().collect(Collectors.toMap(CharacterEntity::getId, Character::fromCharacterEntity));
        List<Character> roots = new ArrayList<>();
        for (CharacterEntity entity : characterEntities) {
            if (!idObjectMapping.containsKey(entity.getParentId())) {
                roots.add(idObjectMapping.get(entity.getId()));
            } else {
                idObjectMapping.get(entity.getParentId()).addSubclass(idObjectMapping.get(entity.getId()));
            }
        }
        return roots;
    }

}
