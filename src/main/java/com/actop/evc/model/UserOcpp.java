package com.actop.evc.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class UserOcpp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username;
	private String password;
	private String email;
	private String role;
	
	private String provider;
	private String providorId;
	//private Timestamp loginDate;
	@CreationTimestamp
	private Timestamp createDate;

	@Builder
	public UserOcpp(String username, String password, String email, String role, String provider, String providorId,
			Timestamp createDate) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.role = role;
		this.provider = provider;
		this.providorId = providorId;
		this.createDate = createDate;
	}
	
}
