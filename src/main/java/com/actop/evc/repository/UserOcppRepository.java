package com.actop.evc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.actop.evc.model.UserOcpp;


//CRUD 함수를 JpaRepository 가 들고 있음
// @Repository 라는 어노테이션이 없어도 Ioc 되요. 이유는 JpaRepository를 상속했기 때문에...
public interface UserOcppRepository extends JpaRepository<UserOcpp, Integer>{

	// findBy규칙 -> Username문법
	// select * from user where username = 1?
	public UserOcpp findByUsername(String username); // Jpa query method 
	
	// select * from user where email = 1?
	//public User findByEmail(String email);
}
