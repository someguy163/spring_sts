package com.office.library.admin.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public String loginConfirm(AdminMemberVo adminMemberVo , HttpSession session) {
		String nextpage = "admin/member/login_ok";
		AdminMemberVo loginedAdminMemberVo = adminMemberService.loginConfirm(adminMemberVo);
		
		if (loginedAdminMemberVo == null) {
			return "admin/member/login_ng";		
		}
//		세선
		else { 
			session.setAttribute("loginedAdminMemberVo", loginedAdminMemberVo);
			session.setMaxInactiveInterval(60 * 30);
			System.out.println("세션확인하기");
		}
		return nextpage;
	
	}
	
	@GetMapping(value = "/logoutConfirm")
	public String logoutConfirm(HttpSession session) {
		
		System.out.println("[AdminMemberController] logoutConfirm");
		
		
//		2개다 같은방법이다 1번은 특정 세션 지우기
//		2번은 Array형식일떄 사용하면 된다
		session.removeAttribute("loginedAdminMemberVo");
//		session.invalidate();
		
		return "redirect:/admin";
	}
	
	
//	관리자 목록 Model 사용
//	@GetMapping(value = "/listupAdmin")
//	public String listupAdmin(Model model) {
//		System.out.println("[AdminMemberService] listupAdmin");
//		
//		String nextPage = "admin/member/listup_admins";
//		
//		List<AdminMemberVo> adminMemberVos = adminMemberService.listupAdmin();
//		
//		model.addAttribute("adminMemberVos",adminMemberVos);
//		return nextPage;
//	}
	
//	관리자 목록 ModelAndView 사용
	@GetMapping(value = "/listupAdmin")
	public ModelAndView listupAdmin(Model model) {
		System.out.println("[AdminMemberService] listupAdmin");
		
		String nextPage = "admin/member/listup_admins";
		
		List<AdminMemberVo> adminMemberVos = adminMemberService.listupAdmin();
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(nextPage);
		modelAndView.addObject("adminMemberVos",adminMemberVos);
		
		
		return modelAndView;
	}
	
	@GetMapping(value = "/setAdminApproval")
	public String setAdminApproaval(@RequestParam("a_m_no") int a_m_no) {
		System.out.println("[AdminMemberController] setAdminApproval");
		
		adminMemberService.setAdminApproaval(a_m_no);
		
		return "redirect:/admin/member/listupAdmin";
		
		
	}
	
//	계정수정
	@GetMapping(value = "/modifyAccountForm")
	public String modifyAccountForm(HttpSession session) {
		String nextPage ="admin/member/modify_account_form";
		AdminMemberVo loginedAdminMemberVo = (AdminMemberVo) session.getAttribute("loginedAdminMemberVo");
		
		if (loginedAdminMemberVo == null) {
			nextPage = "redirect:/admin/member/loginForm";
		}
		return nextPage;
	}
	
	@PostMapping(value = "/modifyAccountConfirm")
	public String modifyAccountConfirm(AdminMemberVo adminMemberVo , HttpSession session) {
		
		String nextPage = "admin/member/modify_account_ok";
		
		int result = adminMemberService.modifyAccountConfirm(adminMemberVo);
		
		if (result > 0) {
			
			AdminMemberVo loginedAdminMemberVo = adminMemberService.getLoginedAdminMemberVo(adminMemberVo.getA_m_no());
			
			session.setAttribute("loginedAdminMemberVo", loginedAdminMemberVo);
			session.setMaxInactiveInterval(60 * 30);	
		}
		else {
			nextPage ="admin/member/ng";
		}
		return nextPage;
	}
	
	@GetMapping(value = "/findPasswordForm")
	public String findPasswordForm() {
		String nextPage = "admin/member/find_password_form";
		return nextPage;
	}
	@PostMapping(value = "/findPasswordConfirm")
	public String findPasswordConfirm(AdminMemberVo adminMemberVo) {
		String nextPage = "admin/member/find_password_ok";
		
		int result = adminMemberService.findPasswordConfirm(adminMemberVo);
		
		if (result <=0) {
			nextPage = "admin/member/find_password_ng";
		}
		return nextPage;
	}
	


}
