package MIT.ems.member;

import java.util.HashMap;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;


@Component
public class MemberDao {

	private Map<String, MemberVo> memberDB = new HashMap<String, MemberVo>();

	public void insert(MemberVo memberVo) {
		System.out.println("memberDao insert");
		System.out.println("id : " + memberVo.getsId());
		System.out.println("pw : " + memberVo.getsPw());
		memberDB.put(memberVo.getsId(), memberVo);
		
		printAllBember();

	}
	
	public void printAllBember() {
		System.out.println("모든 멤버 출력");
		
		Set<String> keys = memberDB.keySet();
		Iterator<String> iterator = keys.iterator();
		
		while (iterator.hasNext()) {
			String key = iterator.next();
			MemberVo member = memberDB.get(key);
			
			System.out.println("ID : " + member.getsId());
			System.out.println("PW : " + member.getsPw());
			System.out.println("NAME : " + member.getsName());
			System.out.println("AGE : " + member.getsAge());
			System.out.println("GENDER : " + member.getsGender());
			System.out.println("MAJOR : " + member.getsMajor());
			
		}
	}
	
	public MemberVo selectMember(MemberVo memberVo) {
		
		MemberVo signedMember = memberDB.get(memberVo.getsId());
		if (signedMember != null && memberVo.getsPw().equals(signedMember.getsPw())) {
			return signedMember;
		}
		else {
			return null;
		}
		
	}


}
