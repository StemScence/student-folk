package com.stemscence.studentsapp.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Elc {
	private String name;
	private String principalId;
	private String id;
	private String password;
	private List<String> oldPrincipals;
	//key as the subject and value as list of teacherId
	private Map<String,List<String>> subjects;
}