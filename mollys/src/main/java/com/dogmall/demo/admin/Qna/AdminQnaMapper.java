package com.dogmall.demo.admin.Qna;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.qna.QnaDetailInfoVO;
import com.dogmall.demo.qna.QnaVO;

public interface AdminQnaMapper {

	List<QnaVO> qna_list(@Param("cri") Criteria cri, @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	int getTotalCount(@Param("cri") Criteria cri, @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	QnaVO qna_info (Long qna_code);
	
	List<QnaVO> qna_detail_info(Long qna_code);
	
	void qna_basic_modify(QnaVO vo);
}
