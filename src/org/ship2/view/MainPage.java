package org.ship2.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class MainPage extends JPanel{

	private MainFrame mf;
	
	public MainPage(MainFrame mainFrame) {
		this.mf = mainFrame;
		
		this.setSize(1280, 720);
		this.setBackground(Color.BLUE);
		this.setLayout(null);
		
		JButton menuButton = new JButton();
		menuButton.setText("메뉴 관리");
		menuButton.setBounds(100,200,100,100);
		
		JButton orderButton = new JButton();
		orderButton.setText("주문 관리");
		orderButton.setBounds(400,200,100,100);
		
		JButton memberButton = new JButton();
		memberButton.setText("회원관리");
		memberButton.setBounds(700,200,100,100);
		
		// FROM 송언석 : 잠시 GUI 테스트 동안에 꼽사리좀 끼겠습니다.
		JButton employeeButton = new JButton();
		employeeButton.setText("직원 관리");
		employeeButton.setBounds(100,400,100,100);
		
		this.add(menuButton);
		this.add(orderButton);
		this.add(memberButton);
		// FROM 송언석 : 잠시 GUI 테스트 동안에 꼽사리좀 끼겠습니다.
		this.add(employeeButton);
		
		menuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Menu menupage = new Menu(mf);
				changePanel(menupage);
			}
		});
//		orderButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				OrderPage orderpage = new OrderPage(mf);
//				changePanel(orderpage);
//			}
//		});
//		memberButton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				MemberPage memberPge = new MemberPage(mf);
//				changePanel(memberPge);
//			}
//		});
		
		// FROM 송언석 : 잠시 GUI 테스트 동안에 꼽사리좀 끼겠습니다.
		employeeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EmployeeManagementPage empManagementScreen =
						new EmployeeManagementPage(mf);
				changePanel(empManagementScreen);
			}
		});
		
		mf.add(this);
	}
	
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.repaint();
	}
}	