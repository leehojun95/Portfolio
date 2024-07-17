package com.dogmall.demo.QnA;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnAVO {


	private Long qna_code;
	private String mbl_id;
	private int pro_num;
	private String qna_title;
	private String qna_content;
	private Date   qna_date;
	private String admin_id;
	private String ans_content;
	private boolean ans_check;
	private Date    ans_date;
}
