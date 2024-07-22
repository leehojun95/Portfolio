package com.dogmall.demo.admin.order;

import java.util.List;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.Order.OrderVO;

public interface AdminOrderMapper {

	List<OrderVO> order_list(Criteria cri);
	
	int getTotalCount(Criteria cri);
	
	OrderVO order_info (Long ord_code);
	
	List<OrderDetailInfoVo> order_detail_info(Long ord_code);
}
