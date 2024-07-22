package com.dogmall.demo.admin.Qna;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.qna.QnaVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminQnaService {
	
	private final AdminQnaMapper adminQnaMapper;
	
	public List<QnaVO> qna_list(Criteria cri){
		return adminQnaMapper.qna_list(cri);
	}
	
	public int getTotalCount(Criteria cri) {
		return adminQnaMapper.getTotalCount(cri);
	}
	
	public QnaVO qna_info (Long qna_code) {
		return adminQnaMapper.qna_info(qna_code);
	}

}
