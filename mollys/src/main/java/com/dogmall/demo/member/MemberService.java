package com.dogmall.demo.member;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberMapper memberMapper;
    
    private final PasswordEncoder passwordEncoder;

    
    public String idCheck(String mbl_id) {
        return memberMapper.idCheck(mbl_id);
    }

	
	public void join(MemberVO vo) {
		
		vo.setMbl_password(passwordEncoder.encode(vo.getMbl_password()));
		
		memberMapper.join(vo);
		
	}

	
	public MemberVO login(String mbl_id) {
		// TODO Auto-generated method stub
		return memberMapper.login(mbl_id);
	}

	
	public void modify(MemberVO vo) {
		// TODO Auto-generated method stub
		memberMapper.modify(vo);
		
		
	}

	
	public String idfind(String mbl_name, String mbl_email) {
		// TODO Auto-generated method stub
		return memberMapper.idfind(mbl_name, mbl_email);
	}

	
	public String pwfind(String mbl_id, String mbl_name, String mbl_email) {
		// TODO Auto-generated method stub
		return memberMapper.pwfind(mbl_id, mbl_name, mbl_email);
	}

	
	public void tempPwUpdate(String mbl_id, String temp_enc_pw) {
		memberMapper.tempPwUpdate(mbl_id, temp_enc_pw);
		
	}

	
	public void changePw(String mbl_id, String new_mbl_password) {
		memberMapper.changePw(mbl_id, new_mbl_password);
		
	}

	
	public void delete(String mbl_id) {
		memberMapper.delete(mbl_id);
		
	}

	
	public String existsUserInfo(String sns_email) {
		return memberMapper.existsUserInfo(sns_email);
	}

	
	public String sns_user_check(String sns_email) {
		return memberMapper.sns_user_check(sns_email);
	}

	
	public void sns_user_insert(SNSUserDto dto) {
		memberMapper.sns_user_insert(dto);
		
	}








}