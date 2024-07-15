package com.dogmall.demo.review;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ReviewService {
	
	private final ReviewMapper reviewMapper;
	
	public List<ReviewVO> rev_list(Integer pro_num,Criteria cri){
		return reviewMapper.rev_list(pro_num, cri);
	}
	
	public int getCountReviewByPro_num(Integer pro_num) {
		return reviewMapper.getCountReviewByPro_num(pro_num);
	}
	
	public void review_save(ReviewVO vo) {
		reviewMapper.review_save(vo);
	}
	
	public void revie_delete(Long rev_code) {
		reviewMapper.revie_delete(rev_code);
	}

	public ReviewVO review_modify(Long rev_code) {
		return reviewMapper.review_modify(rev_code);
	}
	
	public void review_update(ReviewVO vo) {
		reviewMapper.review_update(vo);
	}
}
