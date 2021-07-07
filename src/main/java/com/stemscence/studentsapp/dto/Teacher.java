package com.stemscence.studentsapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class Teacher{

	@JsonProperty("country")
	private String country;

	@JsonProperty("firstName")
	private String firstName;

	@JsonProperty("secondName")
	private String  secondName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("phone")
	private String phone;

	@JsonProperty("subjects")
	private List<String> subjectIds;

	@JsonProperty("Elcs")
	private List<String> elcIds;
//key as the lesson id and value as the subject
	@JsonProperty("lessons")
	private Map<String,String> lessonIds;

	@Id
	@JsonProperty("id")
	private String id;

	@JsonProperty("email")
	private String email;
	@JsonProperty("visibility")
	private String visibility;
	//subject as key and list of elcs as value;
	@JsonProperty("approvals")
	private Map<String, List<String>> approvals;
	private String phone_number;
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

}