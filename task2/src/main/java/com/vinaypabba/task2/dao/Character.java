package com.vinaypabba.task2.dao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vinaypabba.task2.data.entity.CharacterEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Character {

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Sub Classes")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Character> subClasses = null;

    public void addSubclass(Character c) {
        if (Objects.isNull(this.subClasses)) {
            this.subClasses = new ArrayList<>();
        }
        this.subClasses.add(c);
    }

    public Character(String name) {
        this.name = name;
    }

    public static Character fromCharacterEntity(CharacterEntity characterEntity) {
        return new Character(characterEntity.getName());
    }

}
