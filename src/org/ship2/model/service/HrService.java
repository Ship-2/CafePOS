package org.ship2.model.service;

import java.sql.Connection;
import java.util.List;

import org.ship2.model.dao.HrDAO;
import org.ship2.model.dto.EmployeeDTO;
import org.ship2.model.dto.HrDTO;

import static org.ship2.common.JDBCTemplate.close;
import static org.ship2.common.JDBCTemplate.commit;
import static org.ship2.common.JDBCTemplate.getConnection;
import static org.ship2.common.JDBCTemplate.rollback;

public class HrService {

	private HrDAO hrDAO = new HrDAO();
	
	public List<EmployeeDTO> selectAllEmployee() {

		Connection con = getConnection();
		
		List<EmployeeDTO> employeeList = hrDAO.selectAllEmployee(con);
		
		close(con);
		
		return employeeList;
	}

	public List<HrDTO> selectHrByEmpCode(int emp_code) {
		Connection con = getConnection();
		
		List<HrDTO> hrList = hrDAO.selectHrByEmpCode(con, emp_code);
		
		close(con);
		return hrList;
	}

	public int insertHr(int emp_code) {

		Connection con = getConnection();
		
		int hrInsertResult = hrDAO.insertHr(con, emp_code);
		
		if(hrInsertResult > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return hrInsertResult;
	}

	public int updateClockOut(int emp_code) {

		Connection con = getConnection();
		
		int clockOutUpdateResult = hrDAO.updateClockOut(con, emp_code);
		
		if(clockOutUpdateResult > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return clockOutUpdateResult;
	}

	public int updateHr(HrDTO hrDTO) {
		
		Connection con = getConnection();
		
		int hrUpdateResult = hrDAO.updateHr(con, hrDTO);
		
		if(hrUpdateResult > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		close(con);
		
		return hrUpdateResult;
	}
}
