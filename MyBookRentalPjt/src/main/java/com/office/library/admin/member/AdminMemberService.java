package com.office.library.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

	@Autowired
	AdminMemberDao adminMemberDao;

	public int createAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("--------------------");
		System.out.println("createAccountConfirm");
		System.out.println("--------------------");

		boolean isMember = adminMemberDao.checkAdminMember(adminMemberVo.getA_m_id());
		
		if (!isMember) {
			int result = adminMemberDao.insertInfo(adminMemberVo);
			
			if (result>0) {
				return 1;
			}
			else {
				return -1;
			}
		}
		else {
			return 0;
		}
	}

}
