package com.dogmall.demo.review;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReviewVO {

	private Long rev_code;
	private String mbl_id;
	private int pro_num;
	private String rev_title;
	private String rev_content;
	private int rev_rate;
	private Date rev_date;
}
