package org.ship2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

	private JPanel warningPopUp;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel IdLabel;
	private JLabel PwLabel;
	private JLabel warningLabel;
	private JButton okButton;
	private List<EmployeeDTO> employeeList;


	/* 로그인 페이지 패널 */
	public LogInPage(MainFrame mainFrame) {
		this.mf = mainFrame;

		this.setSize(1280, 720);
		this.setBackground(Color.gray);
		this.setLayout(null);

		/* 경고 팝업 패널 */
		warningPopUp = new JPanel();
		warningPopUp.setBackground(Color.LIGHT_GRAY);
		warningPopUp.setBounds(350, 182, 500, 400);
		warningPopUp.setVisible(false);
		warningPopUp.setLayout(null);
		this.add(warningPopUp);
		
		/* 확인 버튼 */
		okButton = new JButton();
		okButton.setText("확인");
		okButton.setFont(new Font("굴림", Font.BOLD, 30));
		okButton.setBounds(80, 260, 311, 57);
		warningPopUp.add(okButton);
		
		/* 경고 메세지 */
		warningLabel = new JLabel();
		warningLabel.setText("잘못된 입력입니다.");
		warningLabel.setFont(new Font("굴림", Font.BOLD, 30));
		warningLabel.setBounds(80, 43, 311, 178);
		warningPopUp.add(warningLabel);
		
		/* 확인 버튼 눌렀을 시 액션 */
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				warningPopUp.setVisible(false);
			}
		});

		/* 아이디 입력필드 */
		JTextField textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(220, 180, 400, 170);
		textField.setFont(new Font("굴림", Font.BOLD, 30));
		add(textField);
		textField.setColumns(10);

		/* 비밀번호 입력필드 */
		JPasswordField passwordField = new JPasswordField();
		passwordField.setColumns(10);
		passwordField.setBounds(220, 380, 400, 170);
		passwordField.setFont(new Font("굴림", Font.BOLD, 30));
		add(passwordField);

		/* 로그인 버튼 */
		JButton logInButton = new JButton("로그인");
		logInButton.setBounds(800, 177, 400, 381);
		logInButton.setFont(new Font("굴림", Font.BOLD, 30));
		add(logInButton);

		IdLabel = new JLabel("ID");
		IdLabel.setBounds(22, 226, 152, 71);
		IdLabel.setFont(new Font("굴림", Font.BOLD, 20));
		add(IdLabel);

		PwLabel = new JLabel("PASSWORD");
		PwLabel.setBounds(22, 432, 152, 71);
		PwLabel.setFont(new Font("굴림", Font.BOLD, 20));
		add(PwLabel);

		mf.getContentPane().add(this);

		/* 로그인 버튼 액션 리스너 */
		/* idtext와 employee의 getEmpId 비교 후 pwtext와 employee의 getEmpPw비교, 확인되면 로그인 성공 후 메인화면 이동 */
		logInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				employeeList = selectEmployee();

				String idtext = textField.getText();
				String pwtext = passwordField.getText();
				Boolean flag = true;
				
				for (int i = 0; i < employeeList.size(); i++) {
					EmployeeDTO employee = employeeList.get(i);
					if (idtext.equals(employee.getEmpId())) {
						if (pwtext.equals(employee.getEmpPw())) {
							System.out.println("로그인 성공!");
							MainPage mainpage = new MainPage(mf);
							flag = false;
						 if (employee.getJobCode() == 1) {
							 mainpage.isManager = true;
						 }
						 changePanel(mainpage);
						}
					}
				}
				if(flag) {
					warningPopUp.setVisible(true);
				}
			}
		});
	}


	/* 패널 변경 메소드(메인메소드로) */
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
