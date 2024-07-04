package com.dogmall.demo.service;

import java.util.List;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.domain.AdminProductVO;

public interface AdminProductService {
	
	void pro_insert(AdminProductVO vo);
	
	List<AdminProductVO> pro_list(Criteria cri);
	
	int getTotalCount(Criteria cri);

}
