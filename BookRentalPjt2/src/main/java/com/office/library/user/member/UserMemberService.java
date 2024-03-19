package com.office.library.user.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMemberService {

	final static public int USER_ACCOUNT_ALREADY_EXIST = 0;
	final static public int USER_ACCOUNT_CREATE_SUCCESS = 1;
	final static public int USER_ACCOUNT_CREATE_FAIL = -1;
	@Autowired
	UserMemberDao userMemberDao;

	public int createAccountConfrim(UserMemberVo userMemberVo) {

		boolean isMember = userMemberDao.isUserMember(userMemberVo.getU_m_id());

		if (!isMember) {

			int result = userMemberDao.insertUserAccount(userMemberVo);

			if (result > 0) {
				return USER_ACCOUNT_CREATE_SUCCESS;	
			}
			else {
				return USER_ACCOUNT_CREATE_FAIL;
			}
		}
		else {
			return USER_ACCOUNT_ALREADY_EXIST;	
		}

	}
	
	public UserMemberVo loginConfirm(UserMemberVo userMemberVo) {
		UserMemberVo loginedUserMemberVo = userMemberDao.selectUser(userMemberVo);
		
		if (loginedUserMemberVo !=null) {
			System.out.println("Login Success");
		}
		else {
			System.out.println("Login Fail");
		}
		
		return loginedUserMemberVo;
	}
	
	public int modifyAccountConfirm(UserMemberVo userMemberVo) {
		return userMemberDao.updateUserAccount(userMemberVo);
	}
	
	public UserMemberVo getLoginedUserMemberVo(int u_m_no) {
		return userMemberDao.selectUser(u_m_no);
	}

}
