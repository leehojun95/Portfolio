package com.dogmall.demo.board;

import java.util.List;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.admin.board.AdmimBoardVO;

public interface BoardMapper {
	
	List<AdmimBoardVO> board_list(Criteria cri);
	
	int getTotalCount(Criteria cri);

	AdmimBoardVO board_info(int b_num);
}
