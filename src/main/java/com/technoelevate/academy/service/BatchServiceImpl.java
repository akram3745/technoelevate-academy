package com.technoelevate.academy.service;

import static com.technoelevate.academy.message.Message.*;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import static com.technoelevate.academy.constant.Constant.*;

import com.technoelevate.academy.constant.Constant;
import com.technoelevate.academy.dto.Batch;
import com.technoelevate.academy.dto.CandidatesDetails;
import com.technoelevate.academy.dto.FileUpload;
import com.technoelevate.academy.exception.CustomException;
import com.technoelevate.academy.exception.FileNotFoundException;
import com.technoelevate.academy.message.Message;
import com.technoelevate.academy.pojo.Form;
import com.technoelevate.academy.repository.BatchRepository;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unused")
@Slf4j
@Service
@Validated
public class BatchServiceImpl implements BatchService {

	@Autowired
	private BatchRepository batchRepository;

	Batch batch3 = new Batch();
	
	Form form = new Form();

	Constant constant = new Constant();
	private Path directory;

	@Override
	public Batch saveBatch(MultipartFile file, @Valid Batch batch) {
		try {
			Batch batch2 = batchRepository.findByBatchId(batch.getBatchId());
			if (batch2 != null) {
				try {
					String pathString = "Batch" + batch2.getLocation() + batch2.getTechnology();
					this.directory = constant.getPath(pathString);
					FileUtils.forceDelete(new java.io.File(this.directory.toString()));
				} catch (Exception e) {
					log.error(FILE_NOT_FOUND);
					throw new FileNotFoundException(FILE_NOT_FOUND);
				}
			}
			try {
				this.directory = constant.getPath("Batch" + batch.getLocation() + batch.getTechnology());
				FileUpload fileUpload = constant.getFileUpload(file, this.directory);
				System.out.println(fileUpload);
				batch.setFiles(fileUpload);

			} catch (Exception e) {
				log.error(SOMETHING_WENT_WRONG);
				throw new CustomException(SOMETHING_WENT_WRONG);
			}

			List<CandidatesDetails> candidates = batch.getCandidates();
			List<CandidatesDetails> candidates2 = new ArrayList<>();
			if (!candidates.isEmpty()) {

				for (CandidatesDetails candidatesDetails : candidates) {
					   form = constant.getform(candidatesDetails.getEmail(),batch.getBatchId());
					if (form != null && form.getStatus() != null) {
						if (form.getStatus().equalsIgnoreCase("onboard")) {
							candidates2.add(candidatesDetails);
						}

					}
				}
				if (candidates2.isEmpty()) {
					
					throw new CustomException(NO_MATCH_FORM_FOUND_FOR_CANDIDATES);
				}
				batch.setCandidates(candidates2);
				if (batch.getBatchType().equalsIgnoreCase("Internal")) {
					batch3 = batchRepository.save(batch);
					form.setBatchId(batch3.getBatchId());
				} else {
					batch.setMentors(null);
					batch3 = batchRepository.save(batch);
					form.setBatchId(batch3.getBatchId());
				}
				for (CandidatesDetails candidatesDetails : candidates2) {
					form = constant.getform(candidatesDetails.getEmail(),batch3.getBatchId());	
				}
				
				return batch3;

			} else {
				throw new CustomException(Message.CANDIDATE_LIST_IS_EMPTY);
			}

		} catch (CustomException exception) {
			throw exception;
		} catch (FileNotFoundException exception) {
			throw exception;
		} catch (Exception e) {
			e.printStackTrace();
			log.error(SOMETHING_WENT_WRONG);
			throw new CustomException(SOMETHING_WENT_WRONG);
		}

	}

	@Override
	public Batch getBatch(int id) {

		if (id > 99) {

			try {
				return batchRepository.findByBatchId(id);
			} catch (Exception e) {
				log.error(SOMETHING_WENT_WRONG);
				throw new CustomException(SOMETHING_WENT_WRONG);
			}

		}
		log.error(INVALID_ID);
		throw new CustomException(INVALID_ID);

	}

	@Override
	public Batch deleteBatch(int id) {

		try {
			if (id > 99) {
				batch3 = batchRepository.findByBatchId(id);
				batchRepository.delete(batch3);
				if (batch3 != null) {
					String pathString = "Batch" + batch3.getLocation() + batch3.getTechnology();
					this.directory = constant.getPath(pathString);
					try {
						FileUtils.forceDelete(new java.io.File(this.directory.toString()));
					} catch (Exception e) {
						log.error(FILE_NOT_FOUND);
						throw new FileNotFoundException(FILE_NOT_FOUND);
					}

				}
				return batch3;
			}
			log.error(INVALID_ID);
			throw new CustomException(INVALID_ID);

		} catch (Exception e) {
			log.error(SOMETHING_WENT_WRONG);
			throw new CustomException(SOMETHING_WENT_WRONG);
		}
	}

}
