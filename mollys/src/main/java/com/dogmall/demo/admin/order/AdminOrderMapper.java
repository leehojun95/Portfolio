package com.dogmall.demo.admin.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.Order.OrderVO;

public interface AdminOrderMapper {

	List<OrderVO> order_list(@Param("cri") Criteria cri, @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	List<Map<String, Object>> order_list2();
	
	int getTotalCount(@Param("cri") Criteria cri, @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	OrderVO order_info (Long ord_code);
	
	List<OrderDetailInfoVo> order_detail_info(Long ord_code);
	
	void order_product_delete(@Param("ord_code") Long ord_code, @Param("pro_num") int pro_num); 
	
	void order_basic_modify(OrderVO vo);
	
	void order_tot_price_change(Long ord_code);
}
