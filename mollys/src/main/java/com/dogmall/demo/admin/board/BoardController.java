package com.dogmall.demo.admin.board;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

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

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.DTO.PageDTO;
import com.dogmall.demo.constants.Constants;
import com.dogmall.demo.util.FileManagerUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/board/*")
public class BoardController {

	private final BoardService boardService;
	
	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	
	@Value("${file.ckdir}")
	private String uploadCKPath;
	
	@GetMapping("/board_list")
	public void board_list(Criteria cri, String b_title, Model model) throws Exception{
		
		List<BoardVO> boardlist = boardService.getBoardInfoList(cri, b_title);
		
		int totalcount = boardService.getMailListCount(b_title);
		PageDTO pageDto = new PageDTO(cri, totalcount);
		
		model.addAttribute("boardlist", boardlist);
		model.addAttribute("pageMaker", pageDto);
	}
	
	@GetMapping("/board_insert")
	public void board_insert(@ModelAttribute BoardVO vo) {
		
	}
	
	@PostMapping("/board_insert")
	public String board_insert(@ModelAttribute BoardVO vo, MultipartFile uploadFile) throws Exception {
		
		String dateFolder = FileManagerUtils.getDateFolder();
		String saveFileName = FileManagerUtils.uploadFile(uploadPath, dateFolder, uploadFile);

		vo.setB_img(saveFileName);
		vo.setB_up_folder(dateFolder);
		
		boardService.board_insert(vo);
		
		return "redirect:/admin/board/board_list";
	}
	
	@PostMapping("/imageupload")
	public void imageupload(HttpServletRequest request, HttpServletResponse response, MultipartFile upload) {
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		try {
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			
			String ckUploadPath = uploadCKPath + fileName;
			
			out = new FileOutputStream(ckUploadPath);
			
			out.write(bytes);
			out.flush();
			
			printWriter = response.getWriter();
			
			// 한글파일 인코딩 설정문제 발생.
			String fileUrl = Constants.ROOT_URL + "/admin/product/display/" + fileName ;
					
			printWriter.println("{\"filename\" :\"" + fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}"); 
			printWriter.flush();
			
		}catch (Exception ex) {
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
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			entity = FileManagerUtils.getFile(uploadCKPath, fileName);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return entity;
	}
}
