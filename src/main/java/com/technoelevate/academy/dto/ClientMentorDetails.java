package com.technoelevate.academy.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Range;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static com.technoelevate.academy.message.Message.*;

import java.io.Serializable;
@SuppressWarnings("serial")
@Setter
@Getter
@Transactional
@Entity
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class ClientMentorDetails implements Serializable {
	

	@Id
	@SequenceGenerator(name = "clientmentor_sequence_generator", initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "clientmentor_sequence_generator")
	private int clientMentorId;
	
	
	@NotEmpty(message = CLIENT_MENTOR_NAME_CANNOT_BE_EMPTY)
	private String clientMentorName;
	
	@NotEmpty(message = DESIGNATION_CANNOT_BE_EMPTY)
	private String designation;
	
	@Range(min = 6000000000l , max = 9999999999l,message = ENTER_THE_CORRECT_NUMBER)
	private long contactNo;
	
	@Pattern(regexp = "[a-zA-Z]+[0-9]*[@][a-z]*[.][a-z]{2,3}",message = ENTER_THE_CORRECT_EMAIL)
	private String emailId;
	
	
	
	@Override
	public String toString() {
		return "ClientMentor [clientMentorId=" + clientMentorId + ", clientMentorName=" + clientMentorName
				+ ", designation=" + designation + ", contactNo=" + contactNo + ", emailId=" + emailId + "]";
	}



	public ClientMentorDetails(@NotEmpty(message = "Client Mentor name cannot be empty") String clientMentorName,
			@NotEmpty(message = "Designation Cannot Be Empty") String designation,
			@Range(min = 6000000000l, max = 9999999999l, message = "Please Enter The Correct Contact  Number") long contactNo,
			@Pattern(regexp = "[a-zA-Z]+[0-9]*[@][a-z]*[.][a-z]{2,3}", message = "Enter the correct email") String emailId) {
		super();
		this.clientMentorName = clientMentorName;
		this.designation = designation;
		this.contactNo = contactNo;
		this.emailId = emailId;
	}
	
	
	
	
	
	

}
