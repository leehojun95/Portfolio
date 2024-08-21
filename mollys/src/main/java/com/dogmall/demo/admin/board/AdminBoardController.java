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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
public class AdminBoardController {

	private final AdminBoardService adminboardService;
	
	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	
	@Value("${file.ckdir}")
	private String uploadCKPath;

	@GetMapping("/board_insert")
	public void board_insert(AdmimBoardVO vo) throws Exception{
		
	}
	
	@PostMapping("/board_insert")
	public String board_insertOk(AdmimBoardVO vo, MultipartFile uploadFile) throws Exception{
		
		String dateFolder = FileManagerUtils.getDateFolder();
		String saveFileName = FileManagerUtils.uploadFile(uploadPath, dateFolder, uploadFile);
		
		vo.setB_img(saveFileName);
		vo.setB_up_folder(dateFolder);
		
		adminboardService.board_insert(vo);
		
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
	
	@GetMapping("/board_list")
	public void board_list(Criteria cri, Model model) throws Exception{
		
		List<AdmimBoardVO> board_list = adminboardService.board_list(cri);
		
		board_list.forEach(vo -> {
			vo.setB_up_folder(vo.getB_up_folder().replace("\\", "/"));
		});
		
		int totalcount = adminboardService.getTotalCount(cri);
		
		model.addAttribute("board_list", board_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalcount));
	}
	
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {
		
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	@GetMapping("/board_edit")
	public void board_edit(@ModelAttribute("cri") Criteria cri, Integer b_num, Model model) {
		
		AdmimBoardVO vo = adminboardService.board_edit(b_num);
		
		vo.setB_up_folder(vo.getB_up_folder().replace("\\", "/"));
		model.addAttribute("boardVO", vo);
	}
	
	@PostMapping("/board_edit")
	public String board_edit(AdmimBoardVO vo, MultipartFile uploadFile, Criteria cri, RedirectAttributes rttr) throws Exception{
		
		if(!uploadFile.isEmpty()) {
			
			FileManagerUtils.delete(uploadPath, vo.getB_up_folder(), vo.getB_img(), "image");
			
			String dateFolder = FileManagerUtils.getDateFolder();
			String saveFileName = FileManagerUtils.uploadFile(uploadPath, dateFolder, uploadFile);
			
			vo.setB_img(saveFileName);
			vo.setB_up_folder(dateFolder);
		}
		
		adminboardService.board_edit_ok(vo);
		
		return "redirect:/admin/board/board_list" + cri.getListLink();
	}
	
	@GetMapping("/board_delete")
	public String board_delete(Criteria cri, Integer b_num) {
		
		adminboardService.board_delete(b_num);
		
		return "redirect:/admin/board/board_list" + cri.getListLink();
		
	}
	
}










