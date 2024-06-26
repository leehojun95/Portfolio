package com.dogmall.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/product/*")
@Controller
public class ProductController {

	private final ProductService productService;
	
	@GetMapping("/view")
	public void view() {
		
	}
	
	@PostMapping("/view")
	public String viewOK() {
		
		
		// 장바구니 and 구매하기
		return "redirect:";
	}
}
