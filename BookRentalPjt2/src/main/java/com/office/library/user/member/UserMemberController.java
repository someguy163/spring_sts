package com.office.library.user.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.val;

@Controller
@RequestMapping(value = "/user/member")
public class UserMemberController {

	
	@Autowired
	UserMemberService userMemberService;
	
//	회원가입
	@GetMapping(value = "/createAccountForm")
	public String createAccountForm() {
		return "user/member/create_account_form";
	}
//	회원가입 확인
	@PostMapping(value = "/createAccountConfirm")
	public String createAccountConfirm(UserMemberVo userMemberVo) {
		
		int result = userMemberService.createAccountConfrim(userMemberVo);
		
		if (result <= 0) {
			return "user/member/create_account_ng";
		}
		return "user/member/create_account_ok";
	}
	
	
//	로그인 입력창
	@GetMapping(value = "/loginForm")
	public String loginForm() {
		
		return "user/member/login_form";
	}
	
//	로그인 버튼
	@PostMapping(value = "/loginConfirm")
	public String loginConfirm(UserMemberVo userMemberVo , HttpSession session) {
		
		String nextPage = "user/member/login_ok";
		
		UserMemberVo loginedUserMemberVo = userMemberService.loginConfirm(userMemberVo);
		
		if (loginedUserMemberVo == null) {
			nextPage = "user/member/login_ng";
		}
		else {
			
			session.setAttribute("loginedUserMemberVo", loginedUserMemberVo);
			session.setMaxInactiveInterval(60 * 30);
			nextPage = "user/member/login_ok";
		}
		
		return nextPage;
	}
	
//	계정정보 수정창으로 이동
	@GetMapping(value = "/modifyAccountForm")
	public String modifyAccountForm(HttpSession session) {
		
		UserMemberVo loginedUserMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		
		if (loginedUserMemberVo == null) {
			return "user/member/loginForm";
		}
		else {
			return "user/member/modify_account_form";	
		}
	}
	
//	계정정보 수정확인버튼
	@PostMapping(value = "/modifyAccountConfirm")
	public String modifyAccountConfirm(UserMemberVo userMemberVo , HttpSession session) {
		int result = userMemberService.modifyAccountConfirm(userMemberVo);
		if ( result > 0 ) {
//			정보수정하고 세션을 재설정하기 위함
			UserMemberVo loginedUserMemberVo = userMemberService.getLoginedUserMemberVo(userMemberVo.getU_m_no());
			session.setAttribute("loginedUserMemberVo", loginedUserMemberVo);
			session.setMaxInactiveInterval(60 * 30);
		}
		else {
			return "user/member/modify_account_ng";
		}
		return "user/member/modify_account_ok";
	}
	
//	로그아웃
	@GetMapping(value = "/logoutConfirm")
	public String logouConfirm(HttpSession session) {
		
		session.invalidate();
		return "redirect:/";
	}
}
