package com.actop.evc.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.actop.evc.model.UserOcpp;
import com.actop.evc.repository.UserOcppRepository;


// 시큐리티 설정에서 loginProcessingUrl("/login")
// /login 요청이 오염 자동으로 UserDetailsService 타입으로 Ioc 되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipaDetailsService implements UserDetailsService{

	@Autowired
	private UserOcppRepository userOcppRepository;
	
	// Security Session (내부 Authentication(내부 UserDetails))
	// 함수종료시 @AuthenticationPrincipal 어노테이션이 만들어 진다.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserOcpp userEntity = userOcppRepository.findByUsername(username);
		if(userEntity != null) {
			return new PrincipalDetails(userEntity);
		}
		return null;
	}

}
