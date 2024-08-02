package com.dogmall.demo.member;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dogmall.demo.kakaologin.KakaoLoginVO;
import com.dogmall.demo.mail.EmailDTO;
import com.dogmall.demo.mail.EmailService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/*")
@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	private final PasswordEncoder passwordEncoder;
	
	private final EmailService emailService;

	@GetMapping("/join")
	public void join() {
		log.info("join...");
	}
	
	@PostMapping("/join")
	public String joinOK(MemberVO vo) throws Exception{
		
//		vo.setMbl_password(passwordEncoder.encode(vo.getMbl_password()));
		
		memberService.join(vo);
		
		
		return "redirect:/user/login";
	}
	
	@GetMapping("/idCheck")
	public ResponseEntity<String> idCheck(String mbl_id) throws Exception{
		
		log.info("아이디: " + mbl_id);
		
		ResponseEntity<String> entity = null;
		
		String idMbl ="";
		if(memberService.idCheck(mbl_id) != null) {
			idMbl = "no";
		}else {
			idMbl = "yes";
		}
		
		entity = new ResponseEntity<String>(idMbl, HttpStatus.OK);
		
		return entity;
		
	}
	
	@GetMapping("/login")
	public void login() {
		
	}
	
	@PostMapping("/login")
	public String loginOK(String mbl_id, String mbl_password, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		MemberVO vo = memberService.login(mbl_id);
		
		String msg = "";
		String url = "/";
		
		
		if(vo != null) {
			
			if(passwordEncoder.matches(mbl_password, vo.getMbl_password())) {
				vo.setMbl_password("");
				session.setAttribute("login_status", vo);
			}else {
				msg ="failPW";
				url = "/user/login";
			}
		}else {
			msg ="failID";
			url ="/user/login";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:" + url;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/" ;
	}
	
	@GetMapping("/idfind")
	public void idfind() {
		
	}
	
	@PostMapping("/idfind")
	public String idfindOK(String mbl_name, String mbl_email, String authcode, HttpSession session, RedirectAttributes rttr) throws Exception {
		
		String msg = "";
		String url = "/";
		
		if(authcode.equals(session.getAttribute("authcode"))) {
			
			String mbl_id = memberService.idfind(mbl_name, mbl_email);
			if(mbl_id != null) {
				
				String subject = "Molly's 아이디를 보냅니다.";
				EmailDTO dto = new EmailDTO("Molly's", "Molly's", mbl_email, subject, mbl_id);
				
				emailService.sendMail("mailtemplate/emailidfind", dto, mbl_id);
				
				session.removeAttribute("authcode");
				
				msg = "success";
				url = "/user/login";
				rttr.addFlashAttribute("msg", msg);
				
				
			}else {
				msg = "failID";
				url = "/user/idfind";
			}
			
			
			
		}else {
			
			msg = "failAuthCode";
			url = "/user/idfind";
			
		}
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:" + url;
	}
	
	@GetMapping("/pwfind")
	public void pwfind() {
		
	}

	@PostMapping("/pwfind")
	public String pwfindOK(String mbl_id, String mbl_name, String mbl_email, String authcode, HttpSession session, RedirectAttributes rttr) throws Exception{
		

		String url = "";
		String msg = "";
		
		if(authcode.equals(session.getAttribute("authcode"))) {
			
			// 사용자가 입력한 3게정보(아이디, 이메일, 이름)을 조건으로 사용하여, 이메일을 DB에서 가져온다.
			String d_eamil = memberService.pwfind(mbl_id, mbl_name, mbl_email);
			if(d_eamil != null) {
				String temPw = UUID.randomUUID().toString().replaceAll("-", ""); // - 를 제거
				temPw = temPw.substring(0, 10);
			String enc_temPw = passwordEncoder.encode(temPw);
			
			memberService.tempPwUpdate(mbl_id, enc_temPw);
			
			EmailDTO dto = new EmailDTO("Molly's", "Molly's", d_eamil, "Molly's에서 임시비밀번호를 보냅니다.", temPw);
			
			emailService.sendMail("mailtemplate/emailPwResult", dto, temPw); 
			
			session.removeAttribute("authcode");
			
			url = "/user/login";
		
		}else {
			msg = "failInput";
			url = "/user/pwfind";
		}

	}else {
		msg = "failAuth";
		url = "/user/pwfind";
	}
	return "redirect:" + url;
}
	
	
	@GetMapping("/mypage")
	public void mypage(HttpSession session, Model model) throws Exception {
		
		if(session.getAttribute("login_status") != null) {
			String mbl_id = ((MemberVO) session.getAttribute("login_status")).getMbl_id();
			MemberVO vo = memberService.login(mbl_id);
			model.addAttribute("user", vo);
		} else if(session.getAttribute("kakao_status") != null) {
			
			KakaoLoginVO kakaoLoginVO= (KakaoLoginVO) session.getAttribute("kakao_status");
			
			// MyPage에서 보여줄 정보를 선택적으로 작업.
			MemberVO vo = new MemberVO();
			vo.setMbl_name(kakaoLoginVO.getNickname());
			vo.setMbl_email(kakaoLoginVO.getEmail());

			model.addAttribute("user", vo);
			model.addAttribute("msg", "kakao_login");
			
		}
		
	}
	
	@PostMapping("/modify")
	public String modify(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		if(session.getAttribute("login_status") == null)
		return "redirect:/user/login";
		
		String mbl_id= ((MemberVO)session.getAttribute("login_status")).getMbl_id();
		
		memberService.modify(vo);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/user/mypage";
	}
	
	@GetMapping("/changpw")
	public void chhangpw() {
		
	}
	
	@PostMapping("/changpw")
	public String changpwOK(String cur_mbl_password, String new_mbl_password, HttpSession session, RedirectAttributes rttr) {
		
		String mbl_id =((MemberVO) session.getAttribute("login_status")).getMbl_id();
		
		MemberVO vo = memberService.login(mbl_id);
		
		String msg = "";
		
		if(vo != null) {
			if(passwordEncoder.matches(cur_mbl_password, vo.getMbl_password())) {
				
				String enc_new_mbl_password = passwordEncoder.encode(new_mbl_password);
				memberService.changePw(mbl_id, enc_new_mbl_password);
				msg = "success";
			}else {  // 사용자가 입력한 비밀번호가 암호화된 형태에 해당하지 않는 것이라면 
				msg = "failPW";
			}
		}
		
		rttr.addFlashAttribute("msg", msg); // jsp에서 msg변수를 사용목적
		
		return "redirect:/user/changpw";
	}
	
	@GetMapping("/delete")
	public void delete() {
		
	}
	
	@PostMapping("/delete")
	public String deleteOK(String mbl_password, HttpSession session, RedirectAttributes rttr) throws Exception{
		
		String mbl_id = ((MemberVO) session.getAttribute("login_status")).getMbl_id();
		
		MemberVO vo = memberService.login(mbl_id);
		
		String msg = "";
		String url = "/";
		
		if(vo != null) {
			
			if(passwordEncoder.matches(mbl_password, vo.getMbl_password())) {
				
				memberService.delete(mbl_id);
				session.invalidate();
			}else {
				msg = "failPW";
				url = "/user/delete"; 
				
				rttr.addFlashAttribute("msg", msg);
			}
		}
		
		return "redirect:" + url;
	}
}
