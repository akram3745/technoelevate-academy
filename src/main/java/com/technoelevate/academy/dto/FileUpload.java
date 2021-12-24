package com.technoelevate.academy.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings("serial")
@Setter
@Getter
@Transactional
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FileUpload implements Serializable {
	
	@Id
	@SequenceGenerator(name = "file_sequence_generator", initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "file_sequence_generator")
	private int fileId;
	
	private String fileName;
	
	private String fileType;
	
	private String filePath;
	
	

	public FileUpload(String fileName, String fileType, String filePath) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.filePath = filePath;
	}
	
	

	@Override
	public String toString() {
		return "FileUpload [fileId=" + fileId + ", fileName=" + fileName + ", fileType=" + fileType + ", filePath="
				+ filePath + "]";
	}
	
	
	
	

}
