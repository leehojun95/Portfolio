package com.dogmall.demo.admin.product;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dogmall.demo.DTO.Criteria;
import com.dogmall.demo.DTO.PageDTO;
import com.dogmall.demo.admin.category.AdminCategoryService;
import com.dogmall.demo.admin.category.AdminCategoryVO;
import com.dogmall.demo.util.FileManagerUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/admin/product*")
@RequiredArgsConstructor
@Controller
public class AdminProductController {
	
	private final AdminProductService adminProductService;
	private final AdminCategoryService adminCategoryService;

	@Value("${file.product.image.dir}")
	private String uploadPath;
	
	@Value("${file.ckdir}")
	private String uploadCKPath;
	
	
	@GetMapping("pro_insert")
	public void pro_insertForm(Model model) {
		
		List<AdminCategoryVO> adcate_list =  adminCategoryService.getFirstCategoryList();
		model.addAttribute("adcate_list", adcate_list);
	}
	
	@PostMapping("pro_insert")
	public String pro_insertOk(AdminProductVO vo, MultipartFile uploadFile) throws Exception {
		
		
		
		String dateFolder = FileManagerUtils.getDateFolder();
		String saveFileName = FileManagerUtils.uploadFile(uploadPath, dateFolder, uploadFile);
		
		vo.setPro_img(saveFileName);
		vo.setPro_up_folder(dateFolder);
		
		log.info("상품정보: " + vo);
		
		adminProductService.pro_insert(vo);
		
		return "redirect:/admin/product/pro_list";
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
			
			String fileUrl = "/admin/product/display/" + fileName ;
					
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
	
	@GetMapping("/pro_list")
	public void pro_list(Criteria cri, Model model) throws Exception {
		
//		cri.setAmount(2);
		
		List<AdminProductVO> pro_list = adminProductService.pro_list(cri);
		
		pro_list.forEach(vo -> {
			vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		});
		
		int totalcount = adminProductService.getTotalCount(cri);
		
		model.addAttribute("pro_list", pro_list);
		model.addAttribute("pageMaker", new PageDTO(cri, totalcount));
		
	}
	
	@GetMapping("/image_display")
	public ResponseEntity<byte[]> image_display(String dateFolderName, String fileName) throws Exception {
		
		return FileManagerUtils.getFile(uploadPath + dateFolderName, fileName);
	}
	
	@GetMapping("/pro_edit")
	public void pro_edit(@ModelAttribute("cri") Criteria cri, Integer pro_num, Model model) throws Exception {
		
		List<AdminCategoryVO> adcate_list = adminCategoryService.getFirstCategoryList();
		model.addAttribute("adcate_list", adcate_list);
		
		AdminProductVO vo = adminProductService.pro_edit(pro_num);
		
		vo.setPro_up_folder(vo.getPro_up_folder().replace("\\", "/"));
		model.addAttribute("adminproductVO", vo);
		
		int ctg_h_code = vo.getCtg_h_code();
		int ctg_l_code = adminCategoryService.getFirstCategoryBySecondCategory(ctg_h_code).getCtg_l_code();
		model.addAttribute("ctg_l_code", ctg_l_code);
		
		model.addAttribute("sub_adcate_list", adminCategoryService.getSecondCategoryList(ctg_l_code));
	}
	
	@PostMapping("/pro_edit")
	public String pro_edit(AdminProductVO vo, MultipartFile uploadFile, Criteria cri, RedirectAttributes rttr) throws Exception{
		
		if(!uploadFile.isEmpty()) {
			
			FileManagerUtils.delete(uploadPath, vo.getPro_up_folder(), vo.getPro_img(), "image");
			
			String dateFolder = FileManagerUtils.getDateFolder();
			String saveFileName = FileManagerUtils.uploadFile(uploadPath, dateFolder, uploadFile);
			
			vo.setPro_img(saveFileName);
			vo.setPro_up_folder(dateFolder);
		}
		
		adminProductService.pro_edit_ok(vo);
		
		return "redirect:/admin/product/pro_list" + cri.getListLink() ;
	}
	
	@GetMapping("pro_delete")
	public String pro_delete(Criteria cri, Integer pro_num) throws Exception{
		
		adminProductService.pro_delete(pro_num);
		
		return "redirect:/admin/product/pro_list" + cri.getListLink();
	}
	
	@PostMapping("/pro_checked_modify1") 
	public ResponseEntity<String> pro_ckecked_modify1(
			@RequestParam("pro_num_arr") List<Integer> pro_num_arr,
			@RequestParam("pro_price_arr") List<Integer> pro_price_arr,
			@RequestParam("pro_buy_arr") List<String> pro_buy_arr) throws Exception{
		
		adminProductService.pro_checked_modify1(pro_num_arr, pro_price_arr, pro_buy_arr);
		
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<>("success", HttpStatus.OK);
		
		return entity;
	}
	
	@PostMapping("/pro_checked_modify2")
	public ResponseEntity<String> pro_checked_modify2 (
			@RequestParam("pro_num_arr") List<Integer> pro_num_arr,
			@RequestParam("pro_price_arr") List<Integer> pro_price_arr,
			@RequestParam("pro_buy_arr") List<String> pro_buy_arr) throws Exception {
		
		
		log.info("상품코드: " + pro_num_arr);
		log.info("상품가격: " + pro_price_arr);
		log.info("상품판매: " + pro_buy_arr);
		
		
		adminProductService.pro_checked_modify2(pro_num_arr, pro_price_arr, pro_buy_arr);
		
		ResponseEntity<String> entity = null;
		entity = new ResponseEntity<>("success", HttpStatus.OK);
		
		return entity;
	}
			
	
}







