package com.actop.evc.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.actop.evc.model.Ocpp_Tag;


public interface OcppTagRepository extends JpaRepository<Ocpp_Tag, Integer> {
	
	Ocpp_Tag findByTagid(String Id_tag);
	
	//@Query(value="select * from ocpp_tag where id_tag = ?1", nativeQuery = true)
	//Ocpp_Tag findByIdtag(String Id_tag);
}
