package com.office.library.book.admin;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.office.library.book.BookVo;
import com.office.library.book.admin.util.UploadFileService;

@Controller
@RequestMapping(value = "/book/admin")
public class BookController {
	
	
	@Autowired
	BookService bookService;
	
	@Autowired
	UploadFileService uploadFileService;
	
	@GetMapping(value = "/registerBookForm")
	public String registerBookForm() {
		return "/admin/book/register_book_form";
	}
	
	@PostMapping(value = "/registerBookConfirm")
	public String registerBookConfirm(BookVo bookVo , @RequestParam("file") MultipartFile file) {
		
		String nextPage = "admin/book/register_book_ok";
		
		String savedFileName = uploadFileService.upload(file);
		
		if (savedFileName != null) {
			bookVo.setB_thumbnail(savedFileName);
			int result = bookService.registerBookConfirm(bookVo);
			
			if (result <= 0) {
				nextPage = "admin/member/register_book_ng";
			}
		}
		else {
			nextPage = "admin/member/register_book_ng";
		}
		return nextPage;
		
	} 

}
