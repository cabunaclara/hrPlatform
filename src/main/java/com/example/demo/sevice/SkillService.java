package com.example.demo.sevice;

import com.example.demo.dao.SkillDAO;
import com.example.demo.model.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SkillService {

    Skill findById(Long id);

    Skill save(Skill skill);

    List<Skill> findAll();

    Skill create (SkillDAO skillDAO);

    void remove (Skill skill);

    Skill update (Skill skill, SkillDAO skillDAO);

    Skill findByName(String name);
}
