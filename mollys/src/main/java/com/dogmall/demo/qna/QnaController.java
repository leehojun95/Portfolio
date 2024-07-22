package com.dogmall.demo.qna;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.DTO.PageDTO;
import com.dogmall.demo.admin.AdminVO;
import com.dogmall.demo.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/qna/*")
public class QnaController {
	
	private final QnaService qnaService;
	
	@GetMapping("/qnalist/{pro_num}/{page}")
	public ResponseEntity<Map<String, Object>> qnalist(@PathVariable("pro_num") int pro_num, @PathVariable("page") int page) throws Exception{
		ResponseEntity<Map<String, Object>> entity = null;
		Map<String, Object> map = new HashMap<>();
		
		Criteria cri = new Criteria();
		cri.setAmount(5);
		cri.setPageNum(page);
		
		List<QnaVO> qnalist = qnaService.qna_list(pro_num, cri);
		
		int qnacount = qnaService.getCountQnaByPro_num(pro_num);
		PageDTO pageMaker = new PageDTO(cri, qnacount);
		
		map.put("qnalist", qnalist);
		map.put("pageMaker", pageMaker);
		
		entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
		
		return entity;
	}
	
	@PostMapping(value = "/qna_save", consumes = {"application/json"}, produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> qna_save(@RequestBody QnaVO vo, HttpSession session) throws Exception{
	
		String mbl_id = ((MemberVO) session.getAttribute("login_status")).getMbl_id();
		vo.setMbl_id(mbl_id);
		
		if(vo.getAns_check() == null) {
			vo.setAns_check("답변보류");
		}
		
		qnaService.qna_save(vo);
		
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}

	
	@GetMapping("/qna_modify/{qna_code}")
	public ResponseEntity<QnaVO> qna_modify(@PathVariable("qna_code") Long qna_code) throws Exception{
		
		ResponseEntity<QnaVO> entity =null;
		
		entity = new ResponseEntity<QnaVO>(qnaService.qna_modify(qna_code), HttpStatus.OK);
		
		return entity;
	}
	
	@PutMapping("/qna_modify")
	public ResponseEntity<String> qna_modify(@RequestBody QnaVO vo) throws Exception {
		
		ResponseEntity<String> entity = null;
		
		qnaService.qna_update(vo);
		
		entity = new ResponseEntity<String> ("success", HttpStatus.OK);
		
		return entity;
	}
	
	
	
	@DeleteMapping("/qna_delete/{qna_code}")
	public ResponseEntity<String> qna_delete(@PathVariable("qna_code") Long qna_code) throws Exception{
		
		log.info("장바구니코드:" + qna_code);
		ResponseEntity<String> entity = null;
		qnaService.qna_delete(qna_code);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}

}
