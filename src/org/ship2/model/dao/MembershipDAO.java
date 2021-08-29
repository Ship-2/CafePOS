package org.ship2.model.dao;

import static org.ship2.common.JDBCTemplate.close;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.ship2.model.dto.MembershipDTO;

public class MembershipDAO {
	private Properties prop = new Properties();

	
	public MembershipDAO() {
		
		try { 
			prop.loadFromXML(new FileInputStream("mapper/membership-query.xml."));
		} catch (IOException e) {
			e.printStackTrace();
		}
	} 
	
	public List<MembershipDTO> selectAllMembers(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		List<MembershipDTO> memberList=null;
		
		String query = prop.getProperty("selectAllMembers");
		
		try {
			pstmt = conn.prepareStatement(query);
			
			rset = pstmt.executeQuery();
			
			memberList = new ArrayList();
			
			while (rset.next()) {
				MembershipDTO membership = new MembershipDTO();
				membership.setMemCode(rset.getInt("MEM_CODE"));
				membership.setMemName(rset.getString("MEM_NAME"));
				membership.setMemPhone(rset.getString("MEM_PHONE"));
				membership.setMemPoint(rset.getInt("MEM_POINT"));
				membership.setMemYn(rset.getString("MEM_YN"));
				
				memberList.add(membership);
				
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}

		return memberList;

		}
	
	public int insertNewMember(Connection conn, MembershipDTO mem) {
		PreparedStatement pstmt = null;
		int createResult = 0;
		
		String query = prop.getProperty("insertNewMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, mem.getMemName());
			pstmt.setString(2, mem.getMemPhone());
			createResult = pstmt.executeUpdate();
			
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return createResult;
	}
	
	public int deleteMember(Connection conn, String memCode) {
		PreparedStatement pstmt = null;
		int deleteResult = 0;
		
		String query = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memCode);
			
			deleteResult = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return deleteResult;
	
	}
	
	public int updateMember (Connection conn, MembershipDTO memDTO) {
		PreparedStatement pstmt = null;
		int updateResult = 0; 
		
		String query = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, memDTO.getMemName());
			pstmt.setString(2, memDTO.getMemPhone());
			pstmt.setString(3, memDTO.getMemYn());
			pstmt.setInt(4, memDTO.getMemCode());
			updateResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updateResult;
	}
	
	public MembershipDTO selectMem(Connection con ,String memPhone) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		MembershipDTO membershipDTO = new MembershipDTO();
		
		String query = prop.getProperty("selectMem");
		
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, memPhone);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				membershipDTO.setMemCode(rset.getInt("MEM_CODE"));
				membershipDTO.setMemName(rset.getString("MEM_NAME"));
				membershipDTO.setMemPhone(rset.getString("MEM_PHONE"));
				membershipDTO.setMemPoint(rset.getInt("MEM_POINT"));
				membershipDTO.setMemYn(rset.getString("MEM_YN"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} 
		return membershipDTO;
	}
	
	public int updateMemberPoint(Connection conn, MembershipDTO memDTO) {
		PreparedStatement pstmt = null;
		int updateResult = 0; 
		
		String query = prop.getProperty("updateMemberPoint");
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, memDTO.getMemPoint());
			pstmt.setInt(2, memDTO.getMemCode());
			updateResult = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		} 
		
		return updateResult;
	}

	
}
	



