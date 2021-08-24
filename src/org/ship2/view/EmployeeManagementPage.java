package org.ship2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * @author Unsuk Song
 *
 */
public class EmployeeManagementPage extends JPanel {
	private MainFrame mf;
	private Scanner sc = new Scanner(System.in);
	
	/**
	 * Default Ctor
	 * @Note DO NOT USE!!
	 */
	public EmployeeManagementPage() {
	}
	
	
	/**
	 * 메인 프레임을 전달 인자로 받는 직원 관리 화면 생성자.
	 * 일단 여기에 다 때려넣어본다.
	 * 
	 * @param mainFrame		
	 */
	public EmployeeManagementPage(MainFrame mainFrame) {
		/* ------------------------------------------------------
		 * |  [ CONTAINER ]									 	 |
		 * ---------------------------------------------------- */
		this.mf = mainFrame;
		this.setSize(1280, 720);
		this.setLayout(null);
		
		
		/* ------------------------------------------------------
		 * |  [ COMPONENTS ]									 |
		 * ---------------------------------------------------- */
		
		/* 좌측 직원 정보 패널 */
		JPanel leftEmpFieldPanel = new JPanel();
		leftEmpFieldPanel.setBounds(128, 72, 448, 576);
		leftEmpFieldPanel.setBackground(Color.GRAY);

		
		JLabel empInfoLabel = new JLabel("직원 정보");
		empInfoLabel.setBounds(128, 72, 400, 72);
		empInfoLabel.setBackground(Color.WHITE);
		empInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		leftEmpFieldPanel.add(empInfoLabel);
		
		
		/* 우측 직원 정보 패널 */
		JPanel rightEmpListPanel = new JPanel();
		rightEmpListPanel.setBounds(704, 72, 448, 576);
		rightEmpListPanel.setBackground(Color.GRAY);
		
		/* 기타 컴포넌트 */
		JButton backToMainButton = new JButton("메인으로");
		backToMainButton.setBounds(1152, 0, 110, 60);
		backToMainButton.setFont(new Font("굴림", Font.PLAIN, 10));
		this.add(backToMainButton);
		
		this.add(leftEmpFieldPanel);
		this.add(rightEmpListPanel);
		
		
		/* ------------------------------------------------------
		 * |  [ EVENT PROCESSING ]								 |
		 * ---------------------------------------------------- */
		backToMainButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage mainPage = new MainPage(mf);
				changePanel(mainPage);
			}
		});
		
	}

	
	// 나중에 static 메소드로 한 곳에 정의해 두고 모든 페이지에서 가져다 쓰면 좋을 듯.
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.repaint();
	}

}
