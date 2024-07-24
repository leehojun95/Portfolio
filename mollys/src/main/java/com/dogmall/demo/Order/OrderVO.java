package com.dogmall.demo.Order;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderVO {

	//ord_code, mbl_id, ord_name, ord_addr_zipcode, ord_addr_basic, ord_addr_detail, ord_tel, ord_price, ord_desc, ord_regdate
	
	private Long	ord_code;
	private String  mbl_id;
	private String  ord_name;
	private String  ord_addr_zipcode;
	private String  ord_addr_basic;
	private String  ord_addr_detail;
	private String  ord_tel;
	private int     ord_price;
	private String  ord_desc;
	private String  ord_admin_memo;
	private Date	ord_regdate;
}
