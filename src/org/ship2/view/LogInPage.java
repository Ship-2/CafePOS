package org.ship2.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.ship2.controller.LogInController;
import org.ship2.model.dto.EmployeeDTO;

public class LogInPage extends JPanel {

	private MainFrame mf; // 메인 프레임 객체 생성

	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel IdLabel;
	private JLabel PwLabel;

	
	/* 로그인 페이지 패널 */
	public LogInPage(MainFrame mainFrame) {
		this.mf = mainFrame;

		this.setSize(1280, 720);
		this.setBackground(Color.gray);
		this.setLayout(null);
		

		/* 아이디 입력필드 */
		textField = new JTextField();
		textField.setBounds(220, 180, 400, 170);
		add(textField);
		textField.setColumns(10);

		/* 비밀번호 입력필드 */
		passwordField = new JPasswordField();
		passwordField.setBounds(220, 380, 400, 170);
		add(passwordField);

		/* 로그인 버튼 */
		JButton logInButton = new JButton("로그인");
		logInButton.setBounds(758, 177, 434, 381);
		add(logInButton);

		IdLabel = new JLabel("ID");
		IdLabel.setBounds(22, 226, 152, 71);
		add(IdLabel);

		PwLabel = new JLabel("PASSWORD");
		PwLabel.setBounds(22, 432, 152, 71);
		add(PwLabel);

		mf.getContentPane().add(this);
		
		/* 로그인 버튼 액션 리스너 */
		logInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage mainpage = new MainPage(mf);
				changePanel(mainpage);
			}
		});
	}

	/* 패널 변경 메소드(메인메소드로) */
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.repaint();
	}
}
