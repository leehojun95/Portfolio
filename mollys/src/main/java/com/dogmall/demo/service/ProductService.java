package com.dogmall.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.domain.AdminProductVO;

public interface ProductService {
	
	List<AdminProductVO> pro_list(@Param("ctg_l_code") int ctg_l_code, @Param("cri") Criteria cri);
	
	int getCountProductByCategory(int ctg_l_code);

}
