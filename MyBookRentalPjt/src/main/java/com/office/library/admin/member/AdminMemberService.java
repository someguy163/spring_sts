package com.office.library.admin.member;

import java.util.List;

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
	
	public AdminMemberVo loginConfirm(AdminMemberVo adminMemberVo) {
		
		AdminMemberVo loginedAdminMemberVo = adminMemberDao.selectAdmin(adminMemberVo);
		
		if (loginedAdminMemberVo !=null) {
			System.out.println("AdminMemberService loginConfrim Success");
		}
		else {
			System.out.println("AdminMemberService loginConfrim Fail");	
		}
		
		return loginedAdminMemberVo;
	}
	
	public List<AdminMemberVo> listupAdmin() {
		
		return adminMemberDao.selectAdmins();
	}
	
	public int setAdminApproval(int a_m_no) {
		
	return adminMemberDao.updateAdminAccount(a_m_no);
	}

}
