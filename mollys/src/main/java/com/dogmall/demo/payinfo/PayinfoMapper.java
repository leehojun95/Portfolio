package com.dogmall.demo.payinfo;

public interface PayinfoMapper {

	void payinfo_insert(PayinfoVO vo);
	
	PayinfoVO ord_pay_info(Long ord_code);
	
	void pay_tot_price_change(Long ord_code);
}
