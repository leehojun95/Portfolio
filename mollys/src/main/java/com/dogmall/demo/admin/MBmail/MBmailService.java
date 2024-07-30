package com.dogmall.demo.admin.MBmail;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class MBmailService {
	
	private final MBmailMapper mbmailMapper;

	public List<MBmailVO> getMailInfoList( Criteria cri, String m_title){
		return mbmailMapper.getMailInfoList(cri, m_title);
	}

	public int getMailListCount(String m_title) {
		return mbmailMapper.getMailListCount(m_title);
	}
	
	public void mailling_save(MBmailVO vo) {
		mbmailMapper.mailling_save(vo);
	}
	
	public String[] getAllMailAddress() {
		return mbmailMapper.getAllMailAddress();
	}
	
	public void mailSendCountUpdate(int m_num) {
		mbmailMapper.mailSendCountUpdate(m_num);
	}
	
	public MBmailVO getMailSendInfo(int m_num) {
		return mbmailMapper.getMailSendInfo(m_num);
	}
	
	public void mailling_edit(MBmailVO vo) {
		mbmailMapper.mailling_edit(vo);
	}
	
	public void mailling_delete(int m_num) {
		mbmailMapper.mailling_delete(m_num);
	}
}
