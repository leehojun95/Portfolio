package com.dogmall.demo.admin.MBMG;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.member.MemberVO;

public interface MBMGMapper {

	List<MemberVO> mbmg_list(@Param("cri") Criteria cri, @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	int getTotalCount(@Param("cri") Criteria cri, @Param("start_date") String start_date, @Param("end_date") String end_date);

	MemberVO mbmg_edit(String mbl_id);
	
	void mbmg_edit_ok(MemberVO vo);
	
	void mbmg_delete(String mbl_id);
}
