package com.example.demo.model;

/*
    Class reperesents Skill
 */


import com.example.demo.dao.SkillDAO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "skills")
@Where(clause = "active=true")
public class Skill implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "skillList",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<JobCandidate> jobCandidateList;

    @Column(name="active")
    private boolean active;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<JobCandidate> getJobCandidateList() {
        return jobCandidateList;
    }

    public void setJobCandidateList(List<JobCandidate> jobCandidateList) {
        this.jobCandidateList = jobCandidateList;
    }

    public Skill() {
    }

    public Skill(SkillDAO skillDAO) {
        this.setName(skillDAO.getName());
        this.setActive(true);
    }
}

