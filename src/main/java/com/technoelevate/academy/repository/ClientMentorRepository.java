package com.technoelevate.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.academy.dto.ClientMentorDetails;

public interface ClientMentorRepository extends JpaRepository<ClientMentorDetails, Integer>{

}
