package com.bubblebear.backendProject.entity;

import java.sql.Date;
import java.util.List;

import com.bubblebear.backendProject.entity.limits.UserFieldLimits;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "users")
public class User implements UserFieldLimits {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "fullname", nullable = false, length = FULLNAME_DB_LENGTH)
	private String fullname;
	
	@Column(name = "email", nullable = false, length = EMAIL_DB_LENGTH, unique = true)
	private String email;
	
	@Column(name = "password", nullable = false, length = PASSWORD_DB_LENGTH)
	private String password;
	
	@Column(name = "birthday" ,columnDefinition="DATE")
	private Date birthday;
	
	@Column(name = "phone_number", length = PHONE_NUMBER_DB_LENGTH)
	private String phone_number;
	
	@Column(name = "role", columnDefinition = "INT default 0")
	private int role;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.DETACH,fetch = FetchType.EAGER)
	@JsonIgnoreProperties("user")
	private List<Orders> orders;
	


	/*
	@OneToMany(mappedBy = "user")  
	@JsonIgnoreProperties("user")
	private List<Orders> orders = new ArrayList<>(); */


	
	
}
