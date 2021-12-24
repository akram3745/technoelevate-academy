package com.technoelevate.academy.dto.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technoelevate.academy.dto.FileUpload;

/**
 * @author AkramLadaf
 *
 */
class FileUploadTest {

	private String jsonObject="{\"fileId\":0,\"fileName\":\"abc\",\"fileType\":\"png\",\"filePath\":\"D/:\"}";
	private ObjectMapper mapper;
	
	@BeforeEach
	public void setup() {
		this.mapper = new ObjectMapper();
		this.mapper.findAndRegisterModules();
	}

	@Test
	public void testFileUploadSerializable() throws JsonMappingException, JsonProcessingException {
		FileUpload fileUpload = mapper.readValue(jsonObject, FileUpload.class);
		String jsonObject = mapper.writeValueAsString(fileUpload);
		assertEquals(this.jsonObject, jsonObject);
	}
	@Test
	public void testFileUploadDeserializable() throws JsonMappingException, JsonProcessingException {
		FileUpload fileUpload = mapper.readValue(jsonObject, FileUpload.class);
		String expected="abc";
		assertEquals(expected, fileUpload.getFileName());
	}


}
