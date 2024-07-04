package com.dogmall.demo.mapper;

import java.util.List;

import com.dogmall.demo.domain.AdminCategoryVO;

public interface AdminCategoryMapper {

	List<AdminCategoryVO> getFirstCategoryList();
	
	List<AdminCategoryVO> getSecondCategoryList(int ctg_l_code);
	
	AdminCategoryVO getFirstCategoryBySecondCategory (int ctg_h_code);
}
