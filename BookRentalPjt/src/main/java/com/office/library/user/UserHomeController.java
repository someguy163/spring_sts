package com.office.library.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserHomeController {

	@GetMapping(value = {"/",""})
	public String home() {
		System.out.println("userHome");
		return "user/home";
	}
}
