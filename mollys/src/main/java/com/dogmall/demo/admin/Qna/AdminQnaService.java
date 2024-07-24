package com.dogmall.demo.admin.Qna;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.qna.QnaDetailInfoVO;
import com.dogmall.demo.qna.QnaVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminQnaService {
	
	private final AdminQnaMapper adminQnaMapper;
	
	public List<QnaVO> qna_list(Criteria cri, String start_date, String end_date){
		return adminQnaMapper.qna_list(cri, start_date, end_date);
	}
	
	public int getTotalCount(Criteria cri, String start_date, String end_date) {
		return adminQnaMapper.getTotalCount(cri, start_date, end_date);
	}
	
	public QnaVO qna_info (Long qna_code) {
		return adminQnaMapper.qna_info(qna_code);
	}
	
	public List<QnaVO> qna_detail_info(Long qna_code){
		return adminQnaMapper.qna_detail_info(qna_code);
	}
	
	public void qna_basic_modify(QnaVO vo) {
		adminQnaMapper.qna_basic_modify(vo);
	}

}
