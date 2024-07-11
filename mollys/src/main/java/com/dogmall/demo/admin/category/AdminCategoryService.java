package com.dogmall.demo.admin.category;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminCategoryService  {
	
	private final AdminCategoryMapper adminCategoryMapper;


	public List<AdminCategoryVO> getFirstCategoryList() {
		// TODO Auto-generated method stub
		return adminCategoryMapper.getFirstCategoryList();
	}


	public List<AdminCategoryVO> getSecondCategoryList(int ctg_l_code) {
		// TODO Auto-generated method stub
		return adminCategoryMapper.getSecondCategoryList(ctg_l_code);
	}


	public AdminCategoryVO getFirstCategoryBySecondCategory(int ctg_h_code) {
		// TODO Auto-generated method stub
		return adminCategoryMapper.getFirstCategoryBySecondCategory(ctg_h_code);
	}

}
