package com.technoelevate.academy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.technoelevate.academy.dto.Batch;

public interface BatchRepository extends JpaRepository<Batch, Integer> {

	public Batch findByBatchId(int id);
	
	
}
