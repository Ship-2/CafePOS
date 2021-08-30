package org.ship2.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.ship2.controller.LogInController;
import org.ship2.model.dto.EmployeeDTO;

public class MainPage extends JPanel{

	private MainFrame mf;
	public Boolean isManager = false;
	
	public MainPage(MainFrame mainFrame) {
		this.mf = mainFrame;
		this.setSize(1280, 720);
		this.setBackground(Color.GRAY);
		this.setLayout(null);
		
		/* EmployeeDTO 용 employee 만들기 */
		
		ImageIcon order = new ImageIcon("images/order.png");
		ImageIcon membership = new ImageIcon("images/membership.png");
		ImageIcon sales = new ImageIcon("images/sales.png");
		ImageIcon menu = new ImageIcon("images/menu.png");
		ImageIcon employee = new ImageIcon("images/employee.png");
		ImageIcon hr = new ImageIcon("images/hr.png");
		ImageIcon logout = new ImageIcon("images/logout.png");
	
		JButton orderButton = new JButton("주문", order);
		orderButton.setBounds(140, 20, 453, 327);
		orderButton.setBackground(Color.GRAY);						// 버튼 객체의 배경 회색 지정
		orderButton.setBorder(new EmptyBorder(0, 0, 0, 0));			// 버튼 객체의 border 안보이게 하는 과정
		add(orderButton);
		
		JButton membershipButton = new JButton("멤버쉽", membership);
		membershipButton.setBounds(140, 360, 453, 138);
		membershipButton.setBackground(Color.GRAY);
		membershipButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(membershipButton);
		
		JButton salesButton = new JButton("매출", sales);
		salesButton.setBounds(140, 520, 453, 138);
		salesButton.setBackground(Color.GRAY);
		salesButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(salesButton);
		
		JButton menuManageButton = new JButton("메뉴 관리", menu);
		menuManageButton.setBounds(660, 20, 420, 224);
		menuManageButton.setBackground(Color.GRAY);
		menuManageButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(menuManageButton);
		
		// FROM 송언석 : 잠시 GUI 테스트 동안에 꼽사리좀 끼겠습니다.
		/* 직원 관리 버튼 */
		JButton employeeManageButton = new JButton("직원 관리", employee);
		employeeManageButton.setBounds(660, 260, 420, 224);
		employeeManageButton.setBackground(Color.GRAY);
		employeeManageButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(employeeManageButton);
		
		JButton hrButton = new JButton("출퇴근", hr);
		hrButton.setBounds(660, 520, 188, 138);
		hrButton.setBackground(Color.GRAY);
		hrButton.setBorder(new EmptyBorder(0, 0, 0, 0));
		add(hrButton);
		
		JButton logoutButton = new JButton("로그아웃", logout);
		logoutButton.setBounds(891, 520, 188, 138);
		logoutButton.setBackground(Color.GRAY);
		logoutButton.setBorder(new EmptyBorder(0, 0, 0, 0));
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
		
		
		/* 근퇴관리 페이지 */
		hrButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				HrView hrView = new HrView(mf);
				changePanel(hrView);
			}
		});
		
		/* 매출관리 페이지 */
		salesButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (isManager) {
				DailySalesGuiView dailySalesGuiView = new DailySalesGuiView(mf);
				changePanel(dailySalesGuiView);
//			}
				}
			}
		});
		
		
		/* 직원관리 페이지 */
		employeeManageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
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
	
}