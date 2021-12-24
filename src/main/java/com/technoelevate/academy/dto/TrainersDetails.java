package com.technoelevate.academy.dto;
import static com.technoelevate.academy.message.Message.*;

import java.io.Serializable;
import java.sql.Clob;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Index;
import org.hibernate.annotations.IndexColumn;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.technoelevate.academy.converter.StringListConverter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@SuppressWarnings({ "serial", "unused" })
@Setter
@Getter
@Transactional
@Entity
@AllArgsConstructor
@Validated
@NoArgsConstructor
public class TrainersDetails implements Serializable{
	
	@Id
	@SequenceGenerator(name = "trainers_sequence_generator", initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "trainers_sequence_generator")
	private int trainerId;
	
	@NotEmpty(message = TRAINER_NAME_CANNOT_BE_EMPTY)
	private String trainerName;
	
	@NotEmpty(message = DAY_CANNOT_BE_EMPTY)
	private String days;
	
	@Pattern(regexp = "[a-zA-Z]+[0-9]*[@][a-z]*[.][a-z]{2,3}",message = ENTER_THE_CORRECT_EMAIL)
	private String emailId;
	
	
	@NotEmpty(message = TECHNOLOGY_CANNOT_BE_EMPTY)
	@Convert(converter = StringListConverter.class)
	private String[] technology;

	

	@Override
	public String toString() {
		return "TrainersList [trainerId=" + trainerId + ", trainerName=" + trainerName + ", days=" + days + ", emailId="
				+ emailId + ", technology=" + technology + "]";
	}



	public TrainersDetails(@NotEmpty(message = "Trainer name cannot be Empty") String trainerName,
			@NotEmpty(message = "Day cannot be Empty") String days,
			@Pattern(regexp = "[a-zA-Z]+[0-9]*[@][a-z]*[.][a-z]{2,3}", message = "Enter the correct email") String emailId) {
		super();
		this.trainerName = trainerName;
		this.days = days;
		this.emailId = emailId;
	}
	
	

	
	
	
	
	
	
	
	

}
