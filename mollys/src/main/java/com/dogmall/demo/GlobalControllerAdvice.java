package com.dogmall.demo;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.dogmall.demo.admin.category.AdminCategoryService;
import com.dogmall.demo.admin.category.AdminCategoryVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@ControllerAdvice(basePackages = {"com.dogmall.demo.product", "com.dogmall.demo.board"})
public class GlobalControllerAdvice {

	private final AdminCategoryService adminCategoryService;
	
	@ModelAttribute
	public void comm_test(Model model) {
	    List<AdminCategoryVO> cate_list = adminCategoryService.getFirstCategoryList();
	    model.addAttribute("user_cate_list", cate_list);
	}

}
