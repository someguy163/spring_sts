package com.office.library.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping (value = "/admin/member")
public class AdminMemberController {


	@Autowired
	AdminMemberService adminMemberService;
	@GetMapping(value = "createAccountForm")
	public String createAccountForm() {


		return "admin/member/create_account_form";
	}

	@PostMapping(value = "/createAccountConfirm")
	public String createAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("--------------------");
		System.out.println("createAccountConfrim");
		System.out.println("--------------------");
		int result = adminMemberService.createAccountConfirm(adminMemberVo);

		if (result<=0) {
			return "admin/member/create_account_ng";
		}
		else {
			return "admin/member/create_account_ok";			
		}

	}



}
