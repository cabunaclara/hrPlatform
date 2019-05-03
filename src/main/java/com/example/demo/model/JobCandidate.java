package com.example.demo.model;

/*
    Class represents JobCandidate
 */


import com.example.demo.dao.JobCandidateDAO;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "candidates")
@Where(clause = "active=true")
public class JobCandidate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="firstName", nullable = false)
    private String firstName;

    @Column(name="lastName", nullable = false)
    private String lastName;

    @Column(name="dateOfBirth", nullable = false)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @Column(name="contactNumber",nullable = false)
    private String contactNumber;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="active")
    private boolean active;

    @ManyToMany
    @JoinTable(
            name = "possesses",
            joinColumns = @JoinColumn(name = "jobCandidate_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skillList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

    public List<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<Skill> skillList) {
        this.skillList = skillList;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public JobCandidate() {
    }

    public JobCandidate(String firstName, String lastName, Date dateOfBirth,
                        String contactNumber, String email, List<Skill> skillList) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.contactNumber = contactNumber;
        this.email = email;
        this.skillList = skillList;
        this.active=true;
    }

    public Date stringToDate(String dateStr){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date date =null;
        try {
            date=sdf.parse(dateStr);
        }catch (ParseException e){
            e.printStackTrace();
            return null;
        }
        return date;
    }

    public String dateToString (){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateStr = sdf.format(this.getDateOfBirth());
        return dateStr;
    }

    public JobCandidate(JobCandidateDAO jobCandidateDAO){
        this.firstName = jobCandidateDAO.getFirstName();
        this.lastName = jobCandidateDAO.getLastName();
        this.contactNumber = jobCandidateDAO.getContactNumber();
        this.email = jobCandidateDAO.getEmail();
        this.skillList=new ArrayList<>();
        this.active=true;
        Date date = stringToDate(jobCandidateDAO.getDateOfBirth());
        this.dateOfBirth= date;
    }

    public void addSkill (Skill skill) {
        if (this.getSkillList().contains(skill)){
            return;
        }
        this.skillList.add(skill);
    }

    public void removeSkill (Skill skill) {
        if (!(this.getSkillList().contains(skill))){
            return;
        }
        this.skillList.remove(skill);
    }
}
