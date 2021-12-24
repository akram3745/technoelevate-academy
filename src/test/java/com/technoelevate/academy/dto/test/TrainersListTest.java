package com.technoelevate.academy.dto.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technoelevate.academy.dto.TrainersDetails;

/**
 * @author AkramLadaf
 *
 */
class TrainersListTest {

	private String jsonObject="{\"trainerId\":100,\"trainerName\":\"Nilim\",\"days\":\"Friday\",\"emailId\":\"nilim123@gmail.com\",\"technology\":[\"Java\",\"Jdbc\"]}";
	private ObjectMapper mapper;
	
	@BeforeEach
	public void setup() {
		this.mapper = new ObjectMapper();
		this.mapper.findAndRegisterModules();
	}

	@Test
	public void testTrainerSerializable() throws JsonMappingException, JsonProcessingException {
		TrainersDetails trainer = mapper.readValue(jsonObject, TrainersDetails.class);
		String jsonObject = mapper.writeValueAsString(trainer);
		assertEquals(this.jsonObject, jsonObject);
	}
	@Test
	public void testTrainerDeserializable() throws JsonMappingException, JsonProcessingException {
		TrainersDetails trainer = mapper.readValue(jsonObject, TrainersDetails.class);
		int expected=100;
		assertEquals(expected, trainer.getTrainerId());
	}

}
