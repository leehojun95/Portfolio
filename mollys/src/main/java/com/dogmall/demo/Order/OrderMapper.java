package com.dogmall.demo.Order;

import org.apache.ibatis.annotations.Param;

public interface OrderMapper {

	void order_insert(OrderVO vo);
	
	void oderDetail_insert(@Param("ord_code") Long ord_code, @Param("mbl_id") String mbl_id);
}
