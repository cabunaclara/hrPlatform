package com.example.demo.dao;

import com.example.demo.model.Skill;

public class SkillDAO {

    private String  name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SkillDAO() {
    }

    public SkillDAO(String name) {
        this.name = name;
    }

    public SkillDAO(Skill skill){
        this.name=skill.getName();
    }
}
