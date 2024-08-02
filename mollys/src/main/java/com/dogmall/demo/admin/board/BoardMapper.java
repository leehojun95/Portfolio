package com.dogmall.demo.admin.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.Criteria;

public interface BoardMapper {
	
	List<BoardVO> getBoardInfoList(@Param("cri") Criteria cri, @Param("b_title") String b_title);
	
	int getMailListCount(String b_title);
	
	void board_insert(BoardVO vo);

}
