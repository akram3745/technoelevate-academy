package com.technoelevate.academy.exception;

import java.util.Date;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.technoelevate.academy.message.ErrorMessage;
@RestControllerAdvice
public class ExceptionHandlerController extends  ResponseEntityExceptionHandler {
	
	
	@ExceptionHandler(CustomException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage IdNotFound(CustomException exception, WebRequest request) {
		return new ErrorMessage(HttpStatus.BAD_REQUEST.value(), new Date(), exception.getMessage(),
				request.getDescription(false));
	}
	
	@ExceptionHandler(FileNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage fileNotFound(FileNotFoundException exception, WebRequest request) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), exception.getMessage(),
				request.getDescription(false));
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(HttpStatus.EXPECTATION_FAILED.value(), new Date(),
				"Validation Error", exception.getBindingResult().getFieldError().getDefaultMessage());
		return new ResponseEntity<>(errorMessage, HttpStatus.EXPECTATION_FAILED);
	}
	
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseStatus(HttpStatus.EXPECTATION_FAILED)
	public ResponseEntity<CustomExceptionResponse> handleValidationException(ConstraintViolationException e) {
		CustomExceptionResponse validationResponse = new CustomExceptionResponse();
		validationResponse.setError(true);
		StringBuilder bld = new StringBuilder();
		for (ConstraintViolation<?> s : e.getConstraintViolations()) {
			bld.append(s.getMessage());
		}
		validationResponse.setMessage(bld.toString());
		validationResponse.setData(null);
		return new ResponseEntity<CustomExceptionResponse>(validationResponse, HttpStatus.EXPECTATION_FAILED);
	}

	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public ErrorMessage badRequest(CustomException exception, WebRequest request) {
		return new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), exception.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorMessage internalServerError(CustomException exception, WebRequest request) {
		return new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), exception.getMessage(),
				request.getDescription(false));
	}

}
