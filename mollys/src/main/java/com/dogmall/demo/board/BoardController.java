package com.dogmall.demo.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.DTO.PageDTO;
import com.dogmall.demo.admin.board.AdmimBoardVO;
import com.dogmall.demo.util.FileManagerUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/board/*")
public class BoardController {

	private final BoardService boardService;
	
	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	@Value("${file.ckdir}")
	private String uploadCKPath;
	
	@GetMapping("/board_list")
	public void board_list(Criteria cri, Model model) {
		
		List<AdmimBoardVO> board_list = boardService.board_list(cri);
		
		board_list.forEach(vo -> {
			vo.setB_up_folder(vo.getB_up_folder().replace("\\", "/"));
		});
		
		int toatalCount = boardService.getTotalCount(cri);
		
		model.addAttribute("board_list", board_list);
		model.addAttribute("pageMaker", new PageDTO(cri, toatalCount));
	}
	
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception{
		
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	@GetMapping("/board_detail")
	public void board_detail(int b_num, Model model) throws Exception{
		
		AdmimBoardVO vo = boardService.board_info(b_num);
		vo.setB_up_folder(vo.getB_up_folder().replace("\\", "/"));
		
		model.addAttribute("board", vo);
		
	}

}