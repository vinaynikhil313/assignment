package com.vinaypabba.task2.data;

import com.vinaypabba.task2.data.entity.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepo extends JpaRepository<CharacterEntity, Integer> {
}
