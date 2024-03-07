package com.company.hello.member;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
@Component
public class MemberDao {

	
	private Map<String , MemberVo> memberDB = new HashMap<String, MemberVo>();
	
	public void insertMember(MemberVo memberVo) {
		System.out.println("[Member Dao insertMember()]");
		
		System.out.println("m_id : " + memberVo.getM_id());
		System.out.println("m_pw : " + memberVo.getM_pw());
		System.out.println("m_mail : " + memberVo.getM_mail());
		System.out.println("m_phone : " + memberVo.getM_phone());
		
		memberDB.put(memberVo.getM_id(), memberVo);
		printAllMember();
	}
	
	public void printAllMember() {
		System.out.println("[MemberDao printAllMember]");
		
		Set<String> keys = memberDB.keySet();
		Iterator<String> iterator = keys.iterator();
		
		while (iterator.hasNext()) {
			String key = iterator.next();
			MemberVo memberVo = memberDB.get(key);
			
			System.out.println("m_id : " + memberVo.getM_id());
			System.out.println("m_pw : " + memberVo.getM_pw());
			System.out.println("m_mail : " + memberVo.getM_mail());
			System.out.println("m_phone : " + memberVo.getM_phone());
			
		}
		
	}
	
	public MemberVo selectMember(MemberVo memberVo) {
		System.out.println("[MemberDao] selectMember");
		MemberVo signedMember = memberDB.get(memberVo.getM_id());
		
		if (signedMember !=null && memberVo.getM_pw().equals(signedMember.getM_pw())) {
			return signedMember;
		}
		else {
			return null;
		}
		
	}
}
