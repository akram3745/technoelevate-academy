package com.technoelevate.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.academy.dto.CandidatesDetails;

public interface CandidateRepository extends JpaRepository<CandidatesDetails, Integer>{

}
