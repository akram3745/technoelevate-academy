package com.technoelevate.academy.dto;

import static com.technoelevate.academy.message.Message.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.technoelevate.academy.converter.StringListConverter;
import com.technoelevate.academy.message.Message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@SuppressWarnings({ "serial", "unused" })
@Setter
@Getter
@Transactional
@Entity
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class Batch implements Serializable {
	
	@Id
	@SequenceGenerator(name = "batch_sequence_generator", initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "batch_sequence_generator")
	private int batchId;
	
	@NotEmpty(message = BATCH_TYPE_CANNOT_BE_EMPTY)
	private String batchType;
	
	@NotEmpty(message = LOCTION_CANNOT_BE_EMPTY)
	private String location;
	
	@NotEmpty(message = TECHNOLOGY_CANNOT_BE_EMPTY)
	private String technology;
	
	
	@FutureOrPresent(message = DATE_SHOULD_BE_FUTURE_OR_PRESENT)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd",timezone = "Asia/Kolkata")
	private LocalDate date;
	
	
	@NotEmpty(message = Message.MENTORS_CANNOT_BE_EMPTY)
	@Convert(converter = StringListConverter.class)
	private String[] tyMentor;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "batchId")
	private FileUpload files;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "batchId")
	private List<@Valid  ClientMentorDetails> mentors;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "batchId")
	private List<@Valid  TrainersDetails> trainers;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "batchId")
	private List<@Valid  CandidatesDetails> candidates;
	
	
	


	@Override
	public String toString() {
		return "Batch [batchId=" + batchId + ", batchType=" + batchType + ", location=" + location + ", technology="
				+ technology + ", date=" + date + ", tyMentor=" + Arrays.toString(tyMentor) + "]";
	}





	public Batch(int batchId, @NotEmpty(message = "Batch type cannot be Empty") String batchType,
			@NotEmpty(message = "Loction cannot be empty") String location,
			@NotEmpty(message = "Technology cannot be Empty") String technology,
			@PastOrPresent(message = "Date should be past or present") LocalDate date) {
		super();
		this.batchId = batchId;
		this.batchType = batchType;
		this.location = location;
		this.technology = technology;
		this.date = date;
	}


	

	
	
}
