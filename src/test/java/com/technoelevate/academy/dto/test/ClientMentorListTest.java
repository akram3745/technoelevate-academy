package com.technoelevate.academy.dto.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technoelevate.academy.dto.ClientMentorDetails;

/**
 * @author Akramladaf
 *
 */
class ClientMentorListTest {


	private String jsonObject="{\"clientMentorId\":100,\"clientMentorName\":\"sahid\",\"designation\":\"SW\",\"contactNo\":8451254754,\"emailId\":\"sa123@gmail.com\"}";
	private ObjectMapper mapper;
	
	@BeforeEach
	public void setup() {
		this.mapper = new ObjectMapper();
		this.mapper.findAndRegisterModules();
	}

	@Test
	public void testMentorSerializable() throws JsonMappingException, JsonProcessingException {
		ClientMentorDetails mentor = mapper.readValue(jsonObject, ClientMentorDetails.class);
		String jsonObject = mapper.writeValueAsString(mentor);
		assertEquals(this.jsonObject, jsonObject);
	}
	@Test
	public void testMentorDeserializable() throws JsonMappingException, JsonProcessingException {
		ClientMentorDetails mentor = mapper.readValue(jsonObject, ClientMentorDetails.class);
		int expected=100;
		assertEquals(expected, mentor.getClientMentorId());
	}


}
