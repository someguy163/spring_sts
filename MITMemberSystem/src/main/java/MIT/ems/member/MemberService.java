package MIT.ems.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MemberService {

	@Autowired
	MemberDao memberDao;
	public int signUpConfirm(MemberVo memberVo) {
		memberDao.insert(memberVo);
		
		return 0;
	}
	
	public MemberVo signInCofrim(MemberVo memberVo) {
		
		MemberVo signInedMember = memberDao.selectMember(memberVo);
		
		return signInedMember;
	}
}
