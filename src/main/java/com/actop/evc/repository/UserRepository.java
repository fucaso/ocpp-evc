package com.actop.evc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.actop.evc.model.User;


// DAO
// 자동으로 bean 등록이 된다.
//@Repository // 생략이 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{

	@Modifying
	@Query(value="INSERT INTO user(ocpp_tag_pk, first_name, sex) VALUES(?1, ?2,?3)", nativeQuery = true)
	int  mSave(int ocpp_tag_pk, String first_name, String sex);
}

//JPA Naming 쿼리
// select * from user where username = ? AND password = ?;
//User findByUsernameAndPassword(String username, String password);

/*
 * @Query(value="select * from user where username = ? AND password = ?",
 * nativeQuery = true) User login(String username, String password);
 */