package com.dogmall.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class KakaoLoginVO {

	private Long id;
	private String nickname;
	private String email;
}
