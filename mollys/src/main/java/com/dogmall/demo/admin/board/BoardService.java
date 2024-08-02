package com.dogmall.demo.admin.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class BoardService {

	private final BoardMapper boardMapper;
	
	public void board_insert(BoardVO vo) {
		boardMapper.board_insert(vo);
	}
	
	public List<BoardVO> getBoardInfoList(Criteria cri, String b_title) {
		return boardMapper.getBoardInfoList(cri, b_title);
	}
	
	public int getMailListCount(String b_title) {
		return boardMapper.getMailListCount(b_title);
	}
}
