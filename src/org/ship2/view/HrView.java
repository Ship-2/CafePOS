package org.ship2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.ship2.controller.HrController;
import org.ship2.model.dto.EmployeeDTO;
import org.ship2.model.dto.HrDTO;

import javax.swing.JLabel;

public class HrView extends JPanel {
	
	private MainFrame mf; // 메인 프레임 객체 생성
	
	private String employeeColNames[] = {"이름", "전화번호"};	// 테이블 컬럼 배열 생성
	private String hrColNames[] = {"날짜", "출근시간", "퇴근시간"};
	
	private DefaultTableModel employeeModel = new DefaultTableModel(employeeColNames, 0);  // 테이블 모델 객체 생성(타이틀 포함)
	private DefaultTableModel hrModel = new DefaultTableModel(hrColNames, 0);	
	
	private JScrollPane employeeScrollpane = new JScrollPane();  // 테이블을 붙일 스크롤팬 생성
	private JScrollPane hrScrollpane = new JScrollPane();	

	private JTable employeeTable;
	private JTable hrTable;
	private List<EmployeeDTO> employeeList;
	private List<HrDTO> hrList;
	private int emp_code;
	private String hr_date;
	private int employeeIndex;
	private int hrIndex;

	private HrController hrController = new HrController();

	/* 직원 근퇴관리 패널 */
	public HrView(MainFrame mainFrame) {
		
		this.mf = mainFrame;

		this.setSize(1280, 720);
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(null);
		
		JButton mainBtn = new JButton("메인으로");
		mainBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		mainBtn.setBounds(1144, 10, 97, 86);
		this.add(mainBtn);
		
		JTable employeeTable = new JTable(employeeModel);
		employeeScrollpane = new JScrollPane(employeeTable);
		employeeScrollpane.setBounds(120, 150, 340, 350);
		this.add(employeeScrollpane);
		
		JTable hrTable = new JTable(hrModel);
		hrScrollpane = new JScrollPane(hrTable);
		hrScrollpane.setBounds(550, 150, 570, 350);
		this.add(hrScrollpane);
		
		JLabel titleLabel = new JLabel("직원 근퇴 관리");
		titleLabel.setFont(new Font("나눔고딕", Font.BOLD, 40));
		titleLabel.setBounds(492, 35, 551, 46);
		add(titleLabel);
		
		JButton clockInButton = new JButton("출근");
		clockInButton.setBounds(180, 550, 140, 70);
		clockInButton.setFont(new Font("나눔고딕", Font.BOLD, 20));
		add(clockInButton);
		
		JButton clockOutButton = new JButton("퇴근");
		clockOutButton.setBounds(400, 550, 140, 70);
		clockOutButton.setFont(new Font("나눔고딕", Font.BOLD, 20));
		add(clockOutButton);
		
		JButton updateButton = new JButton("수정");
		updateButton.setBounds(1000, 550, 140, 70);
		updateButton.setFont(new Font("나눔고딕", Font.BOLD, 20));
		add(updateButton);
		
		
		/* ====== ======= ==== 직원 리스트를 select 후 테이블 객체에 담기 ===== ======= ===== */
		employeeList = hrController.selectAllEmployee();
		for (int i = 0; i < employeeList.size(); i++) {
			EmployeeDTO employee = employeeList.get(i);
			
			Object recode1[] = new Object[2];
			recode1[0] = employee.getEmpName();
			recode1[1] = employee.getEmpPhone();

			employeeModel.addRow(recode1);
		}
		
		
		/* ========== employeeTable에서 선택한 직원의 근퇴 정보를 hrTable에 나타내기 ========== */
		employeeTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				//선택한 직원의 emp_code 알아내기
				employeeIndex = employeeTable.getSelectedRow();
				
				EmployeeDTO employee = employeeList.get(employeeIndex);
				emp_code = employee.getEmpCode(); 
//				System.out.println(emp_code);
				
				//해당 직원의 근퇴정보 select
				selectHr(emp_code);
			}
		});
		
		
		/* ===== ========== ===== 출근버튼 클릭 시 HR insert ===== ========== ===== */	
		clockInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int hrInsertResult = hrController.insertHr(emp_code);
				
				
				selectHr(emp_code);
			}
		});
		
		
		/* ===== ========== ===== 퇴근버튼 클릭 시 HR update ===== ========== ===== */
		clockOutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int clockOutUpdateResult = hrController.updateClockOut(emp_code);
				
				selectHr(emp_code);
			}
		});	
		
		
		/* ===== ========== ===== 수정버튼 클릭 시 HR update ===== ========== ===== */
		hrTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				hrIndex = hrTable.getSelectedRow();
				
			}
		});
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				HrDTO hrDTO = new HrDTO();
				hrDTO.setHrDate(hrModel.getValueAt(hrIndex, 0).toString());
				hrDTO.setEmpCode(emp_code);
				hrDTO.setClockIn(hrModel.getValueAt(hrIndex, 1).toString());
				hrDTO.setClockOut(hrModel.getValueAt(hrIndex, 2).toString());
								
				int hrUpdateResult = hrController.updateHr(hrDTO);
				
				selectHr(emp_code);
			}
		});	
		
		
		
		/* 메인화면으로 돌아갈 이벤트 */
		mainBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage mainpage = new MainPage(mf);
				changePanel(mainpage);
			}
		});

	}
	
	
	public void selectHr(int emp_code) {
		hrList = hrController.selectHrByEmpCode(emp_code);
		hrModel.setNumRows(0);
		for (int i = 0; i < hrList.size(); i++) {
			HrDTO hr = hrList.get(i);
			
			Object recode2[] = new Object[3];
			recode2[0] = hr.getHrDate();
			recode2[1] = hr.getClockIn();
			recode2[2] = hr.getClockOut();
			
			hrModel.addRow(recode2);
		}
	}
	
	
	/* 패널 변경 메소드(메인메소드로) */
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.getContentPane().add(panel);
		mf.repaint();
	}
}
