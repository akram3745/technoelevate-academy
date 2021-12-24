package com.technoelevate.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technoelevate.academy.dto.TrainersDetails;

public interface TrainersRepository extends JpaRepository<TrainersDetails, Integer>{

}
