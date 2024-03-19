package com.office.library.book.admin;

import java.util.List;

import javax.mail.search.StringTerm;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.office.library.admin.member.AdminMemberVo;
import com.office.library.book.BookVo;
import com.office.library.book.admin.util.UploadFileService;

@Controller
@RequestMapping(value = "/book/admin")

public class BookController {


	@Autowired
	UploadFileService uploadFileService;
	@Autowired
	BookService bookService;
	@GetMapping(value = "/registerBookForm")
	public String registerBookForm() {
		System.out.println("[BookController] registerBookForm");
		return "admin/book/register_book_form";
	}

	@PostMapping(value = "/registerBookConfirm")
	public String registerBookConfirm(BookVo bookVo ,@RequestParam("file") MultipartFile file) {

		String nextPage ="admin/book/register_book_ok";

		String savedFileName = uploadFileService.upload(file);

		if (savedFileName != null) {
			bookVo.setB_thumbnail(savedFileName);
			int result = bookService.registerBookConfirm(bookVo);

			if (result <= 0) {
				nextPage = "admin/book/register_book_ng";

			}
		}
		else {
			nextPage ="admin/book/register_book_ok";
		}
		return nextPage;
	}

	
//	검색해서 도서 출력
	@GetMapping(value = "/searchBookConfirm")
	public String searchBookConfirm(BookVo bookVo , Model model) {
		String nextPage = "admin/book/search_book";

		List<BookVo> bookVos = bookService.searchBookConfirm(bookVo);

		model.addAttribute("bookVos",bookVos);

		return nextPage;

	}
//	검색해서 출력된 도서 상세보기
	@GetMapping(value = "/bookDetail")
	public String bookDetail(@RequestParam("b_no") int b_no , Model model) {

		BookVo bookVo = bookService.bookDetail(b_no);

		model.addAttribute("bookVo",bookVo);

		return "admin/book/book_detail";
	}
	
	@GetMapping(value = "/modifyBookForm")
	public String modifyBookForm(@RequestParam("b_no") int b_no , Model model , HttpSession session) {
		
		String nextPage = "admin/book/modify_book_form";
		
		AdminMemberVo loginedAdminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
		
		if (loginedAdminMemberVo == null) {
			return "redirect:/admin/member//loginForm";
		}
		
		BookVo bookVo = bookService.modifyBookForm(b_no);
		
		model.addAttribute("bookVo",bookVo);
		
		return nextPage;
	}
	
	@PostMapping(value = "/modifyBookConfirm")
	public String modifyBookConfirm(BookVo bookVo , @RequestParam("file") MultipartFile file , HttpSession session) {
		String nextPage = "admin/book/modify_book_ok";
		
		AdminMemberVo loginedAdminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
		
		if (loginedAdminMemberVo == null) 
			return "redirect:/admin/member//loginForm";
		
		
		if (!file.getOriginalFilename().equals("")) {
			
			String savedFileName = uploadFileService.upload(file);
			if (savedFileName != null) 
				bookVo.setB_thumbnail(savedFileName);
			
		}
			int result = bookService.modifyBookConfirm(bookVo);
			
			if (result <= 0) 
				nextPage ="admin/book/modify_book_ng";
			
		return nextPage;
	}
	
	@GetMapping(value = "/deleteBookConfirm")
	public String deleteBookConfirm(@RequestParam("b_no") int b_no , HttpSession session) {
		
		String nextPage = "admin/book/delete_book_ok";
		
		AdminMemberVo loginedAdminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
		
		if (loginedAdminMemberVo == null) 
			return "redirect:/admin/member//loginForm";
		
		int result = bookService.deleteBookConfirm(b_no);
		
		if (result <= 0) {
			nextPage ="admin/book/delete_book_ng";
		}
		
		return nextPage;
	}
	
	

}
