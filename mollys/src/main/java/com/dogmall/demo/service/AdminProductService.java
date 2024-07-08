package com.dogmall.demo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.AdminProductDTO;
import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.domain.AdminProductVO;

public interface AdminProductService {
	
	void pro_insert(AdminProductVO vo);
	
	List<AdminProductVO> pro_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	AdminProductVO pro_edit(Integer pro_num);
	
	void pro_edit_ok(AdminProductVO vo);
	
	void pro_delete(Integer pro_num);
	
	void pro_checked_modify1(List<Integer> pro_num_arr, List<Integer> pro_price_arr, List<String> pro_buy_arr);

	void pro_checked_modify2(List<Integer> pro_num_arr, List<Integer> pro_price_arr, List<String> pro_buy_arr);
}
