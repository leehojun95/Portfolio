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

	
	private Integer pay_id;
	private Long ord_code;
	private String paymethod;
	private int pay_price;
	private String pay_status;
	private Date pay_date;
}
