package org.ship2.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import org.ship2.controller.LogInController;
import org.ship2.model.dto.EmployeeDTO;

import jdk.internal.misc.FileSystemOption;

public class MainPage extends JPanel{

	private MainFrame mf;
	public Boolean isManager = false;
	
	public MainPage(MainFrame mainFrame) {
		this.mf = mainFrame;
		this.setSize(1280, 720);
		this.setBackground(Color.BLUE);
		this.setLayout(null);
		
		/* EmployeeDTO 용 employee 만들기 */
		
		ImageIcon order = new ImageIcon("images/coffee-shop.png");
	
		JButton orderButton = new JButton("주문", order);
		orderButton.setBounds(140, 20, 453, 327);
		add(orderButton);
		
		JButton membershipButton = new JButton("멤버쉽");
		membershipButton.setBounds(140, 360, 453, 138);
		add(membershipButton);
		
		JButton salesButton = new JButton("매출");
		salesButton.setBounds(140, 520, 453, 138);
		add(salesButton);
		
		JButton menuManageButton = new JButton("메뉴 관리");
		menuManageButton.setBounds(660, 20, 420, 224);
		add(menuManageButton);
		
		// FROM 송언석 : 잠시 GUI 테스트 동안에 꼽사리좀 끼겠습니다.
		/* 직원 관리 버튼 */
		JButton employeeManageButton = new JButton("직원 관리");
		employeeManageButton.setBounds(660, 260, 420, 224);
		add(employeeManageButton);
		
		JButton hrButton = new JButton("출퇴근");
		hrButton.setBounds(660, 520, 188, 138);
		add(hrButton);
		
		JButton logoutButton = new JButton("로그아웃");
		logoutButton.setBounds(891, 520, 188, 138);
		add(logoutButton);
		
		
		/* 메뉴관리 페이지 */
		menuManageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isManager) {
				Menu menupage = new Menu(mf);
				changePanel(menupage);
				}
			}
		});
		
		/* 로그인 페이지로 돌아감 */
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LogInPage loginPage = new LogInPage(mf);
				changePanel(loginPage);
				System.out.println("로그아웃 하였습니다.");
			}
		});
		
		/* 주문페이지 */
		orderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderPage orderpage = new OrderPage(mf);
				changePanel(orderpage);
			}
		});
		
		
		/* 매출관리 페이지 */
		salesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isManager) {
				DailySalesGuiView salesFrame = new DailySalesGuiView();
				salesFrame.setVisible(true);
				}
			}
		});
		
//		/* 매출관리 페이지 */
//		salesButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				
//				DailySalesGuiView salesFrame = new DailySalesGuiView(mf);
//				changePanel(salesFrame);
//			}
//		});
		
		/* 직원관리 페이지 */
//		employeeManageButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				MemberPage memberPge = new MemberPage(mf);
//				changePanel(memberPge);
//			}
//		});
		
		// FROM 송언석 : 잠시 GUI 테스트 동안에 꼽사리좀 끼겠습니다.
		employeeManageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isManager) {
				EmployeeManagementPage empManagementPage =
						new EmployeeManagementPage(mf);
				changePanel(empManagementPage);
				}
			}
		});
		
		mf.add(this);
	}
	
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.repaint();
	}

	public List<EmployeeDTO> selectEmployee() {
		LogInController logInController = new LogInController();

		List<EmployeeDTO> employeeList = logInController.selectAllEmployee();
		return employeeList;
	}
	
//	public void checkManager() {
//		employeeList = selectEmployee();
//		for (int i = 0; i < employeeList.size(); i++) {
//			EmployeeDTO employee = employeeList.get(i);
//			if (employee.getJobCode() > 1) {		
//				this.setEnabled(false);
//			}
//		}
//	}
	
}