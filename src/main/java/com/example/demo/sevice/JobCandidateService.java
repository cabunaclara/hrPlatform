package com.example.demo.sevice;

import com.example.demo.dao.JobCandidateDAO;
import com.example.demo.model.JobCandidate;
import com.example.demo.model.Skill;

import javax.print.attribute.standard.JobSheets;
import java.util.List;

public interface JobCandidateService {

    JobCandidate findById(Long id);

    JobCandidate save(JobCandidate jobCandidate);

    List<JobCandidate> findAll();

    JobCandidate create(JobCandidateDAO jobCandidateDAO);

    void remove (JobCandidate jobCandidate);

    JobCandidate update (JobCandidate jobCandidate, JobCandidateDAO jobCandidateDAO);

    JobCandidate addSkill(JobCandidate jobCandidate, Skill skill);

    JobCandidate removeSkill(JobCandidate jobCandidate, Skill skill);

    List<JobCandidate> findBySkill(Skill skill);

    List<JobCandidate> findByName(String firstName, String lastName);
}
