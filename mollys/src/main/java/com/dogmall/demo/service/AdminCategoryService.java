package com.dogmall.demo.service;

import java.util.List;

import com.dogmall.demo.domain.AdminCategoryVO;

public interface AdminCategoryService {
	
	List<AdminCategoryVO> getFirstCategoryList();
	
	List<AdminCategoryVO> getSecondCategoryList(int ctg_l_code);
	
	AdminCategoryVO getFirstCategoryBySecondCategory (int ctg_h_code);

}
