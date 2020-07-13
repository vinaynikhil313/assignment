package com.vinaypabba.task2.dao;

import com.vinaypabba.task2.data.entity.CharacterEntity;
import lombok.Data;

@Data
public class CharacterRequest {

    private int parentId;

    private String name;

    private String color;

    public CharacterEntity toCharacterEntity() {
        return new CharacterEntity(this.parentId, this.name, this.color);
    }

}
