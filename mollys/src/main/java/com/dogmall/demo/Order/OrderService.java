package com.dogmall.demo.Order;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dogmall.demo.cart.CartMapper;
import com.dogmall.demo.payinfo.PayinfoMapper;
import com.dogmall.demo.payinfo.PayinfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class OrderService {
	
	private final OrderMapper orderMapper;
	private final CartMapper cartMapper;
	private final PayinfoMapper payinfoMapper;
	
	@Transactional
	public void order_process(OrderVO vo, String mbl_id) {
		
		vo.setMbl_id(mbl_id);
		orderMapper.order_insert(vo);
		
		orderMapper.oderDetail_insert(vo.getOrd_code(), mbl_id);
		
		PayinfoVO p_vo = PayinfoVO.builder()
				.ord_code(vo.getOrd_code())
				.pay_id(vo.getOrd_price())
				.paymethod("kakaopay")
				.pay_status("완납")
				.build();
		
		payinfoMapper.payinfo_insert(p_vo);
		
		cartMapper.cart_empty(mbl_id);
	}

}
