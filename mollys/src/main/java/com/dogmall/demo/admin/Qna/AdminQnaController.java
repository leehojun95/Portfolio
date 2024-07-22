package com.dogmall.demo.admin.Qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.DTO.PageDTO;
import com.dogmall.demo.Order.OrderVO;
import com.dogmall.demo.admin.order.OrderDetailInfoVo;
import com.dogmall.demo.payinfo.PayinfoVO;
import com.dogmall.demo.qna.QnaVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/qna/*")
public class AdminQnaController {
	
	private final AdminQnaService adminQnaService;
	
	@GetMapping("/qna_list")
	public void qna_list(Criteria cri, Model model) throws Exception {
		
		cri.setAmount(5);
		List<QnaVO> qna_list = adminQnaService.qna_list(cri);
		
		int totalCount = adminQnaService.getTotalCount(cri);
		
		model.addAttribute("qna_list", qna_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
	
	@GetMapping("/order_detail_info")
	public ResponseEntity<Map<String, Object>> order_detail_info(Long ord_code, Model model) throws Exception{
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> map = new HashMap<>();
		
		QnaVO vo = adminQnaService.qna_info(ord_code);
		
		map.put("qna_basic", vo);

		entity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
		return entity;
	}

}
