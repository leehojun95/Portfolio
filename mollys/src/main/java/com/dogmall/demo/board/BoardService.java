package com.dogmall.demo.board;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.admin.board.AdmimBoardVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardMapper boardMapper;
	
	public List<AdmimBoardVO> board_list(Criteria cri){
		return boardMapper.board_list(cri);
	}
	
	public int getTotalCount(Criteria cri) {
		return boardMapper.getTotalCount(cri);
	}
	
	public AdmimBoardVO board_info(int b_num) {
		return boardMapper.board_info(b_num);
	}

}
