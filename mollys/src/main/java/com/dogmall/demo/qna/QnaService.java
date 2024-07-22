package com.dogmall.demo.qna;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class QnaService {
	
	private final QnaMapper qnaMapper;

	public List<QnaVO> qna_list(Integer pro_num,Criteria cri){
		return qnaMapper.qna_list(pro_num, cri);
	}
	
	public int getCountQnaByPro_num(Integer pro_num) {
		return qnaMapper.getCountQnaByPro_num(pro_num);
	}
	
	public void qna_save(QnaVO vo) {
		qnaMapper.qna_save(vo);
	}
	
//	public void admin_qna_save(QnaVO vo) {
//		qnaMapper.admin_qna_save(vo);
//	}
	
	public void qna_delete(Long qna_code) {
		qnaMapper.qna_delete(qna_code);
	}

	public QnaVO qna_modify(Long qna_code) {
		return qnaMapper.qna_modify(qna_code);
	}
	
	public void qna_update(QnaVO vo) {
		qnaMapper.qna_update(vo);
	}
}
