package com.dogmall.demo.product;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.admin.product.AdminProductVO;

public interface ProductMapper {

	List<AdminProductVO> pro_list(@Param("ctg_h_code") int ctg_h_code, @Param("cri") Criteria cri);
	
	int getCountProductByCategory(int ctg_h_code);
	
	AdminProductVO pro_info(int pro_num);
}
