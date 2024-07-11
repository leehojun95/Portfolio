package com.dogmall.demo.member;

import org.apache.ibatis.annotations.Param;

public interface MemberMapper {

	String idCheck(String mbl_id);
	
	void join(MemberVO vo);
	
	MemberVO login(String mbl_id);
	
	void modify(MemberVO vo);
	
	String idfind(@Param("mbl_name") String mbl_name, @Param("mbl_email") String mbl_email);
	
	String pwfind(@Param("mbl_id") String mbl_id, @Param("mbl_name") String mbl_name, @Param("mbl_email") String mbl_email);

	void tempPwUpdate(@Param("mbl_id") String mbl_id, @Param("temp_enc_pw") String temp_enc_pw);
	
	void changePw(@Param("mbl_id") String mbl_id, @Param("new_mbl_password") String new_mbl_password);
	
	void delete(String mbl_id);
	
	String existsUserInfo(String sns_email);
		
	String sns_user_check(String sns_email);
	
	void sns_user_insert(SNSUserDto dto);
}
