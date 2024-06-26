package com.dogmall.demo.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// mbl_id, mbl_password, mbl_email, mbl_name, mbl_brith, mbl_zip_code, mbl_addr, mbl_addr_detail, mbl_phone, mbl_gender, mbl_point, mbl_regdate, mbl_updatedate
@Getter
@Setter
@ToString
public class MemberVO {

	private String mbl_id;
	private String mbl_password;
	private String mbl_email;
	private String mbl_name;
	private String mbl_brith;
	private String mbl_zip_code;
	private String mbl_addr;
	private String mbl_addr_detail;
	private String mbl_phone;
	private String mbl_gender;
	private int    mbl_point;
	private Date   mbl_regdate;
	private Date   mbl_updatedate;
}
