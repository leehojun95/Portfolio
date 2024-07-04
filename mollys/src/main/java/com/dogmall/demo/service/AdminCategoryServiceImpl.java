package com.dogmall.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dogmall.demo.domain.AdminCategoryVO;
import com.dogmall.demo.mapper.AdminCategoryMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminCategoryServiceImpl implements AdminCategoryService {
	
	private final AdminCategoryMapper adminCategoryMapper;

	@Override
	public List<AdminCategoryVO> getFirstCategoryList() {
		// TODO Auto-generated method stub
		return adminCategoryMapper.getFirstCategoryList();
	}

	@Override
	public List<AdminCategoryVO> getSecondCategoryList(int ctg_l_code) {
		// TODO Auto-generated method stub
		return adminCategoryMapper.getSecondCategoryList(ctg_l_code);
	}

	@Override
	public AdminCategoryVO getFirstCategoryBySecondCategory(int ctg_h_code) {
		// TODO Auto-generated method stub
		return adminCategoryMapper.getFirstCategoryBySecondCategory(ctg_h_code);
	}

}
