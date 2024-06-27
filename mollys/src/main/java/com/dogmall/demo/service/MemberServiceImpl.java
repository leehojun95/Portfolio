package com.dogmall.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dogmall.demo.domain.MemberVO;
import com.dogmall.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper memberMapper;
    
    private final PasswordEncoder passwordEncoder;

    @Override
    public String idCheck(String mbl_id) {
        return memberMapper.idCheck(mbl_id);
    }

	@Override
	public void join(MemberVO vo) {
		
		vo.setMbl_password(passwordEncoder.encode(vo.getMbl_password()));
		
		memberMapper.join(vo);
		
	}

	@Override
	public MemberVO login(String mbl_id) {
		// TODO Auto-generated method stub
		return memberMapper.login(mbl_id);
	}

	@Override
	public void modify(MemberVO vo) {
		// TODO Auto-generated method stub
		memberMapper.modify(vo);
		
		
	}

	@Override
	public String idfind(String mbl_name, String mbl_email) {
		// TODO Auto-generated method stub
		return memberMapper.idfind(mbl_name, mbl_email);
	}

	@Override
	public String pwfind(String mbl_id, String mbl_name, String mbl_email) {
		// TODO Auto-generated method stub
		return memberMapper.pwfind(mbl_id, mbl_name, mbl_email);
	}

	@Override
	public void tempPwUpdate(String mbl_id, String temp_enc_pw) {
		memberMapper.tempPwUpdate(mbl_id, temp_enc_pw);
		
	}

	@Override
	public void changePw(String mbl_id, String new_mbl_password) {
		memberMapper.changePw(mbl_id, new_mbl_password);
		
	}

	@Override
	public void delete(String mbl_id) {
		memberMapper.delete(mbl_id);
		
	}








}