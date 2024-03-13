BookRentalPjt (자료) 와 MyBookRentalPjt( 내가한것) 비교하기

세션 

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


MimeMessangePreparator 인터페이스
JavaMail MIME 메시지 준비를 위한 콜백 인터페이스이다
 
