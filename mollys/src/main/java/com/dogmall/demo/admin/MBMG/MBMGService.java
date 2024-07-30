package com.dogmall.demo.admin.MBMG;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.member.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MBMGService {
	
	private final MBMGMapper mbmgMapper;
	
	public List<MemberVO> mbmg_list(Criteria cri, String start_date, String end_date){
		return mbmgMapper.mbmg_list(cri, start_date, end_date);
	}
	
	public int getTotalCount(Criteria cri, String start_date, String end_date) {
		return mbmgMapper.getTotalCount(cri, start_date, end_date);
	}
	
	public MemberVO mbmg_edit(String mbl_id) {
		return mbmgMapper.mbmg_edit(mbl_id);
	}
	
	public void mbmg_edit_ok(MemberVO vo) {
		mbmgMapper.mbmg_edit_ok(vo);
	}
	
	public void mbmg_delete(String mbl_id) {
		mbmgMapper.mbmg_delete(mbl_id);
	}
}
