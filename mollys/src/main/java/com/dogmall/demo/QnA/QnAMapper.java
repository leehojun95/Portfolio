package com.dogmall.demo.QnA;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.review.ReviewVO;

public interface QnAMapper {
	
	List<QnAVO> qna_list(@Param("pro_num") Integer pro_num, @Param("cri") Criteria cri);
	
	int getCountQnaByPro_num(Integer pro_num);
	
	void qna_save(QnAVO vo);

}
