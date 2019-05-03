package com.example.demo.controller;

import com.example.demo.dao.SkillDAO;
import com.example.demo.dao.SkillsDAO;
import com.example.demo.model.JobCandidate;
import com.example.demo.model.Skill;
import com.example.demo.sevice.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.loader.plan.spi.Return;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping("/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(value = "/all",  produces = "application/json")
    public ResponseEntity<SkillsDAO> getSkills() {
        List<Skill> skills=skillService.findAll();
        SkillsDAO skillsDAO=new SkillsDAO();
        ArrayList<SkillDAO> helperList= new ArrayList<>();
        for (Skill s: skills) {
            SkillDAO skillDAO= new SkillDAO();
            skillDAO.setName(s.getName());
            helperList.add(skillDAO);
        }
        skillsDAO.setSkillList(helperList);
        return new ResponseEntity<>(skillsDAO, HttpStatus.OK);
    }

    @PostMapping(value = "/create", produces =  "application/json", consumes =  "application/json")
    public ResponseEntity<SkillDAO> createSkill(@RequestBody SkillDAO skillDAO){
        if (skillDAO.getName()== null || skillDAO==null || skillDAO.getName().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Skill skill =skillService.findByName(skillDAO.getName());
        if (skill!=null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Skill createdSkill=skillService.create(skillDAO);
        Skill savedSkill = skillService.save(createdSkill);
        SkillDAO result= new SkillDAO(savedSkill);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable("id") Long id) throws Exception{
        Skill skill = skillService.findById(id);
        if (skill != null) {
            skillService.remove(skill);
            skillService.save(skill);
            return new ResponseEntity <>(objectMapper.writeValueAsString("Skill is deleted."), HttpStatus.OK);
        } else {
            return new ResponseEntity <>(objectMapper.writeValueAsString("Not valid skill id"), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value="/{id}")
    public ResponseEntity<SkillDAO> getSkill (@PathVariable("id") Long id) {
        Skill skill = skillService.findById(id);
        if (skill == null) {
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity <>(new SkillDAO(skill), HttpStatus.OK);
        }
    }

    @PutMapping(value = "/update/{id}", consumes =  "application/json", produces =  "application/json")
    public ResponseEntity<SkillDAO> updateSkill(@PathVariable("id") Long id, @RequestBody SkillDAO skillDAO) {
        Skill skill = skillService.findById(id);
        if (skill == null) {
            return new ResponseEntity <>(HttpStatus.NOT_FOUND);
        }
        if (skillDAO == null || skillDAO.getName().equals("")){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Skill skillUpdated = skillService.update(skill,skillDAO);
        Skill skillSaved =skillService.save(skillUpdated);
        SkillDAO skillDAO1 = new SkillDAO(skillSaved);
        return new ResponseEntity<>(skillDAO1, HttpStatus.OK);

    }


}
