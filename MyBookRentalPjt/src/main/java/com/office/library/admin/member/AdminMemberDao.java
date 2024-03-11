package com.office.library.admin.member;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
@Component
public class AdminMemberDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	PasswordEncoder passwordEncoder;

	public boolean checkAdminMember(String a_m_id) {
		System.out.println("----------------");
		System.out.println("checkAdminMember");
		System.out.println("----------------");

		String checkSql = "SELECT COUNT(*) FROM tbl_my_admin_member WHERE a_m_id = ?";

		int result = jdbcTemplate.queryForObject(checkSql, Integer.class,a_m_id);

		if (result>0) {
			return true;
		}
		else {
			return false;	
		}
	}

	public int insertInfo(AdminMemberVo adminMemberVo) {
		System.out.println("----------------");
		System.out.println("insertInfo");
		System.out.println("----------------");

		List<String> args = new ArrayList<String>();

		String insertSql = "INSERT INTO tbl_my_admin_member(";

		if (adminMemberVo.getA_m_id().equals("super admin")) {
			insertSql = insertSql + "a_m_approval, ";
			args.add("1");
		}

		insertSql += "a_m_id, ";
		args.add(adminMemberVo.getA_m_id());

		insertSql += "a_m_pw, ";
		args.add(passwordEncoder.encode(adminMemberVo.getA_m_pw()));

		insertSql += "a_m_name, ";
		args.add(adminMemberVo.getA_m_name());

		insertSql += "a_m_gender, ";
		args.add(adminMemberVo.getA_m_gender());

		insertSql += "a_m_part, ";
		args.add(adminMemberVo.getA_m_part());

		insertSql += "a_m_position, ";
		args.add(adminMemberVo.getA_m_position());

		insertSql += "a_m_mail, ";
		args.add(adminMemberVo.getA_m_mail());

		insertSql += "a_m_phone, ";
		args.add(adminMemberVo.getA_m_phone());

		insertSql += "a_m_reg_date, a_m_mod_date) ";

		if (adminMemberVo.getA_m_id().equals("super admin")) {
			insertSql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
		}
		else {
			insertSql += "VALUES(?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";
		}

		int result =-1;

		try {
			result = jdbcTemplate.update(insertSql,args.toArray());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}



}
