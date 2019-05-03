package com.example.demo.controller;

import com.example.demo.dao.JobCandidateDAO;
import com.example.demo.dao.JobCandidatesDAO;
import com.example.demo.dao.SkillDAO;
import com.example.demo.model.JobCandidate;
import com.example.demo.model.Skill;
import com.example.demo.sevice.JobCandidateService;
import com.example.demo.sevice.SkillService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/candidates")
public class JobCandidateController {

    @Autowired
    private JobCandidateService jobCandidateService;

    @Autowired
    private  SkillService skillService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping
    public ResponseEntity<JobCandidatesDAO> getAll(){
        List<JobCandidate> jobCandidates = jobCandidateService.findAll();
        JobCandidatesDAO result = new JobCandidatesDAO();
        result.setJobCandidateDAOList(new ArrayList<>());
        for (JobCandidate jobCandidate: jobCandidates){
            JobCandidateDAO jobCandidateDAO = new JobCandidateDAO(jobCandidate);
            result.addJobCandidateDAO(jobCandidateDAO);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/create", consumes = "application/json",produces = "application/json")
    public ResponseEntity<JobCandidateDAO> createJobCandidate (@RequestBody JobCandidateDAO jobCandidateDAO){
        if (jobCandidateDAO==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (jobCandidateDAO.getFirstName().isEmpty()==true ||
            jobCandidateDAO.getLastName().isEmpty()==true ||
            jobCandidateDAO.getContactNumber().isEmpty()==true ||
            jobCandidateDAO.getEmail().isEmpty()==true ||
            jobCandidateDAO.getDateOfBirth().isEmpty()==true
        ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JobCandidate jobCandidate = jobCandidateService.create(jobCandidateDAO);
        JobCandidate saved = jobCandidateService.save(jobCandidate);
        JobCandidateDAO result= new JobCandidateDAO(saved);
        return new ResponseEntity <>(result, HttpStatus.OK);

    }

    @GetMapping(value="/{id}", produces = "application/json")
    public  ResponseEntity<JobCandidateDAO> getCandidate(@PathVariable Long id){
        JobCandidate jobCandidate = jobCandidateService.findById(id);
        if (jobCandidate==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        JobCandidateDAO jobCandidateDAO = new JobCandidateDAO(jobCandidate);
        return new ResponseEntity<>(jobCandidateDAO, HttpStatus.OK);
    }

    @PostMapping(value="/addSkill/candidate/{idCandidate}/skill/{idSkill}", produces = "application/json")
    public ResponseEntity<JobCandidateDAO> addNewSkill(@PathVariable Long idCandidate,@PathVariable Long idSkill){
        JobCandidate jobCandidate = jobCandidateService.findById(idCandidate);
        Skill skill = skillService.findById(idSkill);
        if (jobCandidate==null || skill==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        JobCandidate jobCandidate1 = jobCandidateService.addSkill(jobCandidate,skill);
        JobCandidate jobCandidate2 = jobCandidateService.save(jobCandidate1);
        skill.getJobCandidateList().add(jobCandidate1);
        skillService.save(skill);
        JobCandidateDAO jobCandidateDAO =new JobCandidateDAO(jobCandidate2);
        return new ResponseEntity<>(jobCandidateDAO, HttpStatus.OK);
    }

    @PostMapping(value="/removeSkill/candidate/{idCandidate}/skill/{idSkill}", produces = "application/json")
    public ResponseEntity<JobCandidateDAO> removeSkill(@PathVariable Long idCandidate,@PathVariable Long idSkill){
        JobCandidate jobCandidate = jobCandidateService.findById(idCandidate);
        Skill skill = skillService.findById(idSkill);
        if (jobCandidate==null || skill==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        JobCandidate jobCandidate1 = jobCandidateService.removeSkill(jobCandidate,skill);
        JobCandidate jobCandidate2 = jobCandidateService.save(jobCandidate1);
        JobCandidateDAO jobCandidateDAO =new JobCandidateDAO(jobCandidate2);
        return new ResponseEntity<>(jobCandidateDAO, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteCandidate(@PathVariable("id") Long id) throws Exception{
        JobCandidate jobCandidate= jobCandidateService.findById(id);
        if (jobCandidate != null) {
            jobCandidateService.remove(jobCandidate);
            jobCandidateService.save(jobCandidate);
            return new ResponseEntity <>(objectMapper.writeValueAsString("Candidate is removed."), HttpStatus.OK);
        } else {
            return new ResponseEntity <>(objectMapper.writeValueAsString("Not valid candidate id"), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/searchSkill/{skillName}")
    public ResponseEntity<List<JobCandidateDAO>> searchBySkillName(@PathVariable String skillName){
        Skill skill = skillService.findByName(skillName);
        if (skill == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<JobCandidate> jobCandidates = jobCandidateService.findBySkill(skill);
        if (jobCandidates == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<JobCandidateDAO> result = new ArrayList<>();
        for (JobCandidate jobCandidate:jobCandidates){
            JobCandidateDAO jobCandidateDAO = new JobCandidateDAO(jobCandidate);
            result.add(jobCandidateDAO);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PostMapping(value = "/update/{id}")
    public ResponseEntity<JobCandidateDAO> update(@PathVariable Long id,@RequestBody JobCandidateDAO jobCandidateDAO) {
        JobCandidate jobCandidate = jobCandidateService.findById(id);
        if (jobCandidate==null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (jobCandidateDAO==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (jobCandidateDAO.getFirstName().isEmpty()==true ||
                jobCandidateDAO.getLastName().isEmpty()==true ||
                jobCandidateDAO.getContactNumber().isEmpty()==true ||
                jobCandidateDAO.getEmail().isEmpty()==true ||
                jobCandidateDAO.getDateOfBirth().isEmpty()==true
        ){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        JobCandidate updated = jobCandidateService.update(jobCandidate,jobCandidateDAO);
        JobCandidate saved = jobCandidateService.save(updated);
        JobCandidateDAO result = new JobCandidateDAO(saved);
        return new ResponseEntity <> (result, HttpStatus.OK);
    }

    @GetMapping(value="/search/firstName/{first}/secondName/{second}",produces = "application/json")
    public ResponseEntity<List<JobCandidateDAO>> searchByName(@PathVariable String first, @PathVariable String second){
        List<JobCandidate> jobCandidates= jobCandidateService.findByName(first,second);
        if (jobCandidates==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<JobCandidateDAO> jobCandidateDAOS = new ArrayList<>();
        for (JobCandidate jobCandidate: jobCandidates){
            JobCandidateDAO jobCandidateDAO = new JobCandidateDAO(jobCandidate);
            jobCandidateDAOS.add(jobCandidateDAO);
        }
        return new ResponseEntity<>(jobCandidateDAOS,HttpStatus.OK);

    }
}
