package com.dogmall.demo.payinfo;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PayinfoService {
	private final PayinfoMapper payinfoMapper;

}
