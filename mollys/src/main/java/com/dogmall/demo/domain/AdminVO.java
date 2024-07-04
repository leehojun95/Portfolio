package com.dogmall.demo.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminVO {

	//admin_id, admin_pw, admin_visit_date
	private String admin_id;
	private String admin_pw;
	private Date   admin_visit_date;
}
