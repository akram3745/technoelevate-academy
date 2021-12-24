package com.technoelevate.academy.constant;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.criteria.From;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.technoelevate.academy.dto.FileUpload;
import com.technoelevate.academy.exception.CustomException;
import com.technoelevate.academy.message.Message;
import com.technoelevate.academy.pojo.Form;

@SuppressWarnings("unused")
@Component
public class Constant {

	@Autowired
	RestTemplate restTemplate;

	private Path directory;
	private String dir;

	public FileUpload getFileUpload(MultipartFile file, Path directory2) throws IOException {

		Files.createDirectories(directory2);
		String filename = file.getOriginalFilename();
		Path path = directory2.resolve(filename);
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		FileUpload fileUpload = new FileUpload(filename, file.getContentType(), path.toString());
		return fileUpload;
	}

	public Path getPath(String filename) {
		String dirk = "E:\\fileupload\\";
		dir = dirk + "\\" + filename;
		directory = Paths.get(dir).toAbsolutePath().normalize();
		return directory;

	}

	public Form getform(String email,int batchId) {
		try {

			String url="http://localhost:8080/api/v1/uploadform/form/"+email+"/"+batchId;
			Form form = restTemplate.getForObject(url, Form.class);
			return form;
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new CustomException(Message.NO_MATCH_FORM_FOUND_FOR_CANDIDATES);
		}

	}


}
