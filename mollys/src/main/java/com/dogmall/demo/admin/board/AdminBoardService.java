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
public class AdminBoardService {

	private final AdminBoardMapper adminboardMapper;
	
	public void board_insert(AdmimBoardVO vo) {
		adminboardMapper.board_insert(vo);
	}
	
	public List<AdmimBoardVO> board_list(Criteria cri){
		return adminboardMapper.board_list(cri);
	}
	
	public int getTotalCount(Criteria cri) {
		return adminboardMapper.getTotalCount(cri);
	}
	
	public AdmimBoardVO board_edit(Integer b_num) {
		return adminboardMapper.board_edit(b_num);
	}
	
	public void board_edit_ok(AdmimBoardVO vo) {
		adminboardMapper.board_edit_ok(vo);
	}
	
	public void board_delete(Integer b_num) {
		adminboardMapper.board_delete(b_num);
	}
}
