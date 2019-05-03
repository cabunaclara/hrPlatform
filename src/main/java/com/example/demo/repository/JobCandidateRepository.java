package com.example.demo.repository;


import com.example.demo.model.JobCandidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobCandidateRepository extends JpaRepository<JobCandidate, Long> {


    @Query(value = "SELECT * FROM candidates c WHERE c.first_name = ?1 and c.last_name=?2 and c.active=true", nativeQuery = true)
    List<JobCandidate> findByName(String firstName, String lastName);
}
