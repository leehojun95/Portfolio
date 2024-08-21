package com.dogmall.demo.admin.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.Criteria;

public interface AdminBoardMapper {
	
	void board_insert(AdmimBoardVO vo);
	
	List<AdmimBoardVO> board_list(Criteria cri);

	int getTotalCount(Criteria cri);
	
	AdmimBoardVO board_edit(Integer b_num);
	
	void board_edit_ok(AdmimBoardVO vo);
	
	void board_delete(Integer b_num);
}
