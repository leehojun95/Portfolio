package com.dogmall.demo.payinfo;

import java.util.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class PayinfoVO {

	// pay_id,ord_code, mbl_id, paymethod, pay_price, payinfo, pay_status, pay_date
	
	private Integer pay_id;
	private Long ord_code;
	private String mbl_id;
	private String paymethod;
	private String payinfo;
	private int pay_price;
	private String pay_status;
	private Date pay_date;
}
