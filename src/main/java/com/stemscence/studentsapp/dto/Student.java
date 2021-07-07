package com.stemscence.studentsapp.dto;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Student{

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("gender")
	private String gender;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("subjects")
	private List<String> subjects;

	@JsonProperty("Elcs")
	private Map<String,List<String>> elcs;

	@JsonProperty("id")
	private String id;

	@JsonProperty("email")
	private String email;

	@JsonProperty("dateOfBirth")
	private String dateOfBirth;

	@JsonProperty("secondName")
	private String secondName;

	@JsonProperty("createdBy")
	private String createdBy;

	@JsonProperty("startDate")
	private String startDate;
	private String role;
	private Date email_verified_at;
	private  String school_system_id;
	private boolean activation_status;
	private String verification_code;
	private Date last_login_at;
	private String referral_code;
	private Date last_logout_at;
	private String otp_verified;
	private String dob;
	private Date updated_at;
	private Date created_at;
	private String parent_phone;
	private String parent_email;

}