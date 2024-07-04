package com.dogmall.demo.service;

import org.springframework.stereotype.Service;

import com.dogmall.demo.domain.AdminVO;
import com.dogmall.demo.mapper.AdminMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminServiceImpl implements AdminService {
	
	private final AdminMapper adminMapper;

	@Override
	public AdminVO loginOk(String admin_id) {
		// TODO Auto-generated method stub
		return adminMapper.loginOk(admin_id);
	}

}
