package com.actop.evc.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.actop.evc.model.Ocpp_Tag;
import com.actop.evc.model.UserOcpp;
import com.actop.evc.repository.OcppTagRepository;
import com.actop.evc.repository.UserOcppRepository;
import com.actop.evc.repository.UserRepository;

@Controller
public class IndexController {
	
	@Autowired
	private UserOcppRepository userOcppRepository;
	
	@Autowired
	private OcppTagRepository ocppTagRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping({"","/"})
	public String index() {
		// 머스테치 기본폴더 src/main/resources/
		// 뷰리졸버 설정 : templates(prefix),.mustache(suffix) 생략가능.
		return "index"; //src/main/resources/templates/index.mustache
	}
	@GetMapping("/user")
	public @ResponseBody String user() {
		//System.out.println("principalDetails : "+ principalDetails.getUser());
		return "user";
	}
	
	@GetMapping("/admin")
	public @ResponseBody String admin() {
		return "admin";
	}
	
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "manager";
	}
	// 스프링 시큐리티가 해당주소를 낚아 채버리네요!! - SecurityConfig 파일 생성후 작동안함.
	@GetMapping("/loginForm")
	public String loginForm() {
		return "loginForm";
	}

	@GetMapping("/joinForm")
	public String joinForm() {
		return "joinForm";
	}
	
	@PostMapping("/join") //회원가입
	public String join(UserOcpp userOcpp) {
		System.out.println(userOcpp);
		userOcpp.setRole("ROLE_USER");
		String rawPasaword = userOcpp.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPasaword);
		userOcpp.setPassword(encPassword);
		userOcppRepository.save(userOcpp); //회원가입이 잘됨. 비밀번호:1234 => 시큐리티로 로그인을 할 수 없음. 패스워드 암호와 필요
		
		//회원 가입시 OCPP Tag 만들어 준다.
		LocalDateTime dt = LocalDateTime.now();
		//DateTimeFormatter format = DateTimeFormatter.ofPattern("yyMMddHHmmssSSS");
		String idTag = "A"+dt.format(DateTimeFormatter.ofPattern("yyMMddHHmmssSSS"));
		System.out.println("OCCP Tag : "+idTag);
		dt = dt.plusYears(1);
		Timestamp timestamp = Timestamp.valueOf(dt);
		System.out.println("expiry_date : "+timestamp);
		
		Ocpp_Tag tag = Ocpp_Tag.builder()
				.tagid(idTag)
				.expiry_date(timestamp)
				.parent_id_tag(null)
				.max_active_transaction_count(1)
				.build();
		
		int tag_pk = ocppTagRepository.save(tag).getOcpp_tag_pk(); //저장후 생성된 pk를 반환한다.
		System.out.println("Ocpp_Tag pk : "+tag_pk);
		
		userRepository.mSave(tag_pk, userOcpp.getUsername(), "o");
		
		return "redirect:/loginForm";
	}
}
