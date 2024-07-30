package com.dogmall.demo.admin.MBmail;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.Criteria;

public interface MBmailMapper {
	
	List<MBmailVO> getMailInfoList(@Param("cri") Criteria cri, @Param("m_title") String m_title);

	int getMailListCount(String m_title);
	
	void mailling_save(MBmailVO vo);
	
	String[] getAllMailAddress();
	
	void mailSendCountUpdate(int m_num);
	
	MBmailVO getMailSendInfo(int m_num);
	
	void mailling_edit(MBmailVO vo);
	
	void mailling_delete(int m_num);
	
}
