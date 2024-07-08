package com.dogmall.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.DTO.PageDTO;
import com.dogmall.demo.domain.AdminProductVO;
import com.dogmall.demo.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/product/*")
@RequiredArgsConstructor
@Controller
public class ProductController {
	
	private final ProductService productService;
	
	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	@Value("${file.ckdir}")
	private String uploadCKPath;

	@GetMapping("/pro_list")
	public void pro_list(@ModelAttribute("ctg_l_code") int ctg_l_code, @ModelAttribute("ctg_name") String ctg_name, Criteria cri, Model model) throws Exception {
		
		cri.setAmount(9);
		
		List<AdminProductVO> pro_list =  productService.pro_list(ctg_l_code, cri);
		
		pro_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		int totalCount =  productService.getCountProductByCategory(ctg_l_code);
		
		model.addAttribute("pro_list", pro_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
}
