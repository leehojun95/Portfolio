package com.dogmall.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.domain.AdminProductVO;
import com.dogmall.demo.mapper.AdminProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminProductServiceImpl implements AdminProductService {
	
	private final AdminProductMapper adminProductMapper;

	@Override
	public void pro_insert(AdminProductVO vo) {
		adminProductMapper.pro_insert(vo);
		
	}

	@Override
	public List<AdminProductVO> pro_list(Criteria cri) {
		return adminProductMapper.pro_list(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return adminProductMapper.getTotalCount(cri);
	}

}
