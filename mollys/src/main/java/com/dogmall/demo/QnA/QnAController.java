package com.dogmall.demo.QnA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.DTO.PageDTO;
import com.dogmall.demo.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/qna/*")
@RequiredArgsConstructor
public class QnAController {
	
	private final QnAService qnaservice;

	@GetMapping("/qnalist/{pro_num}.{page}")
	public ResponseEntity<Map<String, Object>> qnalist(@PathVariable("pro_num") int pro_num, @PathVariable("page") int page) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<>();
		
		Criteria cri = new Criteria();
		cri.setAmount(5);
		cri.setPageNum(page);
		
		List<QnAVO> qnalist = qnaservice.qna_list(pro_num, cri);
		
		int qnacount = qnaservice.getCountQnaByPro_num(pro_num);
		PageDTO pageMaker = new PageDTO(cri, qnacount);
		
		map.put("qnalist", qnalist);
		map.put("pageMaer", pageMaker);
		
		entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		
		return entity;
	}
	
	@PostMapping(value = "/qna_save", consumes = {"application/json"}, produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> qna_save(@RequestBody QnAVO vo, HttpSession session) throws Exception{
		
		String mbl_id =((MemberVO) session.getAttribute("login_status")).getMbl_id();
		vo.setMbl_id(mbl_id);
		
		qnaservice.qna_save(vo);
		
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
}








