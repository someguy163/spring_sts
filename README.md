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
