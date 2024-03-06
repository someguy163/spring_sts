package com.company.hello.member;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class MemberController {

	
	
	@RequestMapping(value = "/signIn")
	public String signIn(Model model) {
		return "signIn";
	}
	
	@RequestMapping(value = "/signUp")
	public String signUp(Model model) {
		return "signUp";
	}
}
