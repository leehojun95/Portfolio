package com.dogmall.demo.controller;

import javax.swing.text.html.parser.Entity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dogmall.demo.DTO.EmailDTO;
import com.dogmall.demo.service.EmailService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/email/*")
public class EmailController {
	
	private final EmailService emailService;
	
	@GetMapping("/authcode")
	public ResponseEntity<String> authcode(String type, EmailDTO dto, HttpSession session){
		
		log.info("dto :" + dto);
		
		ResponseEntity<String> entity = null;
		
		String authcode = "";
		for(int i=0; i<6; i++) {
			authcode += String.valueOf((int)(Math.random() * 10));
		}
		
		log.info("인증코드: " + authcode);
		
		session.setAttribute("authcode", authcode);

		try {
			// 메일발송.
			
			// 메일제목 작업
			if(type.equals("emailjoin")) {
				dto.setSubject("Molly's 회원가입 메일인증코드입니다.");
			}else if(type.equals("emailID")) {
				dto.setSubject("Molly's 아이디 메일인증코드입니다.");
			}else if(type.equals("emailPw")) {
				dto.setSubject("Molly's 비밀번호 메일인증코드입니다.");
			}
			
			emailService.sendMail(type, dto, authcode);
			entity = new ResponseEntity<String>("success", HttpStatus.OK); // 200
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
		}
		
		return entity;
	}
	
	@GetMapping("/confirm_authcode")
	public ResponseEntity<String> confirm_authcode(String authcode, HttpSession session) {
		
		ResponseEntity<String> entity =null;
		
		if(session.getAttribute("authcode") != null) {
			
			if(authcode.equals(session.getAttribute("authcode"))) {
				entity = new ResponseEntity<String>("success", HttpStatus.OK);
				session.removeAttribute("authcode");
			}else {
				entity = new ResponseEntity<String>("fail", HttpStatus.OK);
			}
		}else {
			entity = new ResponseEntity<String>("request", HttpStatus.OK);
		}
		
		return entity;
	}
}















