package com.dogmall.demo.admin.MBMG;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.DTO.PageDTO;
import com.dogmall.demo.mail.EmailService;
import com.dogmall.demo.member.MemberVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/mbmg/*")
public class MBMGController {

	private final MBMGService mbmgService;

	@GetMapping("/mbmg_list")
	public void mbmg_list(Criteria cri, @ModelAttribute("start_date") String start_date, @ModelAttribute("end_date") String end_date, Model model) throws Exception {
		
		cri.setAmount(10);
		List<MemberVO> mbmg_list = mbmgService.mbmg_list(cri, start_date, end_date);
		
		int totalCount = mbmgService.getTotalCount(cri, start_date, end_date);
		
		model.addAttribute("mbmg_list", mbmg_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalCount));
	}
	
	@GetMapping("/mbmg_edit")
	public void mbmg_edit(String mbl_id, Model model) throws Exception {
		
	    MemberVO vo = mbmgService.mbmg_edit(mbl_id);
	    
	    model.addAttribute("memberVO", vo);
	}

	
	@PostMapping("/mbmg_edit")
	public String mbmg_edit(MemberVO vo, Criteria cri) throws Exception {
		
		mbmgService.mbmg_edit_ok(vo);
		
		return "redirect:/admin/mbmg/mbmg_list" + cri.getListLink();
	}
	
	@GetMapping("/mbmg_delete")
	public String mbmg_delete(Criteria cri, String mbl_id) throws Exception{
		
		mbmgService.mbmg_delete(mbl_id);
		
		return "redirect:/admin/mbmg/mbmg_list" + cri.getListLink();
	}
	
}
