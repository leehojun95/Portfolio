package com.dogmall.demo.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.member.MemberVO;
import com.dogmall.demo.util.FileManagerUtils;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RequestMapping("/cart/*")
@Slf4j
@Controller
public class CartController {
	
	private final CartService cartService;
	
	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	@GetMapping("/cart_add")
	public ResponseEntity<String> cart_add(CartVO vo, HttpSession session) throws Exception {
		
		String mbl_id = ((MemberVO) session.getAttribute("login_status")).getMbl_id();
		vo.setMbl_id(mbl_id);
		
		ResponseEntity<String> entity = null;
		
		cartService.cart_add(vo);
		
		entity = new ResponseEntity<String>("success", HttpStatus.OK);
		
		return entity;
	}
	
	@GetMapping("/cart_list")
	public void cart_list(HttpSession session, Model model) throws Exception {
		
		String mbl_id = ((MemberVO) session.getAttribute("login_status")).getMbl_id();
		
		List<CartProductVO> cart_list = cartService.cart_list(mbl_id);
		cart_list.forEach(vo -> vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/")));
		
		model.addAttribute("cart_list", cart_list);
	}
	
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {
		
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	@GetMapping("/cart_del")
	public String cart_del(Long cart_code) throws Exception{
		
		cartService.cart_del(cart_code);
		
		return "redirect:/cart/cart_list";
	}
	
	@GetMapping("/cart_change")
	public String cart_change(Long cart_code, int cart_amount) throws Exception{
		
		cartService.cart_change(cart_code, cart_amount);
		
		return "redirect:/cart/cart_list";
	}
	
	@GetMapping("/cart_empty")
	public String cart_empty (HttpSession session) throws Exception{
		
		String mbl_id = ((MemberVO) session.getAttribute("login_status")).getMbl_id();
		
		cartService.cart_empty(mbl_id);
		
		return "redirect:/cart/cart_list";
	}
	
	
}
