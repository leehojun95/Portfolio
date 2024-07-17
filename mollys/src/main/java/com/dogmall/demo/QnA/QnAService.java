package com.dogmall.demo.QnA;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.review.ReviewVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class QnAService {
	
	private final QnAMapper qnaMapper;
	
	public List<QnAVO> qna_list(Integer pro_num, Criteria cri){
		return qnaMapper.qna_list(pro_num, cri);
	}
	
	public int getCountQnaByPro_num(Integer pro_num) {
		return qnaMapper.getCountQnaByPro_num(pro_num);
	}

	public void qna_save(QnAVO vo) {
		qnaMapper.qna_save(null);
	}

}
