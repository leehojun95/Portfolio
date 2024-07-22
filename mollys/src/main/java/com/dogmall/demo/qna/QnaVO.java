package com.dogmall.demo.qna;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

import lombok.ToString;
@Getter
@Setter
@ToString
public class QnaVO {

	// qna_code, mbl_id, pro_num, qna_title, qna_content, qna_date, ans_content, ans_check, ans_date, admin_id
	
	private Long qna_code;
	private String mbl_id;
	private int pro_num;
	private String qna_title;
	private String qna_content;
	private Date qna_date;
	private String ans_content;
	private String ans_check;
	private Date ans_date;
	private String admin_id;
}
