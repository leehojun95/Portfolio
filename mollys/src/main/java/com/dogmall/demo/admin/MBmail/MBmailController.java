package com.dogmall.demo.admin.MBmail;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.apache.tomcat.jni.Library;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.DTO.PageDTO;
import com.dogmall.demo.constants.Constants;
import com.dogmall.demo.mail.EmailDTO;
import com.dogmall.demo.mail.EmailService;
import com.dogmall.demo.util.FileManagerUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
//Constants.ROOT_URL
@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/mbmail/*")
public class MBmailController {
	
	private final MBmailService mbmailService;

	private final EmailService emailService;
	
	@Value("${file.ckdir}")
	private String uploadCKPath;
	
	@GetMapping("/mailling_list")
	public void mailling_list(Criteria cri, String m_title, Model model) throws Exception{
		
		List<MBmailVO> maillist = mbmailService.getMailInfoList(cri, m_title);
		
		int totalcount = mbmailService.getMailListCount(m_title);
		PageDTO pageDto = new PageDTO(cri, totalcount);
		
		model.addAttribute("maillist", maillist);
		model.addAttribute("pageMaker", pageDto);
	}
	
	@GetMapping("/mailling_form")
	public void mailling_form(@ModelAttribute("vo") MBmailVO vo) {
		
	}
	
	@PostMapping("/mailling_save")
	public String mailling_save(@ModelAttribute("vo")MBmailVO vo, Model model, RedirectAttributes rttr) throws Exception{
		
		mbmailService.mailling_save(vo);
		
		model.addAttribute("m_num", vo.getM_num());
		model.addAttribute("msg", "save");
		
		return "/admin/mbmail/mailling_form";
	}
	
	@PostMapping("mailling_send")
	public String mailling_send(MBmailVO vo, RedirectAttributes rttr) throws Exception {
	    // 입력된 이메일 주소를 가져옵니다
	    String recipientEmails = vo.getMbl_email();

	    String[] emailArr;
	    
	    // 이메일 주소가 null이거나 빈 문자열인 경우, 모든 이메일 주소를 가져옵니다
	    if (recipientEmails == null || recipientEmails.trim().isEmpty()) {
	        emailArr = mbmailService.getAllMailAddress();
	    } else {
	        // 이메일 주소를 쉼표로 구분하여 배열로 변환합니다
	        emailArr = recipientEmails.split("\\s*,\\s*");
	    }

	    // 이메일 DTO를 생성합니다
	    EmailDTO dto = new EmailDTO("Molly's", "Molly's", "", vo.getM_title(), vo.getM_content());

	    // 이메일을 지정된 주소로 전송합니다
	    emailService.sendMail(dto, emailArr);

	    // 메일 발송 카운트를 업데이트합니다
	    mbmailService.mailSendCountUpdate(vo.getM_num());

	    // 메시지 플래시 속성을 추가합니다
	    rttr.addFlashAttribute("msg", "send");

	    // 메일 목록 페이지로 리다이렉트합니다
	    return "redirect:/admin/mbmail/mailling_list";
	}

	
	/*
	 	@PostMapping("mailling_send")
	public String mailling_send(MBmailVO vo, RedirectAttributes rttr) throws Exception{
		
		String[] emailArr = mbmailService.getAllMailAddress();
		
		EmailDTO dto = new EmailDTO("Molly's", "Molly's", "", vo.getM_title(), vo.getM_content());
		
		emailService.sendMail(dto, emailArr);
		
		mbmailService.mailSendCountUpdate(vo.getM_num());
		
		rttr.addFlashAttribute("msg", "send");
		
		return "redirect:/admin/mbmail/mailling_list";
	} 
	 */

	
	@GetMapping("/mailling_sendform")
	public void mailling_sendform(int m_num, Model model) throws Exception{
		MBmailVO vo = mbmailService.getMailSendInfo(m_num);
		
		model.addAttribute("vo", vo);
	}
	
	@PostMapping("/mailling_edit")
	public String mailling_edit(@ModelAttribute("vo") MBmailVO vo, Model model) throws Exception{
		
		mbmailService.mailling_edit(vo);
		
		model.addAttribute("msg", "modify");
		
		return "/admin/mbmail/mailling_sendform";
	}
	
	@GetMapping("/mailling_delete")
	public String mailling_delete(Criteria cri, int m_num) throws Exception{
		
		mbmailService.mailling_delete(m_num);
		
		return "redirect:/admin/mbmail/mailling_list" + cri.getListLink();
	}
	
	@PostMapping("/imageupload")
	public void imageupload(HttpServletRequest request, HttpServletResponse response, MultipartFile upload) {
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		try {
			//1)CKeditor를 이용한 파일업로드 처리.
			String fileName = upload.getOriginalFilename(); // 업로드 할 클라이언트 파일이름
			byte[] bytes = upload.getBytes(); // 업로드 할 파일의 바이트배열
			
			String ckUploadPath = uploadCKPath + fileName; // "C:\\Dev\\upload\\ckeditor\\" + "abc.gif"
			
			out = new FileOutputStream(ckUploadPath); // "C:\\Dev\\upload\\ckeditor\\abc.gif" 파일생성. 0 byte
			
			out.write(bytes); // 빨대(스트림)의 공간에 업로드할 파일의 바이트배열을 채운상태.
			out.flush(); // "C:\\Dev\\upload\\ckeditor\\abc.gif" 0 byte -> 크기가 채워진 정상적인 파일로 인식.
			
			//2)업로드한 이미지파일에 대한 정보를 클라이언트에게 보내는 작업
			
			printWriter = response.getWriter();
			
			// 한글파일 인코딩 설정문제 발생.
			String fileUrl = Constants.ROOT_URL + "/admin/product/display/" + fileName; // 매핑주소/이미지파일명
//					String fileUrl = fileName;
			
			
			// Ckeditor 4.12에서는 파일정보를 다음과 같이 구성하여 보내야 한다.
			// {"filename" : "abc.gif", "uploaded":1, "url":"/ckupload/abc.gif"}
			// {"filename" : 변수, "uploaded":1, "url":변수}
			printWriter.println("{\"filename\" :\"" + fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}"); // 스트림에 채움.
			printWriter.flush();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if(printWriter != null) printWriter.close();
		}
	}
	
	@GetMapping("/display/{fileName}")
	public ResponseEntity<byte[]> getFile(@PathVariable("fileName") String fileName) {
		
		log.info("파일이미지: " + fileName);
		
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			entity = FileManagerUtils.getFile(uploadCKPath, fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entity;
		
	}
}
