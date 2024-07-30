package com.dogmall.demo.admin.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.Order.OrderVO;
import com.dogmall.demo.payinfo.PayinfoMapper;
import com.dogmall.demo.payinfo.PayinfoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminOrderService {

	private final AdminOrderMapper adminOrderMapper;
	
	private final PayinfoMapper payinfoMapper;
	
	public List<OrderVO> order_list(Criteria cri, String start_date, String end_date) {
		return adminOrderMapper.order_list(cri, start_date, end_date);
	}
	
	public List<Map<String, Object>> order_list2(){
		return adminOrderMapper.order_list2();
	}
	
	public int getTotalCount(Criteria cri, String start_date, String end_date) {
		return adminOrderMapper.getTotalCount(cri, start_date, end_date);
	}
	
	public OrderVO order_info (Long ord_code) {
		return adminOrderMapper.order_info(ord_code);
	}
	
	public List<OrderDetailInfoVo> order_detail_info(Long ord_code) {
		return adminOrderMapper.order_detail_info(ord_code);
	}
	
	@Transactional
	public void order_product_delete(Long ord_code, int pro_num) {
		
		adminOrderMapper.order_product_delete(ord_code, pro_num);
		
		adminOrderMapper.order_tot_price_change(ord_code);
		
		payinfoMapper.pay_tot_price_change(ord_code);
	}
	
	public void order_basic_modify(OrderVO vo) {
		adminOrderMapper.order_basic_modify(vo);
	}
}
