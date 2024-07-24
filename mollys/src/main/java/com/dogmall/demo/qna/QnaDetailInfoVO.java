package com.dogmall.demo.qna;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaDetailInfoVO {

	private Long qna_code;
	private String mbl_id;
	private Integer pro_num;
	private String qna_title;
	private String qna_content;
	private Date qna_date;
	private String ans_check;
	private String pro_up_folder;
	private String pro_img;
}
