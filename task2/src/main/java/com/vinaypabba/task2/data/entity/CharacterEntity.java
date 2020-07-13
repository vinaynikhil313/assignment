package com.vinaypabba.task2.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "characters")
@Table(name = "characters")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "parent_id")
    private int parentId;

    private String name;

    private String color;

    public CharacterEntity(int parentId, String name, String color) {
        this.parentId = parentId;
        this.name = name;
        this.color = color;
    }
}
