package com.dogmall.demo.admin.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.Criteria;

public interface BoardMapper {
	
	void board_insert(BoardVO vo);
	
	List<BoardVO> board_list(Criteria cri);

	int getTotalCount(Criteria cri);
	
	BoardVO board_edit(Integer b_num);
	
	void board_edit_ok(BoardVO vo);
	
	void board_delete(Integer b_num);
}
