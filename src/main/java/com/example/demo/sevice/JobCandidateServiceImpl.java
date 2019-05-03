package com.example.demo.sevice;

import com.example.demo.dao.JobCandidateDAO;
import com.example.demo.model.JobCandidate;
import com.example.demo.model.Skill;
import com.example.demo.repository.JobCandidateRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JobCandidateServiceImpl implements JobCandidateService {

    @Autowired
    public JobCandidateRepository jobCandidateRepository;

    @Autowired
    public ObjectMapper objectMapper;

    @Override
    public JobCandidate findById(Long id){
        try{
            return jobCandidateRepository.findById(id).get();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JobCandidate save (JobCandidate jobCandidate) {
        return jobCandidateRepository.save(jobCandidate);
    }

    @Override
    public List<JobCandidate> findAll() {
        return jobCandidateRepository.findAll();
    }

    @Override
    public JobCandidate create (JobCandidateDAO jobCandidateDAO) {
        JobCandidate jobCandidate = new JobCandidate(jobCandidateDAO);
        return jobCandidate;
    }

    @Override
    public void remove (JobCandidate jobCandidate) {
        jobCandidate.setActive(false);
    }

    @Override
    public JobCandidate update (JobCandidate jobCandidate, JobCandidateDAO jobCandidateDAO){
        jobCandidate.setFirstName(jobCandidateDAO.getFirstName());
        jobCandidate.setLastName(jobCandidateDAO.getLastName());
        jobCandidate.setEmail(jobCandidateDAO.getEmail());
        jobCandidate.setContactNumber(jobCandidateDAO.getContactNumber());
        Date date=jobCandidate.stringToDate(jobCandidateDAO.getDateOfBirth());
        jobCandidate.setDateOfBirth(date);
        return jobCandidate;
    }

    @Override
    public JobCandidate addSkill (JobCandidate jobCandidate, Skill skill){
        jobCandidate.addSkill(skill);
        return jobCandidate;
    }

    @Override
    public JobCandidate removeSkill (JobCandidate jobCandidate, Skill skill){
        jobCandidate.removeSkill(skill);
        return jobCandidate;
    }

    @Override
    public List<JobCandidate> findBySkill(Skill skill) {
        return skill.getJobCandidateList();
    }

    @Override
    public List<JobCandidate> findByName(String firstName, String lastName) {
        return jobCandidateRepository.findByName(firstName,lastName);
    }

}
