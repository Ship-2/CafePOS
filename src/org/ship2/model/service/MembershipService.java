package org.ship2.model.service;

import static org.ship2.common.JDBCTemplate.close;
import static org.ship2.common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import org.ship2.model.dao.MembershipDAO;
import org.ship2.model.dto.MembershipDTO;

public class MembershipService {
	private MembershipDAO membershipDAO = new MembershipDAO();
	
	public List<MembershipDTO> selectAllMembers() {
		Connection conn = getConnection();
		
		List<MembershipDTO> memberList = membershipDAO.selectAllMembers(conn);
		
		close(conn);
		
		return memberList;
		
	}
	
	public int insertNewMember (MembershipDTO memDTO) {
		Connection conn = getConnection();
		
		int result = 0;
		
		result = membershipDAO.insertNewMember(conn, memDTO);
		
		
		close(conn);
		
		return result;
	}
	

}
