package com.actop.evc.model;

import java.sql.Timestamp;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int user_pk;
	
	@ManyToOne //Many = Board, User = one
	@JoinColumn(name="ocpp_tag_pk")
	private Ocpp_Tag ocpp_Tag;
	
	@ManyToOne //Many = Board, User = one
	@JoinColumn(name="address_pk")
	private Address address;
	
	private String first_name;
	
	private String last_name;
	
	private LocalDate birth_day;
	
	//@Enumerated(EnumType.STRING)
	private String sex;
	
	private String phone;
	
	private String e_mail;
	
	private String note;
}
