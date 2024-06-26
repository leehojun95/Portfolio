package com.dogmall.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dogmall.demo.domain.MemberVO;
import com.dogmall.demo.mapper.MemberMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberMapper mollysMapper;
    
    private final PasswordEncoder passwordEncoder;

    @Override
    public String idCheck(String mbl_id) {
        return mollysMapper.idCheck(mbl_id);
    }

	@Override
	public void join(MemberVO vo) {
		
		vo.setMbl_password(passwordEncoder.encode(vo.getMbl_password()));
		
		mollysMapper.join(vo);
		
	}

	@Override
	public MemberVO login(String mbl_id) {
		// TODO Auto-generated method stub
		return mollysMapper.login(mbl_id);
	}

	@Override
	public void modify(MemberVO vo) {
		// TODO Auto-generated method stub
		mollysMapper.modify(vo);
		
		
	}

	@Override
	public String idfind(String mbl_name, String mbl_email) {
		// TODO Auto-generated method stub
		return mollysMapper.idfind(mbl_name, mbl_email);
	}

	@Override
	public String pwfind(String mbl_id, String mbl_name, String mbl_email) {
		// TODO Auto-generated method stub
		return mollysMapper.pwfind(mbl_id, mbl_name, mbl_email);
	}

	@Override
	public void tempPwUpdate(String mbl_id, String temp_enc_pw) {
		mollysMapper.tempPwUpdate(mbl_id, temp_enc_pw);
		
	}

	@Override
	public void changePw(String mbl_id, String new_mbl_password) {
		mollysMapper.changePw(mbl_id, new_mbl_password);
		
	}

	@Override
	public void delete(String mbl_id) {
		mollysMapper.delete(mbl_id);
		
	}








}