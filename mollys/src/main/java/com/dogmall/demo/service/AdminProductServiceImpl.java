package com.dogmall.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.AdminProductDTO;
import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.domain.AdminProductVO;
import com.dogmall.demo.mapper.AdminProductMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class AdminProductServiceImpl implements AdminProductService {
	
	private final AdminProductMapper adminProductMapper;

	@Override
	public void pro_insert(AdminProductVO vo) {
		adminProductMapper.pro_insert(vo);
		
	}

	@Override
	public List<AdminProductVO> pro_list(Criteria cri) {
		return adminProductMapper.pro_list(cri);
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return adminProductMapper.getTotalCount(cri);
	}

	@Override
	public AdminProductVO pro_edit(Integer pro_num) {
		return adminProductMapper.pro_edit(pro_num);
	}

	@Override
	public void pro_edit_ok(AdminProductVO vo) {
		adminProductMapper.pro_edit_ok(vo);
		
	}

	@Override
	public void pro_delete(Integer pro_num) {
		adminProductMapper.pro_delete(pro_num);
		
	}

	@Override
	public void pro_checked_modify1(List<Integer> pro_num_arr, List<Integer> pro_price_arr, List<String> pro_buy_arr) {
		for(int i=0; i<pro_num_arr.size(); i++) {
			adminProductMapper.pro_checked_modify1(pro_num_arr.get(i), pro_price_arr.get(i), pro_buy_arr.get(i));
		}
	}

	@Override
	public void pro_checked_modify2(List<Integer> pro_num_arr, List<Integer> pro_price_arr, List<String> pro_buy_arr) {
		
		List<AdminProductDTO> pro_modify_list = new ArrayList<>();
		
		for(int i=0; i<pro_num_arr.size(); i++) {
			AdminProductDTO productDTO = new AdminProductDTO(pro_num_arr.get(i), pro_price_arr.get(i), pro_buy_arr.get(i));
			pro_modify_list.add(productDTO);
		}
		adminProductMapper.pro_checked_modify2(pro_modify_list);

}
}
