package MIT.ems.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {


	@Autowired
	MemberDao memberDao;

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

		memberDao.insert(memberVo);
		return "SuccessLog";
	}

	@RequestMapping(value = "/LoginSys")
	public String loginMainPage() {
		return "LoginSys";
	}

	@RequestMapping(value = "SuccessLog")
	public String loginSys() {


		return "SuccessLog";
	}
}
