package com.dogmall.demo.admin.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.DTO.PageDTO;
import com.dogmall.demo.Order.OrderVO;
import com.dogmall.demo.payinfo.PayinfoService;
import com.dogmall.demo.payinfo.PayinfoVO;
import com.dogmall.demo.util.FileManagerUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/order/*")
@RequiredArgsConstructor
public class AdminOrderController {

	private final AdminOrderService adminOrderService;
	private final PayinfoService payinfoService;
	
	
	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	@GetMapping("/order_list")
	public void order_list(Criteria cri, Model model) throws Exception {
		
		cri.setAmount(5);
		List<OrderVO> order_list = adminOrderService.order_list(cri);
		
		int totalCount = adminOrderService.getTotalCount(cri);
		
		model.addAttribute("order_list",order_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
	
	@GetMapping("/order_detail_info")
	public ResponseEntity<Map<String, Object>> order_detail_info(Long ord_code, Model model) throws Exception{
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		Map<String, Object> map = new HashMap<>();
		
		OrderVO vo =adminOrderService.order_info(ord_code);
		map.put("ord_basic", vo);
		
		List<OrderDetailInfoVo> ord_product_list = adminOrderService.order_detail_info(ord_code);
		
		ord_product_list.forEach(ord_pro ->{
			ord_pro.setPro_up_folder(ord_pro.getPro_up_folder().replace("\\", "/"));
		});
		
		map.put("ord_pro_list", ord_product_list);
		
		PayinfoVO p_vo = payinfoService.ord_pay_info(ord_code);
		map.put("payinfo", p_vo);
		
		entity = new ResponseEntity<Map<String,Object>>(map,HttpStatus.OK);
		
		return entity;
	}
	
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception{
		
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
}
