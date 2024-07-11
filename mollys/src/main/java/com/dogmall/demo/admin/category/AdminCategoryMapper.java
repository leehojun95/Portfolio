package com.dogmall.demo.admin.category;

import java.util.List;

public interface AdminCategoryMapper {

	List<AdminCategoryVO> getFirstCategoryList();
	
	List<AdminCategoryVO> getSecondCategoryList(int ctg_l_code);
	
	AdminCategoryVO getFirstCategoryBySecondCategory (int ctg_h_code);
}
