package org.ship2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import org.ship2.controller.EmployeeController;
import org.ship2.model.dto.EmployeeDTO;
import org.ship2.model.dto.MenuCategoriSizeDTO;

/**
 * @author Unsuk Song
 *
 */
public class EmployeeManagementPage extends JPanel {
	private MainFrame mf;
	private int tableIndex = 5000;
	private EmployeeController employeeController = new EmployeeController();
	private JScrollPane tableScrollPane;
	private JTextField empNameTextField;
	private JTextField empJobTextField;
	private JTextField empIdTextField;
	private JTextField empPwTextField;
	private JTextField empPhoneTextField;
	
	
	
	/**
	 * Default Ctor
	 * @Note 기본 생성자 사용 금지!!
	 *       아래의 MainFrame 객체를 매개변수로 하는 생성자를 사용할 것.
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
		/* 
		 * 프레임 인스턴스인 mf는 해당 생성자가 호출될 때 인자로 전달된
		 *  MainFrame으로부터 모든 속성을 물려받았다.
		 */
		this.mf = mainFrame;
		this.setSize(1280, 720);
		this.setLayout(null);
		
		
		
		/* ------------------------------------------------------
		 * |  [ COMPONENTS ]									 |
		 * ---------------------------------------------------- */
		JPanel leftEmpInoPanel = new JPanel();
		leftEmpInoPanel.setBackground(new Color(230, 230, 250));
		leftEmpInoPanel.setForeground(Color.WHITE);
		leftEmpInoPanel.setBounds(51, 89, 538, 480);
		this.add(leftEmpInoPanel);
		leftEmpInoPanel.setLayout(null);
		
		JLabel empInfoLabel = new JLabel("직원 정보");
		empInfoLabel.setOpaque(true);
		empInfoLabel.setFont(new Font("굴림", Font.BOLD, 20));
		empInfoLabel.setBackground(new Color(255, 255, 255));
		empInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empInfoLabel.setBounds(12, 10, 514, 54);
		leftEmpInoPanel.add(empInfoLabel);
		
		JLabel empNameLabel = new JLabel("이름");
		empNameLabel.setOpaque(true);
		empNameLabel.setBackground(new Color(255, 255, 255));
		empNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empNameLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		empNameLabel.setBounds(12, 74, 120, 40);
		leftEmpInoPanel.add(empNameLabel);
		
		JLabel empJobLabel = new JLabel("직급");
		empJobLabel.setOpaque(true);
		empJobLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empJobLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		empJobLabel.setBackground(Color.WHITE);
		empJobLabel.setBounds(12, 124, 120, 40);
		leftEmpInoPanel.add(empJobLabel);
		
		JLabel empIdLabel = new JLabel("아이디(ID)");
		empIdLabel.setOpaque(true);
		empIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empIdLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		empIdLabel.setBackground(Color.WHITE);
		empIdLabel.setBounds(12, 224, 120, 40);
		leftEmpInoPanel.add(empIdLabel);
		
		JLabel empPwLabel = new JLabel("비밀번호(PW)");
		empPwLabel.setOpaque(true);
		empPwLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empPwLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		empPwLabel.setBackground(Color.WHITE);
		empPwLabel.setBounds(12, 274, 120, 40);
		leftEmpInoPanel.add(empPwLabel);
		
		JLabel empPhoneLabel = new JLabel("연락처");
		empPhoneLabel.setOpaque(true);
		empPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		empPhoneLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		empPhoneLabel.setBackground(Color.WHITE);
		empPhoneLabel.setBounds(12, 174, 120, 40);
		leftEmpInoPanel.add(empPhoneLabel);
		
		empNameTextField = new JTextField();
		empNameTextField.setEditable(false);
		empNameTextField.setFont(new Font("굴림", Font.PLAIN, 15));
		empNameTextField.setBounds(144, 74, 250, 40);
		leftEmpInoPanel.add(empNameTextField);
		empNameTextField.setColumns(20);
		
		empJobTextField = new JTextField();
		empJobTextField.setEditable(false);
		empJobTextField.setFont(new Font("굴림", Font.PLAIN, 15));
		empJobTextField.setColumns(20);
		empJobTextField.setBounds(144, 124, 250, 40);
		leftEmpInoPanel.add(empJobTextField);
		
		empIdTextField = new JTextField();
		empIdTextField.setEditable(false);
		empIdTextField.setFont(new Font("굴림", Font.PLAIN, 15));
		empIdTextField.setColumns(20);
		empIdTextField.setBounds(144, 224, 250, 40);
		leftEmpInoPanel.add(empIdTextField);
		
		empPwTextField = new JTextField();
		empPwTextField.setEditable(false);
		empPwTextField.setFont(new Font("굴림", Font.PLAIN, 15));
		empPwTextField.setColumns(20);
		empPwTextField.setBounds(144, 274, 250, 40);
		leftEmpInoPanel.add(empPwTextField);
		
		empPhoneTextField = new JTextField();
		empPhoneTextField.setEditable(false);
		empPhoneTextField.setFont(new Font("굴림", Font.PLAIN, 15));
		empPhoneTextField.setColumns(20);
		empPhoneTextField.setBounds(144, 174, 250, 40);
		leftEmpInoPanel.add(empPhoneTextField);
		
		JButton deleteConfirmButton = new JButton("삭제 확인");
		deleteConfirmButton.setFont(new Font("굴림", Font.BOLD, 15));
		deleteConfirmButton.setBackground(new Color(102, 205, 170));
		deleteConfirmButton.setBounds(244, 401, 110, 69);
		leftEmpInoPanel.add(deleteConfirmButton);
		
		JButton modifyConfirmButton = new JButton("수정 확인");
		modifyConfirmButton.setFont(new Font("굴림", Font.BOLD, 15));
		modifyConfirmButton.setBackground(new Color(102, 205, 170));
		modifyConfirmButton.setBounds(122, 401, 110, 69);
		leftEmpInoPanel.add(modifyConfirmButton);
		
		JButton registConfirmButton = new JButton("등록 확인");
		registConfirmButton.setFont(new Font("굴림", Font.BOLD, 15));
		registConfirmButton.setBackground(new Color(102, 205, 170));
		registConfirmButton.setBounds(0, 401, 110, 69);
		leftEmpInoPanel.add(registConfirmButton);
		
		JPanel rightEmpListPanel = new JPanel();
		rightEmpListPanel.setForeground(Color.WHITE);
		rightEmpListPanel.setBackground(new Color(230, 230, 250));
		rightEmpListPanel.setBounds(669, 89, 538, 480);
		this.add(rightEmpListPanel);
		rightEmpListPanel.setLayout(null);
		
		JLabel empListPanel = new JLabel("직원 목록");
		empListPanel.setOpaque(true);
		empListPanel.setHorizontalAlignment(SwingConstants.CENTER);
		empListPanel.setFont(new Font("굴림", Font.BOLD, 20));
		empListPanel.setBackground(Color.WHITE);
		empListPanel.setBounds(12, 10, 514, 54);
		rightEmpListPanel.add(empListPanel);
		
		employeeController.selectAllEmployees();
		JTable empListTable = new JTable(employeeController.getTableModel());
		empListTable.setFont(new Font("굴림", Font.PLAIN, 15));
		tableScrollPane = new JScrollPane(empListTable);
		tableScrollPane.setBounds(12, 74, 514, 396);
		rightEmpListPanel.add(tableScrollPane);

		JButton backToMainPageButton = new JButton("메인으로");
		backToMainPageButton.setBackground(new Color(102, 205, 170));
		backToMainPageButton.setFont(new Font("굴림", Font.BOLD, 15));
		backToMainPageButton.setBounds(1097, 10, 110, 69);
		this.add(backToMainPageButton);
		
		JButton resgistButton = new JButton("등록");
		resgistButton.setFont(new Font("굴림", Font.BOLD, 15));
		resgistButton.setBackground(new Color(102, 205, 170));
		resgistButton.setBounds(51, 579, 110, 69);
		this.add(resgistButton);
		
		JButton modifyButton = new JButton("수정");
		modifyButton.setFont(new Font("굴림", Font.BOLD, 15));
		modifyButton.setBackground(new Color(102, 205, 170));
		modifyButton.setBounds(173, 579, 110, 69);
		this.add(modifyButton);
		
		JButton deleteButton = new JButton("삭제");
		deleteButton.setFont(new Font("굴림", Font.BOLD, 15));
		deleteButton.setBackground(new Color(102, 205, 170));
		deleteButton.setBounds(295, 579, 110, 69);
		this.add(deleteButton);
		
		
		/* ------------------------------------------------------
		 * |  [ EVENT PROCESSING ]								 |
		 * ---------------------------------------------------- */
		backToMainPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage mainPage = new MainPage(mf);
				changePanel(mainPage);
			}
		});
		
		empListTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tableIndex = empListTable.getSelectedRow();
				
				EmployeeDTO emp = employeeController.getEmpList().get(tableIndex);
				
				String jobName = new String();
				switch (emp.getJobCode()) {
				case 1:
					jobName = "관리자";
				case 2:
					jobName = "직원";
				case 3:
					jobName = "수습";
				}
				
				empNameTextField.setText(emp.getEmpName());
				empJobTextField.setText(jobName);
				empIdTextField.setText(emp.getEmpId());
				empPwTextField.setText(emp.getEmpPw());
				empPhoneTextField.setText(emp.getEmpPhone());
				
			}
		});
		
		
		/* ------------------------------------------------------
		 * |  [ OTHER PROCESS ]									 |
		 * ---------------------------------------------------- */
				
	}
	
	// 나중에 static 메소드로 한 곳에 정의해 두고 모든 페이지에서 가져다 쓰면 좋을 듯.
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.repaint();
	}

}
