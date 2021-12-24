package com.technoelevate.academy.dto;

import static com.technoelevate.academy.message.Message.*;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
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

@SuppressWarnings({ "serial", "unused" })
@Setter
@Getter
@Transactional
@Entity
@AllArgsConstructor
@Validated
@NoArgsConstructor
public class CandidatesDetails implements Serializable {
	
	@Id
	@SequenceGenerator(name = "candidate_sequence_generator", initialValue = 100, allocationSize = 1)
	@GeneratedValue(generator = "candidate_sequence_generator")
	private int candidateId;
	
	@NotEmpty(message = CANDIDATE_NAME_CANNOT_BE_EMPTY)
	private String name;

	@NotEmpty(message =BRANCH_NAME_CANNOT_BE_EMPTY)
	private String branch;

	@Range(min = 6000000000l, max = 9999999999l, message = ENTER_THE_CORRECT_NUMBER)
	private long contactNo;
	

	@Pattern(regexp = "[a-zA-Z]+[0-9]*[@][a-z]*[.][a-z]{2,3}",message = ENTER_THE_CORRECT_EMAIL)
	private String email;

	@NotEmpty(message = DEGREE_CANNOT_BE_EMPTY)
	private String degree;

	@NotEmpty(message = STREAM_CANNOT_BE_EMPTY)
	private String stream;

	@Range(min = 2000, max = 2099, message = ENTER_THE_CORRECT_PASSOUT_YEAR)
	private int passoutYear;

	@Digits(integer = 2, fraction = 2, message = ENTER_THE_CORRECT_PERCENTAGE)
	private double tenthPercentage;

	@Digits(integer = 2, fraction = 2, message = ENTER_THE_CORRECT_PERCENTAGE)
	private double twelvethPercentage;

	@Digits(integer = 2, fraction = 2, message = ENTER_THE_CORRECT_PERCENTAGE)
	private double degreeAggregate;

	@Digits(integer = 2, fraction = 2, message = ENTER_THE_CORRECT_PERCENTAGE)
	private double masterAggregate;
	
	
	
	
	@Override
	public String toString() {
		return "Candidates [candidateId=" + candidateId + ", name=" + name + ", branch=" + branch + ", contactNo="
				+ contactNo + ", email=" + email + ", degree=" + degree + ", stream=" + stream + ", passoutYear="
				+ passoutYear + ", tenthPercentage=" + tenthPercentage + ", twelvethPercentage=" + twelvethPercentage
				+ ", degreeAggregate=" + degreeAggregate + ", masterAggregate=" + masterAggregate + "]";
	}




	public CandidatesDetails(@NotEmpty(message = "Candidate name cannot be Empty ") String name,
			@NotEmpty(message = "Branch name cannot be empty") String branch,
			@Range(min = 6000000000l, max = 9999999999l, message = "Please Enter The Correct Contact  Number") long contactNo,
			@Pattern(regexp = "[a-zA-Z]+[0-9]*[@][a-z]*[.][a-z]{2,3}", message = "Enter the correct email") String email,
			@NotEmpty(message = "Degree cannot be Empty") String degree,
			@NotEmpty(message = "Stream cannot be Empty") String stream,
			@Range(min = 2000, max = 2099, message = "Enter the correct passout year") int passoutYear,
			@Digits(integer = 2, fraction = 2, message = "Enter the correct percentage") double tenthPercentage,
			@Digits(integer = 2, fraction = 2, message = "Enter the correct percentage") double twelvethPercentage,
			@Digits(integer = 2, fraction = 2, message = "Enter the correct percentage") double degreeAggregate,
			@Digits(integer = 2, fraction = 2, message = "Enter the correct percentage") double masterAggregate) {
		super();
		this.name = name;
		this.branch = branch;
		this.contactNo = contactNo;
		this.email = email;
		this.degree = degree;
		this.stream = stream;
		this.passoutYear = passoutYear;
		this.tenthPercentage = tenthPercentage;
		this.twelvethPercentage = twelvethPercentage;
		this.degreeAggregate = degreeAggregate;
		this.masterAggregate = masterAggregate;
	}
	
	
	

}
