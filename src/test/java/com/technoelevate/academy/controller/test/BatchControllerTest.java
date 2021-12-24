package com.technoelevate.academy.controller.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.awt.Image;
import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technoelevate.academy.controller.BatchController;
import com.technoelevate.academy.dto.Batch;
import com.technoelevate.academy.dto.CandidatesDetails;
import com.technoelevate.academy.dto.ClientMentorDetails;
import com.technoelevate.academy.dto.FileUpload;
import com.technoelevate.academy.dto.TrainersDetails;
import com.technoelevate.academy.message.ResponseMessage;
import com.technoelevate.academy.service.BatchService;

@SpringBootTest
class BatchControllerTest {

	@InjectMocks
	private BatchController batchController;

	@Mock
	private BatchService batchService;

	private MockMvc mockMvc;
	@Mock
	private ObjectMapper mapper;
	private ObjectMapper objectMapper;

	@BeforeEach
	void setUp() throws Exception {
		this.mockMvc = MockMvcBuilders.standaloneSetup(batchController).build();
		this.objectMapper = new ObjectMapper();
		this.objectMapper.findAndRegisterModules();
	}

	/**
	 * Test method for
	 * {@link com.technoelevate.academy.controller.BatchController#addBatch(org.springframework.web.multipart.MultipartFile, java.lang.String)}.
	 * 
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	
	@Test
	void testAddBatch() throws UnsupportedEncodingException, Exception {

		FileUpload files = new FileUpload();
		ClientMentorDetails mentor = new ClientMentorDetails(100, "akram", "SW", 8451254754l, "akr123@gmail.com");
		List<ClientMentorDetails> mentors = new ArrayList<>();
		List<TrainersDetails> trainers = new ArrayList<>();
		List<CandidatesDetails> candidates = new ArrayList<>();
		mentors.add(mentor);
		String[] technology = { "Java", "Jdbc" };
		String[] tymentor = { "Bharat", "xyz" };
		TrainersDetails trainer = new TrainersDetails(100, "Nilim", "Friday", "nilim123@gmail.com", technology);
		trainers.add(trainer);
		CandidatesDetails candidate = new CandidatesDetails(100, "akram", "malar", 8547584541l, "akram123@gmail.com", "BE", "ENT", 2019,
				65, 67, 74, 0);
		candidates.add(candidate);
		LocalDate localDate = LocalDate.now();
		Batch batch = new Batch(0,"Internal", "Bangalore", "Spring Boot", localDate, tymentor, files, mentors,
				trainers, candidates);
		MockMultipartFile file = new MockMultipartFile("files", "hello.txt", MediaType.APPLICATION_JSON_VALUE,
				"Hello, World!".getBytes());
		Mockito.when(batchService.saveBatch(Mockito.any(), Mockito.any())).thenReturn(batch);
		String jsonObject =objectMapper.writeValueAsString(batch);
		String result = mockMvc.perform(multipart("/api/v1/academy/batch").file(file).param("data", jsonObject))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseMessage responseMessage = objectMapper.readValue(result, ResponseMessage.class);
		Map<String, String> responseMessageMap = (Map<String, String>) responseMessage.getData();
		for (Map.Entry<String, String> message : responseMessageMap.entrySet()) {
			assertEquals(batch.getBatchId(), message.getValue());
			break;
		}

	}
	
	/**
	 * Test method for
	 * {@link com.technoelevate.academy.controller.BatchController#getBatch(int)}.
	 * 
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unchecked")
	@Test
	void testGetBatch() throws UnsupportedEncodingException, Exception {

		List<CandidatesDetails> candidatesLists = new ArrayList<>();
		List<ClientMentorDetails> clientMentorLists = new ArrayList<>();
		List<TrainersDetails> trainersLists = new ArrayList<>();
         LocalDate localDate = LocalDate.now();
		Batch batch = new Batch(110, "internal", "bfsh", "ss", localDate);
		String[] tymentor = { "kabhi", "subi", "abhi" };
		batch.setTyMentor(tymentor);
		CandidatesDetails candidatesList = new CandidatesDetails("aacss", "aac", 9108074711l, "akram@gmail.com", "s", "d",
				2019, 55.55, 55.55, 55.55, 55.55);
		candidatesLists.add(candidatesList);
		batch.setCandidates(candidatesLists);
		ClientMentorDetails clientMentorList = new ClientMentorDetails("g", "df", 9108074711l, "akram@gmail.com");
		clientMentorLists.add(clientMentorList);
		batch.setMentors(clientMentorLists);
		String[] tech = { "java", "js", "php" };
		TrainersDetails trainersList = new TrainersDetails("d", "dc", "akram@gmail.com");
		trainersList.setTechnology(tech);
		trainersLists.add(trainersList);
		batch.setTrainers(trainersLists);
		Mockito.when(batchService.getBatch(Mockito.anyInt())).thenReturn(batch);
		String jsonString = objectMapper.writeValueAsString(batch);
		String resultString = mockMvc
				.perform(
						get("/api/v1/academy/batch/"+110).contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonString))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		
		ResponseMessage readValue = objectMapper.readValue(resultString, ResponseMessage.class);
		Map<String, String> map = (Map<String, String>)readValue.getData();
		for (Map.Entry<String, String> mm : map.entrySet()) {
			assertEquals(batch.getBatchId(), mm.getValue());
			break;
		}
		
	}
	
	/**
	 * Test method for
	 * {@link com.technoelevate.academy.controller.BatchController#deleteBatch(int)}.
	 * @throws Exception 
	 * @throws UnsupportedEncodingException 
	 */
	@SuppressWarnings("unchecked")
	@Test
	void testDeleteBatch() throws UnsupportedEncodingException, Exception {
		FileUpload files = new FileUpload();
		ClientMentorDetails mentor = new ClientMentorDetails(110, "akram", "SW", 8451254754l, "sa123@gmail.com");
		List<ClientMentorDetails> mentors = new ArrayList<>();
		List<TrainersDetails> trainers = new ArrayList<>();
		List<CandidatesDetails> candidates = new ArrayList<>();
		mentors.add(mentor);
		String[] technology = { "Jaf", "Jdbc" };
		String[] tymentor = { "Bhf", "xyz" };
		TrainersDetails trainer = new TrainersDetails(100, "bharath", "Friday", "bharath123@gmail.com", technology);
		trainers.add(trainer);
		CandidatesDetails candidate = new CandidatesDetails(100, "akram", "Malar", 8547584541l, "akram123@gmail.com", "BE", "ENT", 2019,
				65, 67, 74, 0);
		candidates.add(candidate);
		LocalDate localDate = LocalDate.now();
		Batch batch = new Batch(100, "Internal", "Bangalore", "Spring Boot", localDate, tymentor, files, mentors,
				trainers, candidates);
		Mockito.when(batchService.deleteBatch(Mockito.anyInt())).thenReturn(batch);
		String result = mockMvc.perform(delete("/api/v1/academy/batch/" + batch.getBatchId()))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
		ResponseMessage responseMessage = objectMapper.readValue(result, ResponseMessage.class);
		Map<String, String> responseMessageMap = (Map<String, String>) responseMessage.getData();
		for (Map.Entry<String, String> message : responseMessageMap.entrySet()) {
			assertEquals(batch.getBatchId(), message.getValue());
			break;
		}
	}

}
