package com.dogmall.demo.service;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.SNSUserDto;
import com.dogmall.demo.domain.MemberVO;

public interface MemberService {

	String idCheck(String mbl_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String mbl_id);
	
	void modify(MemberVO vo);
	
	String idfind(String mbl_name, String mbl_email);
	
	String pwfind(String mbl_id, String mbl_name,  String mbl_email);

	void tempPwUpdate(String mbl_id,String temp_enc_pw);

	void changePw(String mbl_id,String new_mbl_password);
	
	void delete(String mbl_id);
	
	String existsUserInfo(String sns_email);
	
	String sns_user_check(String sns_email);
	
	void sns_user_insert(SNSUserDto dto);
	
}
