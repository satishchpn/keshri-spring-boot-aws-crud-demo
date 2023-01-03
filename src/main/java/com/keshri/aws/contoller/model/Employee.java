package com.keshri.aws.contoller.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor

@NoArgsConstructor
public class Employee implements Serializable {

	private static final long serialVersionUID = -1685371566857861336L;

	private Integer id;

	private String firstName;

	private String lastName;

	private String emailId;
}
