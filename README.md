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


html에서 form태그 속성중 enctype="mulitpartform-data"는 
이미지를 전달할때 사용된다.

UUID 
각 객체를 고유하게 식별 가능한 값을 말한다.


LibraryBeanNameGenerator
class 생성할때 add를 누르고 
BeanNameGenerator 검색후 인터페이스를 추가한다

BeanNameGenerator 사용하는 경우는 같은 이름의 Controller , Service , Dao를 사용해야하는경우 
BeanNameGenerator 를 사용한다

 
