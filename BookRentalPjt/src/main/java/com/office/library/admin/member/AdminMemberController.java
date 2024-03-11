package com.office.library.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin/member")
public class AdminMemberController {

	@Autowired
	AdminMemberService adminMemberService;


	@GetMapping("/createAccountForm")
	public String createAccountForm() {
		System.out.println("[AdminMemberController] createAccountForm");
		String nextPage = "admin/member/create_account_form";

		return nextPage;
	}

	@PostMapping("/createAccountConfirm")
	public String createAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberController] createAccountConfrim");
		int result = adminMemberService.createAccountConfirm(adminMemberVo);

		String nextPage = "admin/member/create_account_ok";

		if (result <=0) {
			return "admin/member/create_account_ng";
		}
		else {
			return nextPage;	
		}


	}
//	로그인창
	@GetMapping(value = "/loginForm")
	public String loginForm() {
		
		
		return "admin/member/login_form";
	}
	
	
	@PostMapping(value = "/loginConfirm")
	public String loginConfirm(AdminMemberVo adminMemberVo) {
		String nextpage = "admin/member/login_ok";
		AdminMemberVo loginAdminMemberVo = adminMemberService.loginConfirm(adminMemberVo);
		
		if (loginAdminMemberVo == null) {
			return "admin/member/login_ng";		
		}
//		else {
//			
//		}
		return nextpage;
	
	}
	


}
