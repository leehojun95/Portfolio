package com.dogmall.demo.qna;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.Criteria;

public interface QnaMapper {

	List<QnaVO> qna_list(@Param("pro_num") Integer pro_num, @Param("cri") Criteria cri);
	
	int getCountQnaByPro_num(Integer pro_num);
	
	void qna_save(QnaVO vo);
	
//	void admin_qna_save(QnaVO vo);
	
	void qna_delete(Long qna_code);
	
	QnaVO qna_modify(Long qna_code);
	
	void qna_update(QnaVO vo);
}
