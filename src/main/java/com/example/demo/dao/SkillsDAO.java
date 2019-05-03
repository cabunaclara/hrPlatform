package com.example.demo.dao;

import com.example.demo.model.Skill;

import java.util.ArrayList;
import java.util.List;

public class SkillsDAO {

    private List<SkillDAO> skillList;

    public List<SkillDAO> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<SkillDAO> skillList) {
        this.skillList = skillList;
    }

    public SkillsDAO() {

    }

    public SkillsDAO(List<SkillDAO> skillList) {
        this.skillList = skillList;
    }
}
