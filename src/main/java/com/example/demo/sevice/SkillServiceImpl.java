package com.example.demo.sevice;

import com.example.demo.dao.SkillDAO;
import com.example.demo.model.Skill;
import com.example.demo.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.swing.text.StyledEditorKit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Skill findById (Long id){
        try{
            return skillRepository.findById(id).get();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public Skill save (Skill skill){
        Skill saved = skillRepository.save(skill);
        return saved;
    }

    @Override
    public List<Skill> findAll(){
        return skillRepository.findAll();
    }

    @Override
    public Skill create(SkillDAO skillDAO){
        Skill skill=new Skill(skillDAO);
        return skill;
    }

    @Override
    public void remove(Skill skill) {
        skill.setActive(false);
    }

    @Override
    public Skill update (Skill skill, SkillDAO skillDAO) {
        String name= skillDAO.getName();
        skill.setName(name);
        return skill;
    }

    @Override
    public Skill findByName(String name){
        return skillRepository.findSkillByName(name);
    }
}
