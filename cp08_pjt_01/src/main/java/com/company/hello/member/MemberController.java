package com.company.hello.member;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Controller
public class MemberController {
//	MemberService memberService = new MemberService();
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/signIn")
	public String signIn(Model model) {
		return "sign_In";
	}
	
	@RequestMapping(value = "/signUp")
	public String signUp(Model model) {
		return "signUp";
	}
//	@RequestMapping(value = "/signUpConfirm")
//	public String singUpConfirm() {
//		System.out.println("[Member Controller] signUpConfrim");
//		return null;
//	}
	
	
//	@RequestMapping(value = "/signUpConfirm")
//	public String signUpConfrim(@RequestParam String m_id
//			,@RequestParam int m_pw
//			,@RequestParam String m_mail
//			,@RequestParam String m_phone) {
//		System.out.println("[Member Controller] signUpConfrim");
//		
//		System.out.println("m_id : " + m_id);
//		System.out.println("m_pw : " + m_pw);
//		System.out.println("m_pw type : " + ((Object)m_pw).getClass().getName());
//		System.out.println("m_mail : " + m_mail);
//		System.out.println("m_phone : " + m_phone);
//		
//		return null;
//	}
	
	@RequestMapping(value = "/signUpConfirm")
	public String signUpConfrim(MemberVo memberVo) {
		
		
		System.out.println("[Member Controller] signUpConfrim");
		System.out.println("m_id : " + memberVo.getM_id());
		System.out.println("m_pw : " + memberVo.getM_pw());
		System.out.println("m_mail : " + memberVo.getM_mail());
		System.out.println("m_phone : " + memberVo.getM_phone());
		memberService.signUpConfirm(memberVo);
		
		return "sign_Up_ok";
	}
	
	@RequestMapping(value = "/signInConfirm")
	public String signInConfirm(MemberVo memberVo) {
		
		MemberVo signInedMember = memberService.signInCofrim(memberVo);
		
		if (signInedMember !=null) {
			return "sign_In_ok";	
		}
		return "sign_In_ng";
	}
}
