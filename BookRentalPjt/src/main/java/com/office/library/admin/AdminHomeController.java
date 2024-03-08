package com.office.library.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class AdminHomeController {

	@RequestMapping(value = {"","/"},method = RequestMethod.GET)
	public String home() {
		
		System.out.println("[AdmintHomeController] home()");
		String nextPage ="admin/home";
		return nextPage;
	}
}
