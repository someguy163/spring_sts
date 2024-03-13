package com.office.library.admin.member;

import java.security.DrbgParameters.NextBytes;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.swing.border.CompoundBorder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	//	로그인 창
	@GetMapping(value = "/loginForm")
	public String loginForm() {

		return "admin/member/login_form";
	}

	//	로그인
	@PostMapping(value = "/loginConfirm")
	public String loginConfirm(AdminMemberVo adminMemberVo , HttpSession session) {
		AdminMemberVo loginedAdminMemberVo = adminMemberService.loginConfirm(adminMemberVo);

		if (loginedAdminMemberVo == null) {
			return "admin/member/login_ng";
		}
		else {
			session.setAttribute("loginedAdminMemberVo", loginedAdminMemberVo);
			session.setMaxInactiveInterval(60 * 30);
			return "admin/member/login_ok";
		}


	}

	//	로그아웃
	@GetMapping(value = "/logoutConfirm")
	public String logoutConfirm(HttpSession session) {
		session.removeAttribute("loginedAdminMemberVo");
		return "redirect:/admin";
	}

	//	관리자 목록 보기
	@GetMapping(value = "/listupAdmin")
	public ModelAndView listupAdmin() {

		List<AdminMemberVo> adminMemberVos = adminMemberService.listupAdmin();

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/member/listup_admins");
		modelAndView.addObject("adminMemberVos",adminMemberVos);

		return modelAndView;

	}


	//	관리자 승인부여하기
	@GetMapping(value = "/setAdminApproval")
	public String setAdminApproval(@RequestParam("a_m_no") int a_m_no) {

		adminMemberService.setAdminApproval(a_m_no);

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

	//	관리자 정보 수정하기
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
	
//	비밀번호 찾기 기능
	@GetMapping(value = "/findPasswordForm")
	public String findPasswordForm() {
		
		return "admin/member/find_password_form";
	}
	
//	새로운 비밀번호 메일요청
	@PostMapping(value = "/findPasswordConfirm")
	public String findPasswordConfirm(AdminMemberVo adminMemberVo) {
		int result = adminMemberService.findPasswordConfirm(adminMemberVo);
		
		if (result <=0) {
			return "admin/member/find_password_ng";
		}
		else {
			return "admin/member/find_password_ok";
		}
		
	}
	
	

}
