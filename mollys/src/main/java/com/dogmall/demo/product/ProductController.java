package com.dogmall.demo.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.DTO.PageDTO;
import com.dogmall.demo.admin.product.AdminProductVO;
import com.dogmall.demo.util.FileManagerUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/product/*")
@Slf4j
@Controller
public class ProductController {

	private final ProductService productService;
	
	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	
	@Value("${file.ckdir}")
	private String uploadCKPath;

	@GetMapping("/pro_list")
	public void pro_list(@ModelAttribute("ctg_h_code") int ctg_h_code, @ModelAttribute("ctg_name") String ctg_name, Criteria cri, Model model) throws Exception{
		
		cri.setAmount(8);
		
		log.info("2차카테고리코드 " + ctg_h_code);
		log.info("2차카테고리이름 " + ctg_name);
		
		List<AdminProductVO> pro_list = productService.pro_list(ctg_h_code, cri);
		
		pro_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		int toatalCount = productService.getCountProductByCategory(ctg_h_code);
		
		model.addAttribute("pro_list", pro_list);
		model.addAttribute("pageMaker", new PageDTO(cri, toatalCount));
	}
	
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception{
		
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	

	@GetMapping("pro_info_2")
	public void pro_info_2(int pro_num, Model model) throws Exception{
		
		AdminProductVO vo = productService.pro_info(pro_num);
		vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		
		model.addAttribute("product", vo);
	}
	
	@GetMapping("pro_detail")
	public void pro_detail(int pro_num, Model model) throws Exception{
		
		AdminProductVO vo = productService.pro_info(pro_num);
		vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		
		model.addAttribute("product", vo);
	}
	
}