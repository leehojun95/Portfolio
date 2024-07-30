package com.dogmall.demo.admin.staticanalysis;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/staticanalysis/*")
public class StaticAnalysisController {
	
	private final StaticAnalysisService staticAnalysisService;

	@GetMapping("/orderStats")
	public void getOrderStatus(Model model) {
		
		LocalDate now = LocalDate.now();
		int year = now.getYear();
		int month = now.getMonthValue();
		
		model.addAttribute("year", year);
		model.addAttribute("month", month);
	}
	
	
	@GetMapping("/monthlySalesStatusByTopCategory")
	@ResponseBody
	public List<Map<String, Object>> getMonthlySalesStatusByTopCategory(int year, int month) {
		
		String ord_date = String.format("%s/%s", year, (month < 10 ? "0" + String.valueOf(month) : month));
		log.info("선택일 : " + ord_date);
		
		List<Map<String, Object>> listObjMap = staticAnalysisService.monthlySalesStatusByTopCategory(ord_date);
		
		return listObjMap;
	}
}

