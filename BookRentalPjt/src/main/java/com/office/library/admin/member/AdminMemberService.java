package com.office.library.admin.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminMemberService {

	final static public int ADMIN_ACCOUNT_ALREADY_EXIST=0;
	final static public int ADMIN_ACCOUNT_ALREADY_SUCCESS=1;
	final static public int ADMIN_ACCOUNT_ALREADY_FAIL=-1;

	@Autowired
	AdminMemberDao adminMemberDao;

	public int createAccountConfirm(AdminMemberVo adminMemberVo) {
		System.out.println("[AdminMemberService] createAccountConfirm");

		boolean isMember = adminMemberDao.isAdminMember(adminMemberVo.getA_m_id());

		if (!isMember) {
			int result = adminMemberDao.insertAdminAccount(adminMemberVo);

			if (result>0) {
				return ADMIN_ACCOUNT_ALREADY_SUCCESS;
			}
			else {
				return ADMIN_ACCOUNT_ALREADY_FAIL;
			}
		}
		else {
			return ADMIN_ACCOUNT_ALREADY_EXIST;
		}
	}
	
	public AdminMemberVo loginConfirm(AdminMemberVo adminMemberVo) {
		
		System.out.println("[AdminMemberService] loginConfirm");
		
		AdminMemberVo loginedAdminMembervo = adminMemberDao.selectAdmin(adminMemberVo);
		
		if (loginedAdminMembervo !=null) {
			System.out.println("[AdminMemberService] Admin Member Login Success");
		}
		else {
			System.out.println("[AdminMemberService] Admin Member Login Fail");	
		}
		
		
		return loginedAdminMembervo;
	}

}
