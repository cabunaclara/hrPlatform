package com.example.demo.repository;

import com.example.demo.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository <Skill, Long>{

    @Query(value = "SELECT * FROM skills s WHERE s.name = ?1 and s.active=true", nativeQuery = true)
    Skill findSkillByName(String name);
}
