package org.ship2.controller;

import java.util.List;
import java.util.Map;

import javax.swing.table.DefaultTableModel;

import org.ship2.model.dto.EmployeeDTO;
import org.ship2.model.service.EmployeeService;
import org.ship2.view.EmployeeResultView;

public class EmployeeController {
	private EmployeeService employeeService = new EmployeeService();
	private EmployeeResultView employeeResultView = new EmployeeResultView();
	private DefaultTableModel tableModel = 
			new DefaultTableModel(new String[] {"이름", "직급", "연락처"}, 0) {
				@Override
				public boolean isCellEditable(int row, int column) {
					if (column >= 0)
						return false;
					else
						return true;
				}
			};
	private Object[] tuples;
	private List<EmployeeDTO> empList;
	private boolean isInsertSuccess = false;
	private boolean isSelectSuccess = false;
	private boolean isUpdateSuccess = false;
	private boolean isDeleteSuccess = false;

	public void selectAllEmployees() {
		List<EmployeeDTO> employeeList = employeeService.selectAllEmployees();
		
		if (!employeeList.isEmpty()) {
			this.isInsertSuccess = true;
			employeeResultView.displayDmlResult("selectSuccess");
			employeeResultView.display(employeeList);
			addRowToModel(employeeList);
		} else {
			this.isInsertSuccess = false;
			employeeResultView.displayDmlResult("selectFailed");
		}
		
	}

	public void addRowToModel(List<EmployeeDTO> employeeList) {
		this.empList = employeeList;
		
		for (int i = 0; i < employeeList.size(); i ++) {
			tuples = new Object[3];
			EmployeeDTO emp = employeeList.get(i);
			String jobName = "";
			
			this.tuples[0] = emp.getEmpName();
			switch (emp.getJobCode()) {
			case 1:
				jobName = "관리자"; break;
			case 2:
				jobName = "직원"; break;
			case 3:
				jobName = "수습"; break;
			}
			this.tuples[1] = jobName;
			this.tuples[2] = emp.getEmpPhone();
			
			this.tableModel.addRow(tuples);
		}
		
	}

	public void registerNewEmployee(Map<String, String> empInfoMap) {
		EmployeeDTO empDTO = new EmployeeDTO();
		
		empDTO.setEmpName(empInfoMap.get("name"));
		empDTO.setEmpPhone(empInfoMap.get("phone"));
		empDTO.setJobCode(Integer.valueOf(empInfoMap.get("jobCode")));
		empDTO.setEmpId(empInfoMap.get("id"));
		empDTO.setEmpPw(empInfoMap.get("pw"));
		
		int createResult = employeeService.insertNewEmployee(empDTO);
			
		if (createResult > 0) {
			this.isInsertSuccess = true;
			employeeResultView.displayDmlResult("insertSuccess");
		} else {
			this.isInsertSuccess = false;
			employeeResultView.displayDmlResult("insertFailed");
		}
		
	}

	public void deleteEmployee(String employeeId) {
		int deleteResult = employeeService.deleteEmployee(employeeId);
		
		if (deleteResult > 0) {
			this.isDeleteSuccess = true;
			employeeResultView.displayDmlResult("deleteSuccess");
		} else {
			this.isDeleteSuccess = false;
			employeeResultView.displayDmlResult("deleteFailed");
		}
	
	}

	/**
	 * @param empIdFromUser ID of the employee to be updated.
	 * @param empInfoMap	Information which will be updated.
	 */
	public void modifyEmployee(String empIdFromUser, Map<String, String> empInfoMap) {
		EmployeeDTO empDTO = new EmployeeDTO();
		
		empDTO.setEmpName(empInfoMap.get("name"));
		empDTO.setEmpPhone(empInfoMap.get("phone"));
		empDTO.setJobCode(Integer.valueOf(empInfoMap.get("jobCode")));
		empDTO.setEmpId(empInfoMap.get("id"));
		empDTO.setEmpPw(empInfoMap.get("pw"));
		
		int updateResult = employeeService.updateEmployeeInfo(empIdFromUser, empDTO);
		
		if (updateResult > 0) {
			this.isUpdateSuccess = true;
			employeeResultView.displayDmlResult("updateSuccess");
		} else {
			this.isUpdateSuccess = false;
			employeeResultView.displayDmlResult("updateFailed");
		}
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public void setTableModel(DefaultTableModel tableModel) {
		this.tableModel = tableModel;
	}

	public List<EmployeeDTO> getEmpList() {
		return empList;
	}

	public void setEmpList(List<EmployeeDTO> empList) {
		this.empList = empList;
	}
	
	public boolean isInsertSuccess() {
		return isInsertSuccess;
	}

	public void setInsertSuccess(boolean isInsertSuccess) {
		this.isInsertSuccess = isInsertSuccess;
	}

	public boolean isSelectSuccess() {
		return isSelectSuccess;
	}

	public void setSelectSuccess(boolean isSelectSuccess) {
		this.isSelectSuccess = isSelectSuccess;
	}

	public boolean isUpdateSuccess() {
		return isUpdateSuccess;
	}

	public void setUpdateSuccess(boolean isUpdateSuccess) {
		this.isUpdateSuccess = isUpdateSuccess;
	}

	public boolean isDeleteSuccess() {
		return isDeleteSuccess;
	}

	public void setDeleteSuccess(boolean isDeleteSuccess) {
		this.isDeleteSuccess = isDeleteSuccess;
	}

}
