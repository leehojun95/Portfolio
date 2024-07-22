package com.dogmall.demo.admin.order;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.Order.OrderVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminOrderService {

	private final AdminOrderMapper adminOrderMapper;
	
	public List<OrderVO> order_list(Criteria cri) {
		return adminOrderMapper.order_list(cri);
	}
	
	public int getTotalCount(Criteria cri) {
		return adminOrderMapper.getTotalCount(cri);
	}
	
	public OrderVO order_info (Long ord_code) {
		return adminOrderMapper.order_info(ord_code);
	}
	
	public List<OrderDetailInfoVo> order_detail_info(Long ord_code) {
		return adminOrderMapper.order_detail_info(ord_code);
	}
}
