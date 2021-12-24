package com.technoelevate.academy.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.technoelevate.academy.dto.Batch;
import com.technoelevate.academy.dto.CandidatesDetails;
import com.technoelevate.academy.dto.ClientMentorDetails;
import com.technoelevate.academy.dto.FileUpload;
import com.technoelevate.academy.dto.TrainersDetails;
import com.technoelevate.academy.repository.BatchRepository;
import com.technoelevate.academy.repository.CandidateRepository;
import com.technoelevate.academy.repository.ClientMentorRepository;
import com.technoelevate.academy.repository.FileUploadRepository;
import com.technoelevate.academy.repository.TrainersRepository;
import com.technoelevate.academy.service.BatchServiceImpl;

/**
 * @author AkramLadaf
 *
 */
@SpringBootTest
class BatchServiceImplTest {
	
	@Mock
	private BatchRepository batchRepository;

	@Mock
	private CandidateRepository candidateRepository;

	@Mock
	private ClientMentorRepository clientMentorRepository;

	@Mock
	private FileUploadRepository fileUploadRepository;

	@Mock
	private TrainersRepository trainersRepository;
	
	@InjectMocks
	private BatchServiceImpl batchServiceImpl;

	/**
	 * Test method for
	 * {@link com.technoelevate.academy.service.BatchServiceImpl#saveBatch(org.springframework.web.multipart.MultipartFile, com.technoelevate.academy.dto.Batch)}.
	 */
	@Test
	void testSaveBatch() {
		FileUpload files = new FileUpload();
		ClientMentorDetails mentor = new ClientMentorDetails(100, "akram", "SW", 8451254754l, "akr123@gmail.com");
		List<ClientMentorDetails> mentors = new ArrayList<>();
		List<TrainersDetails> trainers = new ArrayList<>();
		List<CandidatesDetails> candidates = new ArrayList<>();
		mentors.add(mentor);
		String[] technology = { "Java", "Jdbc" };
		String[] tymentor = { "Nilim", "xyz" };
		TrainersDetails trainer = new TrainersDetails(100, "bhag", "Friday", "bhag123@gmail.com", technology);
		trainers.add(trainer);
		CandidatesDetails candidate = new CandidatesDetails(100, "Akram", "malar", 8547584541l, "akram123@gmail.com", "BE", "ENT", 2019,
				65, 67, 74, 0);
		candidates.add(candidate);
		LocalDate localDate = LocalDate.now();
		Batch batch = new Batch(0, "Internal", "Bangalore", "Spring Boot", localDate, tymentor, files, mentors,
				trainers, candidates);
		MockMultipartFile file = new MockMultipartFile("file", "hello.txt", MediaType.APPLICATION_JSON_VALUE,
				"Hello, World!".getBytes());
		Mockito.when(batchRepository.save(Mockito.any())).thenReturn(batch);
		Batch batch2 = batchServiceImpl.saveBatch(file, batch);
		assertEquals(batch2.getDate(), batch.getDate());
	}
    
	/**
	 * Test method for
	 * {@link com.technoelevate.academy.service.BatchServiceImpl#getBatch(int)}.
	 */
	@Test
	void testGetBatch() {
		FileUpload files = new FileUpload();
		ClientMentorDetails mentor = new ClientMentorDetails(100, "sahid", "SW", 8451254754l, "sa123@gmail.com");
		List<ClientMentorDetails> mentors = new ArrayList<>();
		List<TrainersDetails> trainers = new ArrayList<>();
		List<CandidatesDetails> candidates = new ArrayList<>();
		mentors.add(mentor);
		String[] technology = { "Java", "Jdbc" };
		String[] tymentor = { "Bharat", "xyz" };
		TrainersDetails trainer = new TrainersDetails(100, "bhag", "Friday", "bhag123@gmail.com", technology);
		trainers.add(trainer);
		CandidatesDetails candidate = new CandidatesDetails(100, "Akram", "BTM", 8547584541l, "akram123@gmail.com", "BE", "ENT", 2019,
				65, 67, 74, 0);
		candidates.add(candidate);
		LocalDate localDate = LocalDate.now();
		Batch batch = new Batch(100, "Internal", "Bangalore", "Spring Boot", localDate, tymentor, files, mentors,
				trainers, candidates);
		Mockito.when(batchRepository.findByBatchId(Mockito.anyInt())).thenReturn(batch);
		Batch batch2 = batchServiceImpl.getBatch(batch.getBatchId());
		assertEquals(batch2.getDate(), batch.getDate());

	}

	/**
	 * Test method for
	 * {@link com.technoelevate.academy.service.BatchServiceImpl#deleteBatch(int)}.
	 */
	@Test
	void testDeleteBatch() {
		FileUpload files = new FileUpload();
		ClientMentorDetails mentor = new ClientMentorDetails(100, "akram", "SW", 8451254754l, "akram123@gmail.com");
		List<ClientMentorDetails> mentors = new ArrayList<>();
		List<TrainersDetails> trainers = new ArrayList<>();
		List<CandidatesDetails> candidates = new ArrayList<>();
		mentors.add(mentor);
		String[] technology = { "Java", "Jdbc" };
		String[] tymentor = { "nilim", "xyz" };
		TrainersDetails trainer = new TrainersDetails(100, "bhag", "Friday", "bhag123@gmail.com", technology);
		trainers.add(trainer);
		CandidatesDetails candidate = new CandidatesDetails(100, "Akram", "BTM", 8547584541l, "akram123@gmail.com", "BE", "ENT", 2019,
				65, 67, 74, 0);
		candidates.add(candidate);
		LocalDate localDate = LocalDate.now();
		Batch batch = new Batch(115, "Internal", "Bangalore", "Spring Boot", localDate, tymentor, files, mentors,
				trainers, candidates);
		Mockito.when(batchRepository.findByBatchId(Mockito.anyInt())).thenReturn(batch);
		batchRepository.delete(Mockito.any());
		Batch batch2 = batchServiceImpl.deleteBatch(batch.getBatchId());
		assertEquals(batch2.getBatchId(), batch.getBatchId());
	}

}
