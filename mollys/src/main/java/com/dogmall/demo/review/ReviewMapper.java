package com.dogmall.demo.review;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.Criteria;

public interface ReviewMapper {

	List<ReviewVO> rev_list(@Param("pro_num") Integer pro_num, @Param("cri") Criteria cri);
	
	int getCountReviewByPro_num(Integer pro_num);
	
	void review_save(ReviewVO vo);
	
	void revie_delete(Long rev_code);
	
	ReviewVO review_modify(Long rev_code);
	
	void review_update(ReviewVO vo);
}
