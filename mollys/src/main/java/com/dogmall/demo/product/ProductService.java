package com.dogmall.demo.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.admin.product.AdminProductVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductService {

	private final ProductMapper productMapper;
	
	public List<AdminProductVO> pro_list(int ctg_h_code,Criteria cri) {
		return productMapper.pro_list(ctg_h_code, cri);
	}
	
	public int getCountProductByCategory(Integer ctg_h_code) {
		return productMapper.getCountProductByCategory(ctg_h_code);
	}
	
	public AdminProductVO pro_info(int pro_num) {
		return productMapper.pro_info(pro_num);
	}
}
