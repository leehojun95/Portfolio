package com.dogmall.demo.admin.Qna;

import java.util.List;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.qna.QnaVO;

public interface AdminQnaMapper {

	List<QnaVO> qna_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	QnaVO qna_info (Long qna_code);
}
