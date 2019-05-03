package com.example.demo.dao;

import java.util.List;

public class JobCandidatesDAO {

    private List<JobCandidateDAO> jobCandidateDAOList;

    public List<JobCandidateDAO> getJobCandidateDAOList() {
        return jobCandidateDAOList;
    }

    public void setJobCandidateDAOList(List<JobCandidateDAO> jobCandidateDAOList) {
        this.jobCandidateDAOList = jobCandidateDAOList;
    }

    public JobCandidatesDAO() {
    }

    public JobCandidatesDAO(List<JobCandidateDAO> jobCandidateDAOList) {
        this.jobCandidateDAOList = jobCandidateDAOList;
    }

    public void addJobCandidateDAO(JobCandidateDAO jobCandidateDAO){
        this.jobCandidateDAOList.add(jobCandidateDAO);
    }
}
