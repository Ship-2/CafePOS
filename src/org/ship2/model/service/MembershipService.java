package org.ship2.model.service;

import static org.ship2.common.JDBCTemplate.close;
import static org.ship2.common.JDBCTemplate.commit;
import static org.ship2.common.JDBCTemplate.getConnection;
import static org.ship2.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import org.ship2.model.dao.MembershipDAO;
import org.ship2.model.dto.EmployeeDTO;
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
	
	public int deleteMember (String memCode) {
		Connection conn = getConnection();
		
		int deleteResult = 0;
		
		deleteResult = membershipDAO.deleteMember(conn, memCode);
		
		if (deleteResult > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return deleteResult;		
	}
	
	public int updateMember (MembershipDTO memDTO) {
		Connection conn = getConnection();
		
		int updateResult = 0;
		
		updateResult = membershipDAO.updateMember(conn, memDTO);
		
		if (updateResult > 0) {
			commit(conn);
		} else {
			rollback(conn);
		}
		
		close(conn);
		
		return updateResult;
	}
	

}
