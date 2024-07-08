package com.dogmall.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.domain.AdminProductVO;
import com.dogmall.demo.mapper.ProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
	
	private final ProductMapper productMapper;

	@Override
	public List<AdminProductVO> pro_list(int ctg_l_code, Criteria cri) {

		return productMapper.pro_list(ctg_l_code, cri);
	}

	@Override
	public int getCountProductByCategory(int ctg_l_code) {
		
		return productMapper.getCountProductByCategory(ctg_l_code);
	}

}
