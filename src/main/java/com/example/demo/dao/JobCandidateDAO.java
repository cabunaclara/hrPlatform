package com.example.demo.dao;

import com.example.demo.model.JobCandidate;
import com.example.demo.model.Skill;

import java.util.ArrayList;
import java.util.List;

public class JobCandidateDAO {

    public String firstName;

    public String lastName;

    public String dateOfBirth;

    public String contactNumber;

    public String email;

    public List<SkillDAO> skillList;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<SkillDAO> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<SkillDAO> skillList) {
        this.skillList = skillList;
    }

    public JobCandidateDAO() {
    }

    public JobCandidateDAO(String firstName, String lastName, String dateOfBirth, String contactNumber, String email, List<SkillDAO> skillList) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.email = email;
        this.skillList = skillList;
    }

    public JobCandidateDAO(JobCandidate jobCandidate){
        this.firstName=jobCandidate.getFirstName();
        this.lastName=jobCandidate.getLastName();
        this.dateOfBirth=jobCandidate.dateToString();
        this.contactNumber=jobCandidate.getContactNumber();
        this.email=jobCandidate.getEmail();
        this.skillList= new ArrayList<>();
        List<Skill> jobCandidateList =jobCandidate.getSkillList();
        for (Skill skill: jobCandidateList){
            SkillDAO skillDAO = new SkillDAO(skill);
            this.skillList.add(skillDAO);
        }
    }
}
