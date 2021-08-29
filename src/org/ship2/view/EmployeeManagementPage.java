package org.ship2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.ship2.controller.EmployeeController;
import org.ship2.model.dto.EmployeeDTO;

/**
 * @packageName		: org.ship2.view
 * @fileName		: EmployeeManagementPage.java
 * @author			: UnSuk Song
 * @date			: 2021-08-25
 * @description		: 
 *
 */
public class EmployeeManagementPage extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2654488062615442613L;
	
	private EmployeeController employeeController = new EmployeeController();
	private MainFrame mf;
	
	private DefaultTableModel tableModel;
	private int tableIndex = 5000;
	private JTable empListTable;
	private JScrollPane tableScrollPane;
	
	private JTextField empNameTextField;
	private JComboBox<String> empJobComboBox;
	private JTextField empJobTextField;
	private JTextField empIdTextField;
	private JTextField empPwTextField;
	private JTextField empPhoneTextField;
	private final Color successColor = Color.GREEN;
	private final Color failedColor = Color.RED;
	private final Color normalColor = Color.BLACK;
	
	private String selectedEmpName = new String();
	private String selectedEmpId = new String();
	private String currentProcess = new String("");
	
	
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
		
		// DML 수행 결과 Pop-Up
		JPanel dmlResultPopUp = new JPanel();
		dmlResultPopUp.setVisible(false);
		dmlResultPopUp.setLayout(null);
		dmlResultPopUp.setBackground(Color.WHITE);
		dmlResultPopUp.setBounds(423, 189, 429, 249);
		this.add(dmlResultPopUp);
		
		JButton dmlResultPopUpConfirmButton = new JButton("확인");
		dmlResultPopUpConfirmButton.setFont(new Font("굴림", Font.PLAIN, 15));
		dmlResultPopUpConfirmButton.setBounds(187, 206, 109, 33);
		dmlResultPopUp.add(dmlResultPopUpConfirmButton);
		
		JTextPane dmlResultPopUpMessage = new JTextPane();
		dmlResultPopUpMessage.setFont(new Font("굴림", Font.PLAIN, 15));
		dmlResultPopUpMessage.setBounds(12, 64, 405, 93);
		dmlResultPopUp.add(dmlResultPopUpMessage);
		
		// DML 수행 전 조건 검사 Pop-Up
		JPanel conditionCheckPopUp = new JPanel();
		conditionCheckPopUp.setVisible(false);
		conditionCheckPopUp.setLayout(null);
		conditionCheckPopUp.setBackground(Color.WHITE);
		conditionCheckPopUp.setBounds(423, 189, 429, 249);
		this.add(conditionCheckPopUp);
		
		JButton conditionCheckPopUpConfirmButton = new JButton("확인");
		conditionCheckPopUpConfirmButton.setFont(new Font("굴림", Font.PLAIN, 15));
		conditionCheckPopUpConfirmButton.setBounds(187, 206, 109, 33);
		conditionCheckPopUp.add(conditionCheckPopUpConfirmButton);
		
		JTextPane conditionCheckPopUpMessage = new JTextPane();
		conditionCheckPopUpMessage.setFont(new Font("굴림", Font.PLAIN, 15));
		conditionCheckPopUpMessage.setBounds(12, 64, 405, 93);
		conditionCheckPopUp.add(conditionCheckPopUpMessage);
		
		// 직원 추가 확인 팝업
		JPanel registerConfirmPopUp = new JPanel();
		registerConfirmPopUp.setVisible(false);
		registerConfirmPopUp.setBorder(new LineBorder(normalColor, 2, true));
		registerConfirmPopUp.setLayout(null);
		registerConfirmPopUp.setBackground(Color.WHITE);
		registerConfirmPopUp.setBounds(423, 189, 429, 249);
		this.add(registerConfirmPopUp);
		
		JButton registerConfirmPopUpCancelButton = new JButton("취소");
		registerConfirmPopUpCancelButton.setFont(new Font("굴림", Font.PLAIN, 15));
		registerConfirmPopUpCancelButton.setBounds(308, 206, 109, 33);
		registerConfirmPopUp.add(registerConfirmPopUpCancelButton);
		
		JButton registerConfirmPopUpConfirmButton = new JButton("확인");
		registerConfirmPopUpConfirmButton.setFont(new Font("굴림", Font.PLAIN, 15));
		registerConfirmPopUpConfirmButton.setBounds(187, 206, 109, 33);
		registerConfirmPopUp.add(registerConfirmPopUpConfirmButton);
		
		JTextPane registerConfirmPopUpMessage = new JTextPane();
		registerConfirmPopUpMessage.setText(
				"입력하신 정보로 새로운 직원을 추가 하시겠습니까?");
		registerConfirmPopUpMessage.setFont(new Font("굴림", Font.PLAIN, 15));
		registerConfirmPopUpMessage.setBounds(12, 64, 405, 93);
		registerConfirmPopUp.add(registerConfirmPopUpMessage);
		
		// 직원 수정 확인 팝업
		JPanel modifyConfirmPopUp = new JPanel();
		modifyConfirmPopUp.setVisible(false);
		modifyConfirmPopUp.setBorder(new LineBorder(normalColor, 2, true));
		modifyConfirmPopUp.setLayout(null);
		modifyConfirmPopUp.setBackground(Color.WHITE);
		modifyConfirmPopUp.setBounds(423, 189, 429, 249);
		this.add(modifyConfirmPopUp);
		
		JButton modifyConfirmPopUpCancelButton = new JButton("취소");
		modifyConfirmPopUpCancelButton.setFont(new Font("굴림", Font.PLAIN, 15));
		modifyConfirmPopUpCancelButton.setBounds(308, 206, 109, 33);
		modifyConfirmPopUp.add(modifyConfirmPopUpCancelButton);
		
		JButton modifyConfirmPopUpConfirmButton = new JButton("확인");
		modifyConfirmPopUpConfirmButton.setFont(new Font("굴림", Font.PLAIN, 15));
		modifyConfirmPopUpConfirmButton.setBounds(187, 206, 109, 33);
		modifyConfirmPopUp.add(modifyConfirmPopUpConfirmButton);
		
		JTextPane modifyConfirmPopUpMessage = new JTextPane();
		modifyConfirmPopUpMessage.setText(
				"입력하신 정보로 \"" + getSelectedEmpName()
				+ "\"님의 정보를 수정 하시겠습니까?");
		modifyConfirmPopUpMessage.setFont(new Font("굴림", Font.PLAIN, 15));
		modifyConfirmPopUpMessage.setBounds(12, 64, 405, 93);
		modifyConfirmPopUp.add(modifyConfirmPopUpMessage);
		
		// 직원 삭제 확인 팝업
		JPanel deleteConfirmPopUp = new JPanel();
		deleteConfirmPopUp.setVisible(false);
		deleteConfirmPopUp.setBorder(new LineBorder(normalColor, 2, true));
		deleteConfirmPopUp.setLayout(null);
		deleteConfirmPopUp.setBackground(Color.WHITE);
		deleteConfirmPopUp.setBounds(423, 189, 429, 249);
		this.add(deleteConfirmPopUp);
		
		JButton deleteConfirmPopUpCancelButton = new JButton("취소");
		deleteConfirmPopUpCancelButton.setFont(new Font("굴림", Font.PLAIN, 15));
		deleteConfirmPopUpCancelButton.setBounds(308, 206, 109, 33);
		deleteConfirmPopUp.add(deleteConfirmPopUpCancelButton);
		
		JButton deleteConfirmPopUpConfirmButton = new JButton("확인");
		deleteConfirmPopUpConfirmButton.setFont(new Font("굴림", Font.PLAIN, 15));
		deleteConfirmPopUpConfirmButton.setBounds(187, 206, 109, 33);
		deleteConfirmPopUp.add(deleteConfirmPopUpConfirmButton);
		
		JTextPane deleteConfirmPopUpMessage = new JTextPane();
		deleteConfirmPopUpMessage.setText(
				"선택하신 \"" + getSelectedEmpName()
				+ "\" 님을 삭제 하시겠습니까?");
		deleteConfirmPopUpMessage.setFont(new Font("굴림", Font.PLAIN, 15));
		deleteConfirmPopUpMessage.setBounds(12, 64, 405, 93);
		deleteConfirmPopUp.add(deleteConfirmPopUpMessage);
		
		// 좌측 직원 세부 정보 패널
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
		
		// 직원 이름 입력 TextField
		empNameTextField = new JTextField();
		empNameTextField.setEditable(false);
		empNameTextField.setFont(new Font("굴림", Font.PLAIN, 15));
		empNameTextField.setBounds(144, 74, 220, 40);
		leftEmpInoPanel.add(empNameTextField);
		empNameTextField.setColumns(20);
		
		// 직원 직급 입력 ComboBox & TextField
		// ComboBox
		empJobComboBox = new JComboBox<String>(new String[] {"관리자", "직원", "수습"});
		empJobComboBox.setEditable(false);
		empJobComboBox.setVisible(false);
		empJobComboBox.setFont(new Font("굴림", Font.PLAIN, 15));
		empJobComboBox.setBounds(144, 124, 220, 40);
		leftEmpInoPanel.add(empJobComboBox);
		// TextField
		empJobTextField = new JTextField();
		empJobTextField.setEditable(false);
		empJobTextField.setVisible(true);
		empJobTextField.setFont(new Font("굴림", Font.PLAIN, 15));
		empJobTextField.setColumns(20);
		empJobTextField.setBounds(144, 124, 220, 40);
		leftEmpInoPanel.add(empJobTextField);
		
		// 직원 ID 입력 TextField
		empIdTextField = new JTextField();
		empIdTextField.setEditable(false);
		empIdTextField.setFont(new Font("굴림", Font.PLAIN, 15));
		empIdTextField.setColumns(20);
		empIdTextField.setBounds(144, 224, 220, 40);
		leftEmpInoPanel.add(empIdTextField);
		
		// 직원 PW 입력 TextField
		empPwTextField = new JTextField();
		empPwTextField.setEditable(false);
		empPwTextField.setFont(new Font("굴림", Font.PLAIN, 15));
		empPwTextField.setColumns(20);
		empPwTextField.setBounds(144, 274, 220, 40);
		leftEmpInoPanel.add(empPwTextField);
		
		// 직원 연락처 입력 TextField
		empPhoneTextField = new JTextField();
		empPhoneTextField.setEditable(false);
		empPhoneTextField.setFont(new Font("굴림", Font.PLAIN, 15));
		empPhoneTextField.setColumns(20);
		empPhoneTextField.setBounds(144, 174, 220, 40);
		leftEmpInoPanel.add(empPhoneTextField);
		
		JButton registerConfirmButton = new JButton("등록 확인");
		registerConfirmButton.setVisible(false);
		registerConfirmButton.setFont(new Font("굴림", Font.BOLD, 15));
		registerConfirmButton.setBackground(new Color(102, 205, 170));
		registerConfirmButton.setBounds(294, 401, 110, 69);
		leftEmpInoPanel.add(registerConfirmButton);
		
		JButton registerCancelButton = new JButton("등록 취소");
		registerCancelButton.setVisible(false);
		registerCancelButton.setFont(new Font("굴림", Font.BOLD, 15));
		registerCancelButton.setBackground(new Color(102, 205, 170));
		registerCancelButton.setBounds(416, 401, 110, 69);
		leftEmpInoPanel.add(registerCancelButton);
		
		JButton modifyConfirmButton = new JButton("수정 확인");
		modifyConfirmButton.setVisible(false);
		modifyConfirmButton.setFont(new Font("굴림", Font.BOLD, 15));
		modifyConfirmButton.setBackground(new Color(102, 205, 170));
		modifyConfirmButton.setBounds(294, 401, 110, 69);
		leftEmpInoPanel.add(modifyConfirmButton);
		
		JButton modifyCancelButton = new JButton("수정 취소");
		modifyCancelButton.setVisible(false);
		modifyCancelButton.setFont(new Font("굴림", Font.BOLD, 15));
		modifyCancelButton.setBackground(new Color(102, 205, 170));
		modifyCancelButton.setBounds(416, 401, 110, 69);
		leftEmpInoPanel.add(modifyCancelButton);
		
		// 우측 직원 목록 패널
		JPanel rightEmpListPanel = new JPanel();
		rightEmpListPanel.setForeground(Color.WHITE);
		rightEmpListPanel.setBackground(new Color(230, 230, 250));
		rightEmpListPanel.setBounds(669, 89, 538, 480);
		rightEmpListPanel.setLayout(null);
		this.add(rightEmpListPanel);
		
		JLabel empListPanel = new JLabel("직원 목록");
		empListPanel.setOpaque(true);
		empListPanel.setHorizontalAlignment(SwingConstants.CENTER);
		empListPanel.setFont(new Font("굴림", Font.BOLD, 20));
		empListPanel.setBackground(Color.WHITE);
		empListPanel.setBounds(12, 10, 514, 54);
		rightEmpListPanel.add(empListPanel);
		
		// 직원 목록 테이블
		employeeController.selectAllEmployees();
		
		tableModel = employeeController.getTableModel();
		empListTable = new JTable(tableModel);
		empListTable.setFont(new Font("굴림", Font.PLAIN, 15));
		empListTable.setCellSelectionEnabled(false);
		empListTable.setRowSelectionAllowed(true);
		tableScrollPane = new JScrollPane(empListTable);
		tableScrollPane.setBounds(12, 74, 514, 396);
		rightEmpListPanel.add(tableScrollPane);

		JButton backToMainPageButton = new JButton("메인으로");
		backToMainPageButton.setBackground(new Color(102, 205, 170));
		backToMainPageButton.setFont(new Font("굴림", Font.BOLD, 15));
		backToMainPageButton.setBounds(1097, 10, 110, 69);
		this.add(backToMainPageButton);
		
		JButton registerButton = new JButton("직원 등록");
		registerButton.setFont(new Font("굴림", Font.BOLD, 15));
		registerButton.setBackground(new Color(102, 205, 170));
		registerButton.setBounds(51, 579, 110, 69);
		this.add(registerButton);
		
		JButton modifyButton = new JButton("직원 수정");
		modifyButton.setFont(new Font("굴림", Font.BOLD, 15));
		modifyButton.setBackground(new Color(102, 205, 170));
		modifyButton.setBounds(173, 579, 110, 69);
		this.add(modifyButton);
		
		JButton deleteButton = new JButton("직원 삭제");
		deleteButton.setFont(new Font("굴림", Font.BOLD, 15));
		deleteButton.setBackground(new Color(102, 205, 170));
		deleteButton.setBounds(295, 579, 110, 69);
		this.add(deleteButton);
		
		
		/* ------------------------------------------------------
		 * |  [ EVENT PROCESSING ]								 |
		 * ---------------------------------------------------- */
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurrentProcess("INSERT");
				
				eraseAllEmployeeInfo();
				makeJobComboBoxVisible(true);
				setEditableToAllEmployeeInfo(true);
				
				registerConfirmButton.setVisible(true);
				registerCancelButton.setVisible(true);
				modifyButton.setEnabled(false);
				deleteButton.setEnabled(false);
			}
		});
		
		registerConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!isEmployeeInfoNull()) {
					registerConfirmPopUp.setVisible(true);
				} else {
					setEditableToAllEmployeeInfo(false);
					conditionCheckPopUpMessage.setText("직원 추가 작업을 진행할 수 없습니다! "
							+ "입력하신 직원의 정보 중 비어있는 항목이 있는 것 같습니다. "
							+ "입력하신 정보를 다시 확인해 주시고, "
							+ "올바른 직원 정보를 입력해 주세요.");
					conditionCheckPopUp.setBorder(new LineBorder(failedColor, 2, true));
					conditionCheckPopUp.setVisible(true);
				}
			}
		});
		
		registerCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				eraseAllEmployeeInfo();
				makeJobComboBoxVisible(false);
				setEditableToAllEmployeeInfo(false);
				
				registerConfirmButton.setVisible(false);
				registerCancelButton.setVisible(false);
				modifyButton.setEnabled(true);
				deleteButton.setEnabled(true);
			}
		});
		
		registerConfirmPopUpConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeJobComboBoxVisible(false);
				setEditableToAllEmployeeInfo(false);
				employeeController.registerNewEmployee(getEmployeeeInfoFromUser());
				registerConfirmPopUp.setVisible(false);
				
				dmlResultPopUp.setVisible(true);
				if (employeeController.isInsertSuccess()) {
					eraseAllEmployeeInfo();
					refreshEmpListTable();
					
					dmlResultPopUpMessage.setText("직원 추가 작업이 성공적으로 수행되었습니다! "
							+ "직원의 정보가 올바르게 추가 되었는지 직원 목록을 확인해 주세요.");
					dmlResultPopUp.setBorder(new LineBorder(successColor, 2, true));
					
					registerConfirmButton.setVisible(false);
					registerCancelButton.setVisible(false);
				} else {
					dmlResultPopUpMessage.setText("직원 추가 작업을 실패했습니다!");
					dmlResultPopUp.setBorder(new LineBorder(failedColor, 2, true));
				}
			}
		});
		
		registerConfirmPopUpCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				registerConfirmPopUp.setVisible(false);
			}
		});
		
		modifyButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurrentProcess("UPDATE");
				
				empListTable.setRowSelectionAllowed(false);
				setEditableToAllEmployeeInfo(true);
				makeJobComboBoxVisible(true);
				
				modifyConfirmButton.setVisible(true);
				modifyCancelButton.setVisible(true);
				registerButton.setEnabled(false);
				deleteButton.setEnabled(false);
			}
		});
		
		modifyConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setEditableToAllEmployeeInfo(false);
				if (!isEmployeeInfoNull()) {
					modifyConfirmPopUpMessage.setText(
							"입력하신 정보로 \"" + getSelectedEmpName()
							+ "\"님의 정보를 수정 하시겠습니까?");
					modifyConfirmPopUp.setVisible(true);
				} else {
					conditionCheckPopUpMessage.setText("직원 수정 작업을 진행할 수 없습니다! "
							+ "수정하신 직원의 정보 중 비어있는 항목이 있는 것 같습니다. "
							+ "수정하신 정보를 다시 확인해 주시고, "
							+ "올바른 직원 정보를 입력해 주세요.");
					conditionCheckPopUp.setBorder(new LineBorder(failedColor, 2, true));
					conditionCheckPopUp.setVisible(true);
				}
			}
		});
		
		conditionCheckPopUpConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO ConditionCheckPopUp
				conditionCheckPopUp.setVisible(false);
				makeJobComboBoxVisible(true);
				setEditableToAllEmployeeInfo(true);
				
				if (getCurrentProcess().equals("ERROR")) {
					MainPage mainPage = new MainPage(mf);
					changePanel(mainPage);
				}
				
//				switch (getCurrentProcess()) {
//				case "INSERT":
//					makeJobComboBoxVisible(true);
//					setEditableToAllEmployeeInfo(true);
//					break;
//				case "UPDATE":
//					setEditableToAllEmployeeInfo(true);
//					break;
//				case "DELETE": break;
//				case "ERROR":
//					MainPage mainPage = new MainPage(mf);
//					changePanel(mainPage);
//					break;
//				}
			}
		});
		
		modifyCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurrentProcess("MODIFY");
				
				eraseAllEmployeeInfo();
				makeJobComboBoxVisible(false);
				setEditableToAllEmployeeInfo(false);
				empListTable.setRowSelectionAllowed(true);
				
				modifyConfirmButton.setVisible(false);
				modifyCancelButton.setVisible(false);
				registerButton.setEnabled(true);
				deleteButton.setEnabled(true);
			}
		});
		
		modifyConfirmPopUpConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				makeJobComboBoxVisible(false);
				setEditableToAllEmployeeInfo(false);
				employeeController.modifyEmployee(
						getSelectedEmpId(), getEmployeeeInfoFromUser());
				modifyConfirmPopUp.setVisible(false);
				
				dmlResultPopUp.setVisible(true);
				if (employeeController.isUpdateSuccess()) {
					refreshEmpListTable();
					
					dmlResultPopUpMessage.setText("직원 수정 작업이 성공적으로 수행되었습니다! "
							+ "직원의 정보가 올바르게 수정 되었는지 직원 목록을 확인해 주세요.");
					dmlResultPopUp.setBorder(new LineBorder(successColor, 2, true));
					
					modifyConfirmButton.setVisible(false);
					modifyCancelButton.setVisible(false);
				} else {
					dmlResultPopUpMessage.setText("직원 수정 작업을 실패했습니다!");
					dmlResultPopUp.setBorder(new LineBorder(failedColor, 2, true));
				}
			}
		});
		
		modifyConfirmPopUpCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modifyConfirmPopUp.setVisible(false);
			}
		});
		
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setCurrentProcess("DELETE");
				
				registerButton.setEnabled(false);
				modifyButton.setEnabled(false);
				deleteConfirmPopUp.setVisible(true);
			}
		});
		
		deleteConfirmPopUpConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				employeeController.deleteEmployee(selectedEmpId);
				deleteConfirmPopUp.setVisible(false);
				
				dmlResultPopUp.setVisible(true);
				if (employeeController.isDeleteSuccess()) {
					eraseAllEmployeeInfo();
					refreshEmpListTable();
					
					dmlResultPopUpMessage.setText("직원 삭제 작업이 성공적으로 수행되었습니다! "
							+ "직원이 올바르게 삭제 되었는지 직원 목록을 확인해 주세요.");
					dmlResultPopUp.setBorder(new LineBorder(successColor, 2, true));
				} else {
					dmlResultPopUpMessage.setText("직원 삭제 작업을 실패했습니다!");
					dmlResultPopUp.setBorder(new LineBorder(failedColor, 2, true));
				}
			}
		});
		
		deleteConfirmPopUpCancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteConfirmPopUp.setVisible(false);
			}
		});
		
		dmlResultPopUpConfirmButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dmlResultPopUp.setVisible(false);
				
				/* determine which process is ongoing */
				switch (getCurrentProcess()) {
				case "INSERT": // when ongoing process is INSERT process.
					/* determine whether INSERT process is over,
					 * then make other UPDATE, DELETE button enable/disable.
					 */
					if (employeeController.isInsertSuccess()) {
						modifyButton.setEnabled(true);
						deleteButton.setEnabled(true);
						eraseAllEmployeeInfo();
					}
					employeeController.setInsertSuccess(false);
					break;
					
				case "UPDATE": // when ongoing process is UPDATE process.
					/* determine whether UPDATE process is over,
					 * then make other INSERT, DELETE button enable/disable.
					 */
					if (employeeController.isUpdateSuccess()) {
						// TODO add tasks when ongoing process is UPDATE and result is success. 
						registerButton.setEnabled(true);
						modifyButton.setEnabled(true);
					}
					employeeController.setUpdateSuccess(false);
					break;
				case "DELETE": // when ongoing process is DELETE process.
					/* Unlike other processes above, DELETE process does not
					 * require the result of DML. Therefore it doesn't need to
					 * determine whether DELETE process has succeeded or failed.
					 */
					registerButton.setEnabled(true);
					modifyButton.setEnabled(true);
					employeeController.setDeleteSuccess(false);
					break;
					
				default:
					setCurrentProcess("ERROR");
					conditionCheckPopUpMessage.setText("[Error]: 현재 페이지에 "
							+ "알 수 없는 에러가 발생했습니다. 아래의 확인 "
							+ "버튼을 누르거나 우측 상단의 메인 메뉴로 돌아가기 "
							+ "버튼을 눌러 메인 메뉴로 돌아간 후 직원 관리 "
							+ "화면을 다시 선택해 주시기 바랍니다.");
					conditionCheckPopUp.setBorder(new LineBorder(failedColor, 2, true));
					conditionCheckPopUp.setVisible(true);
				}
				
				/* determine whether INSERT process is over,
				 * then make other UPDATE, DELETE button enable/disable.
				 */
//				if (registerButton.isEnabled()
//						&& (!modifyButton.isEnabled() || !deleteButton.isEnabled())
//						) {
//					// when INSERT process is NOT over
//					modifyButton.setEnabled(false);
//					deleteButton.setEnabled(false);
//				} else if (!registerConfirmButton.isEnabled()
//						&& (modifyButton.isEnabled() || deleteButton.isEnabled())
//						){
//					// when INSERT process is over
//					modifyButton.setEnabled(true);
//					deleteButton.setEnabled(true);
//					eraseAllEmployeeInfo();
//				}
				
				/* determine whether UPDATE process is over,
				 * then make other INSERT, DELETE button enable/disable.
				 */
//				if (modifyConfirmButton.isEnabled()
//						&& !registerConfirmButton.isEnabled()) {
//					// when UPDATE process is NOT over
//					registerButton.setEnabled(false);
//					deleteButton.setEnabled(false);
//				} else if (!modifyConfirmButton.isEnabled()
//						&& !registerConfirmButton.isEnabled()){
//					// when UPDATE process is over
//					registerButton.setEnabled(true);
//					deleteButton.setEnabled(true);
//					eraseAllEmployeeInfo();
//				}
				
				/* Unlike other processes above, DELETE process do not require
				 * detailed processing. Therefore it only need to determine
				 * whether current ongoing process is DELETE process, or not.
				 */
//				if (!registerButton.isEnabled()
//						&& !modifyButton.isEnabled()) {
//					// when current ongoing process is DELETE process.
//					registerButton.setEnabled(true);
//					modifyButton.setEnabled(true);
//				}
			}
		});
		
		backToMainPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage mainPage = new MainPage(mf);
				if (true) {
					mainPage.isManager = true;
					changePanel(mainPage);
				}
			}
		});
		
		empListTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				tableIndex = empListTable.getSelectedRow();
				
				EmployeeDTO emp = employeeController.getEmpList().get(tableIndex);
				
				String jobName = "";
				switch (emp.getJobCode()) {
				case 1:
					empJobComboBox.setSelectedIndex(0);
					jobName = "관리자";
					break;
				case 2:
					empJobComboBox.setSelectedIndex(1);
					jobName = "직원";
					break;
				case 3:
					empJobComboBox.setSelectedIndex(2);
					jobName = "수습";
					break;
				}
				
				empNameTextField.setText(emp.getEmpName());
				empJobTextField.setText(jobName);
				empIdTextField.setText(emp.getEmpId());
				empPwTextField.setText(emp.getEmpPw());
				empPhoneTextField.setText(emp.getEmpPhone());
				
				setSelectedEmpName(emp.getEmpName());
				setSelectedEmpId(emp.getEmpId());
			}
		});
		
		
		/* ------------------------------------------------------
		 * |  [ OTHER PROCESS ]									 |
		 * ---------------------------------------------------- */
				
	} /* GUI Panel Ends here */
	
	/**
	 * @methodName : makeJobComboBoxVisible
	 * @author : UnSuk Song
	 * @date : 2021.08.28
	 * @brief : Make JobTextField invisible and JobComboBox visible.
	 * 
	 * @param b set true if want to bring JobComboBox at the top of panel
	 *          and make visible.
	 */
	public void makeJobComboBoxVisible(boolean b) {
		empJobTextField.setVisible(!b);
		empJobComboBox.setVisible(b);
	}

	/**
	 * @methodName : getEmployeeeInfoFromUser
	 * @author : UnSuk Song
	 * @date : 2021.08.28
	 * @brief : Generate HashMap with information of employee from JTextField
	 *          and JComboBox.
	 * 
	 * @return empInfoMap HashMap of information of employee to register.
	 */
	public Map<String, String> getEmployeeeInfoFromUser() {
		Map<String, String> empInfoMap = new HashMap<>();
		
		empInfoMap.put("name", empNameTextField.getText());
		empInfoMap.put("phone", empPhoneTextField.getText());
		empInfoMap.put("jobCode", String.valueOf(empJobComboBox.getSelectedIndex() + 1));
		empInfoMap.put("id", empIdTextField.getText());
		empInfoMap.put("pw", empPwTextField.getText());

		return empInfoMap;
	}

	/**
	 * @methodName : isEmployeeInfoNull
	 * @author : UnSuk Song
	 * @date : 2021.08.28
	 * @brief : Determine whether any JTextField, filled with information of
	 *          employee to register, is empty.
	 *          Note that JobComboBox is excluded at these conditions.
	 * 
	 * @return true if any TextField is empty, false otherwise.
	 */
	public boolean isEmployeeInfoNull() {
		if (empNameTextField.getText().equals("")
				|| empIdTextField.getText().equals("")
				|| empPwTextField.getText().equals("")
				|| empPhoneTextField.getText().equals("")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @methodName : setEditableToAllEmployeeInfo
	 * @author : UnSuk Song
	 * @date : 2021.08.28
	 * @brief : Set property of components added on left side panel, representing
	 *          information of selected employee, into editable or not editable.
	 * 
	 * @param isEditable set true if want to change all input components editable.
	 */
	public void setEditableToAllEmployeeInfo(boolean isEditable) {
		empNameTextField.setEditable(isEditable);
		empJobTextField.setEditable(isEditable);
		empJobComboBox.setEditable(isEditable);
		empIdTextField.setEditable(isEditable);
		empPwTextField.setEditable(isEditable);
		empPhoneTextField.setEditable(isEditable);
	}
	
	/**
	 * @methodName : eraseAllEmployeeInfo
	 * @author : UnSuk Song
	 * @date : 2021.08.28
	 * @brief : Erase all the information of components added on left side panel.
	 */
	public void eraseAllEmployeeInfo() {
		empNameTextField.setText("");
		empJobTextField.setText("");
		empJobComboBox.setSelectedIndex(2);
		empIdTextField.setText("");
		empPwTextField.setText("");
		empPhoneTextField.setText("");
	}
	
	/**
	 * @methodName : refreshTableModel
	 * @author : UnSuk Song
	 * @date : 2021.08.29
	 * @brief : Clear JTable's table model and refresh JTable.
	 * 
	 */
	public void refreshEmpListTable() {
		tableModel.setRowCount(0);
		employeeController.selectAllEmployees();
		tableModel.fireTableDataChanged();
	}

	// 나중에 static 메소드로 한 곳에 정의해 두고 모든 페이지에서 가져다 쓰면 좋을 듯.
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.repaint();
	}

	public String getSelectedEmpName() {
		return selectedEmpName;
	}


	public void setSelectedEmpName(String selectedEmpName) {
		this.selectedEmpName = selectedEmpName;
	}


	public String getSelectedEmpId() {
		return selectedEmpId;
	}


	public void setSelectedEmpId(String selectedEmpId) {
		this.selectedEmpId = selectedEmpId;
	}


	public String getCurrentProcess() {
		return currentProcess;
	}


	public void setCurrentProcess(String currentProcess) {
		this.currentProcess = currentProcess;
	}
	
}
