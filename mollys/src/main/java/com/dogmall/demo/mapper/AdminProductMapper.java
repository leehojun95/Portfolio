package com.dogmall.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.AdminProductDTO;
import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.domain.AdminProductVO;

public interface AdminProductMapper {

	void pro_insert(AdminProductVO vo);

	List<AdminProductVO> pro_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	AdminProductVO pro_edit(Integer pro_num);
	
	void pro_edit_ok(AdminProductVO vo);
	
	void pro_delete(Integer pro_num);
	
	void pro_checked_modify1(@Param("pro_num") Integer pro_num, @Param("pro_price") Integer pro_price, @Param("pro_buy") String pro_buy);

	void pro_checked_modify2(List<AdminProductDTO> pro_modify_list);
}
