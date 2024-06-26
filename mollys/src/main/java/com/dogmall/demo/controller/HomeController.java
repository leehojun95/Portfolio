package com.dogmall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class HomeController {

//	@RequestBody
	@GetMapping("/")
	public String index() {
		log.info("기본주소.");
		return "index";
	}
}
