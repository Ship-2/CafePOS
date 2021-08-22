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
		
		this.add(menuButton);
		this.add(orderButton);
		this.add(memberButton);
		
		
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
		
		mf.add(this);
	}
	
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.repaint();
	}
}	