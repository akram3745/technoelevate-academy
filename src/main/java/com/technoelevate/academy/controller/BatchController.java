package com.technoelevate.academy.controller;

import static com.technoelevate.academy.message.Message.BATCH_SAVE_SUCCESSFULL;
import static com.technoelevate.academy.message.Message.BATCH_TYPE_IS_INCORRECT;
import static com.technoelevate.academy.message.Message.DATA_DELETED_SUCCESFULLY;
import static com.technoelevate.academy.message.Message.INVALID_ID;
import static com.technoelevate.academy.message.Message.SOMETHING_WENT_WRONG;
import static com.technoelevate.academy.message.Message.SUCCESSFUL_FETCH_DATA;

import java.util.Date;

import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.technoelevate.academy.dto.Batch;
import com.technoelevate.academy.exception.CustomException;
import com.technoelevate.academy.message.ResponseMessage;
import com.technoelevate.academy.service.BatchService;

import lombok.extern.slf4j.Slf4j;

@SuppressWarnings("unused")
@Slf4j
@RestController
@Validated
@RequestMapping(path = "/api/v1/academy")
public class BatchController {

	@Autowired
	private BatchService batchService;

	@Autowired
	private ObjectMapper mappper;

	@PostMapping(path = "/batch")
	public ResponseEntity<ResponseMessage> addBatch(@RequestParam("files") MultipartFile file,
			@RequestParam("data") String batch2) throws JsonMappingException, JsonProcessingException {

		@Valid
		Batch batch;
		try {
			batch = mappper.readValue(batch2, Batch.class);

		} catch (JsonMappingException e) {
			log.error(SOMETHING_WENT_WRONG);
			throw new CustomException(SOMETHING_WENT_WRONG);
		}

		Batch saveBatch = batchService.saveBatch(file, batch);
		if (saveBatch != null) {
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage(HttpStatus.OK.value(), new Date(), false, BATCH_SAVE_SUCCESSFULL, saveBatch),
					HttpStatus.OK);
		}
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.BAD_REQUEST.value(), new Date(), true,
				BATCH_TYPE_IS_INCORRECT, saveBatch), HttpStatus.BAD_REQUEST);
	}

	@GetMapping(path = "/batch/{id}")
	public ResponseEntity<ResponseMessage> getBatch(@PathVariable("id") int id) {

		Batch batch = batchService.getBatch(id);
		if (batch != null) {
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage(HttpStatus.OK.value(), new Date(), false, SUCCESSFUL_FETCH_DATA, batch),
					HttpStatus.OK);

		}
		return new ResponseEntity<ResponseMessage>(
				new ResponseMessage(HttpStatus.BAD_REQUEST.value(), new Date(), true, INVALID_ID, batch),
				HttpStatus.BAD_REQUEST);

	}

	@DeleteMapping(path = "/batch/{id}")
	public ResponseEntity<ResponseMessage> deleteBatch(@PathVariable("id") int id) {

		Batch deleteBatch = batchService.deleteBatch(id);
		if (deleteBatch != null) {
			return new ResponseEntity<ResponseMessage>(new ResponseMessage(HttpStatus.OK.value(), new Date(), false,
					DATA_DELETED_SUCCESFULLY, deleteBatch), HttpStatus.OK);
		}
		return new ResponseEntity<ResponseMessage>(
				new ResponseMessage(HttpStatus.BAD_REQUEST.value(), new Date(), true, INVALID_ID, deleteBatch),
				HttpStatus.BAD_REQUEST);

	}

}
