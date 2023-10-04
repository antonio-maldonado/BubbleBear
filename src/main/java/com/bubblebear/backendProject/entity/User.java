package com.bubblebear.backendProject.entity;



import java.sql.Date;


import com.bubblebear.backendProject.entity.limits.UserFieldLimits;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	

	public User(String fullname, String email, String password, 
			Date birthday, String phone_number, int role) {
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
		this.phone_number = phone_number;
		this.role = role;
	}

	/*
	@OneToMany(mappedBy = "user")  
	@JsonIgnoreProperties("user")
	private List<Orders> orders = new ArrayList<>(); */

	
	
	
	
}
