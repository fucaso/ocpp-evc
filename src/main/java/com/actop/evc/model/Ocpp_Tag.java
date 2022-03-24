package com.actop.evc.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ocpp_Tag {
	@Id //Primary Key
	@GeneratedValue(strategy = GenerationType.IDENTITY)//프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
	private int ocpp_tag_pk; //시퀀스, auto_increment
	
	@Column(name="id_tag", nullable = false, length = 20, unique=true)
	private String tagid;
	
	private String parent_id_tag;
	
	private Timestamp expiry_date;
	
	private int max_active_transaction_count;
	
	private String note;
}
