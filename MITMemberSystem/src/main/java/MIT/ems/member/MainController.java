package MIT.ems.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
public class MainController {


	@Autowired
	MemberService memberService;

	@RequestMapping(value = "/newMember")
	public String newMember() {
		return "newMember";
	}

	@RequestMapping(value = "/makeNewMember")
	public String logConfirm(MemberVo memberVo) {
		System.out.println("id : " + memberVo.getsId());
		System.out.println("pw : " + memberVo.getsPw());
		System.out.println("name : " + memberVo.getsName());
		System.out.println("age : " + memberVo.getsAge());
		System.out.println("gender : " + memberVo.getsGender());
		System.out.println("major : " + memberVo.getsMajor());

		memberService.signUpConfirm(memberVo);
		return "SIGN_UP_OK";
	}

	@RequestMapping(value = "/LoginSys")
	public String loginMainPage() {
		return "LoginSys";
	}

	@RequestMapping(value = "SuccessLog")
	public String loginSys(MemberVo memberVo) {

		MemberVo signInedMember = memberService.signInCofrim(memberVo);
		
		if (signInedMember != null) {
			return "SuccessLog";
		}
		else {
			return "sign_In_ng";
		}

		
	}
	@RequestMapping(value = "/MainHome")
	public String MainHome() {
		
		return "MainHome";
	}
}
