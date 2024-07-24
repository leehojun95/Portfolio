package com.dogmall.demo.admin.Qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.DTO.PageDTO;
import com.dogmall.demo.Order.OrderVO;
import com.dogmall.demo.admin.order.OrderDetailInfoVo;
import com.dogmall.demo.payinfo.PayinfoVO;
import com.dogmall.demo.qna.QnaDetailInfoVO;
import com.dogmall.demo.qna.QnaVO;
import com.dogmall.demo.util.FileManagerUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/qna/*")
public class AdminQnaController {
	
	private final AdminQnaService adminQnaService;
	
	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	@GetMapping("/qna_list")
	public void qna_list(Criteria cri, @ModelAttribute("start_date") String start_date, @ModelAttribute("end_date") String end_date, Model model) throws Exception {
		
		cri.setAmount(5);
		List<QnaVO> qna_list = adminQnaService.qna_list(cri, start_date, end_date);
		
		int totalCount = adminQnaService.getTotalCount(cri, start_date, end_date);
		
		model.addAttribute("qna_list", qna_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
		

	}
	
	@GetMapping("/qna_detail_info")
	public ResponseEntity<Map<String, Object>> qna_detail_info(Long qna_code, Model model) throws Exception{
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> map = new HashMap<>();
		
		QnaVO vo = adminQnaService.qna_info(qna_code);
		map.put("qna_basic", vo);
	
		List<QnaVO> qna_list = adminQnaService.qna_detail_info(qna_code);
		
		qna_list.forEach(qna_pro ->{
			qna_pro.setPro_up_folder(qna_pro.getPro_up_folder().replace("\\", "/"));
		});
		
		map.put("qna_list", qna_list);
		

		entity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
		return entity;
	}
	
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception{
		
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	@PostMapping("/qna_basic_modify")
	public ResponseEntity<String> qna_basic_modify(QnaVO vo) throws Exception{
		
		ResponseEntity<String> entity = null;
		
		adminQnaService.qna_basic_modify(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
				
		return entity;
	}

}
