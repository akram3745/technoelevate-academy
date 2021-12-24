package com.technoelevate.academy.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Form implements Serializable {
	
	private int formId;

	private String name;

	private String branch;

	private long contactNo;

	private String email;
	
	private int batchId;

	private String status;

	private String degree;

	private String stream;

	private int passoutYear;

	private double tenthPercentage;

	private double twelvethPercentage;

	private double degreeAggregate;

	private double masterAggregate;

	@Override
	public String toString() {
		return "Form [formId=" + formId + ", name=" + name + ", branch=" + branch + ", contactNo=" + contactNo
				+ ", email=" + email + ", status=" + status + ", degree=" + degree + ", stream=" + stream
				+ ", passoutYear=" + passoutYear + ", tenthPercentage=" + tenthPercentage + ", twelvethPercentage="
				+ twelvethPercentage + ", degreeAggregate=" + degreeAggregate + ", masterAggregate=" + masterAggregate
				+ "]";
	}

	

}
