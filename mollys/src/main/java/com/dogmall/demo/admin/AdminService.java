package com.dogmall.demo.admin;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminService {
	
	private final AdminMapper adminMapper;

	
	public AdminVO loginOk(String admin_id) {
		// TODO Auto-generated method stub
		return adminMapper.loginOk(admin_id);
	}

}
