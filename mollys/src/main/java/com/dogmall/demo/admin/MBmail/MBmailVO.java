package com.dogmall.demo.admin.MBmail;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MBmailVO {

	// m_num, m_title, m_content, m_check, m_regdate
	
	private Integer m_num;
	private String  m_title;
	private String  m_content;
	private String  m_check;
	private int     m_send_count;
	private Date    m_regdate;
	private String 	mbl_email;
	
}
