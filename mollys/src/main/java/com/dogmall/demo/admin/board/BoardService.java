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
	
	public List<BoardVO> board_list(Criteria cri){
		return boardMapper.board_list(cri);
	}
	
	public int getTotalCount(Criteria cri) {
		return boardMapper.getTotalCount(cri);
	}
	
	public BoardVO board_edit(Integer b_num) {
		return boardMapper.board_edit(b_num);
	}
	
	public void board_edit_ok(BoardVO vo) {
		boardMapper.board_edit_ok(vo);
	}
	
	public void board_delete(Integer b_num) {
		boardMapper.board_delete(b_num);
	}
}
