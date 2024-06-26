package com.dogmall.demo.service;

import com.dogmall.demo.DTO.EmailDTO;

public interface EmailService {

	void sendMail(String type, EmailDTO dto, String authcode);
}
