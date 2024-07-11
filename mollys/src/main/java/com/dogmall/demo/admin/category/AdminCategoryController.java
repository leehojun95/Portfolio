package com.dogmall.demo.admin.category;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/admin/category/*")
@RequiredArgsConstructor
@Controller
public class AdminCategoryController {
	
	private final AdminCategoryService adminCategoryService;

	@GetMapping("/secondcategory/{ctg_l_code}")
	public ResponseEntity<List<AdminCategoryVO>> getSecondcategoryList(@PathVariable("ctg_l_code") int ctg_l_code) throws Exception {
		
			ResponseEntity<List<AdminCategoryVO>> entity = null;
			
			entity = new ResponseEntity<List<AdminCategoryVO>>(adminCategoryService.getSecondCategoryList(ctg_l_code), HttpStatus.OK);
			
			return entity;
	}
	 

}
