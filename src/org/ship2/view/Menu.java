package org.ship2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import javax.management.modelmbean.ModelMBean;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import org.ship2.controller.MenuController;
import org.ship2.model.dto.MenuCategoriSizeDTO;
import org.ship2.model.dto.MenuDTO;

public class Menu extends JPanel {
	
	private Scanner sc = new Scanner(System.in);
	
	private MainFrame mf;	// 프레임 객체 생성
	private String colNames[] = {"메뉴명", "가격", "카테고리"};	// 테이블 컬럼 배열 생성
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);	// 테이블 모델 객체 생성(타이틀 포함)
	private JScrollPane scrollpane = new JScrollPane();		// 테이블 에 붙일 스크롤팬 생성
	private Object recode[] = new Object[3];	// 메뉴 리스트 담을 배열 생성
	private MenuDTO menu = new MenuDTO();	// 메뉴DTO 객체 생성
	private MenuController menuController = new MenuController();	// 메뉴 컨트롤러 객체 생성
	private JPanel successResult;	// 팝업 패널 생성
	private JPanel successResult2;
	private JPanel deleteCheck;
	private List<MenuCategoriSizeDTO> menulist;
	private int index = 5000;
	private JPanel sameNameError;
	
	public Menu() {}
	
	public Menu(MainFrame mainFrame) {
		/* 메뉴 패널 생성 */
		this.mf = mainFrame;
		this.setSize(1280, 720);
		this.setLayout(null);
		
		/* 컴포넌트 생성 */
		successResult = new JPanel();
		successResult.setBackground(Color.LIGHT_GRAY);
		successResult.setBounds(377, 182, 361, 212);
		successResult.setVisible(false);
		this.add(successResult);
		
		successResult2 = new JPanel();
		successResult2.setBackground(Color.LIGHT_GRAY);
		successResult2.setBounds(377, 182, 361, 212);
		successResult2.setVisible(false);
		this.add(successResult2);
		
		sameNameError = new JPanel();
		sameNameError.setBackground(Color.LIGHT_GRAY);
		sameNameError.setBounds(377, 182, 361, 212);
		sameNameError.setVisible(false);
		this.add(sameNameError);
		
		JTextField sameNameErrorTF = new JTextField();
		sameNameErrorTF.setFont(new Font("굴림", Font.PLAIN, 16));
		sameNameErrorTF.setText("이미 있는 메뉴입니다.");
		sameNameErrorTF.setBounds(95, 41, 175, 44);
		sameNameError.add(sameNameErrorTF);
		sameNameErrorTF.setColumns(15);
		
		deleteCheck = new JPanel();
		deleteCheck.setBackground(Color.LIGHT_GRAY);
		deleteCheck.setBounds(377, 182, 361, 212);
		deleteCheck.setVisible(false);
		this.add(deleteCheck);
		
		JButton insertBtn = new JButton("추가");
		insertBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		insertBtn.setBounds(250, 400, 97, 86);
		insertBtn.setVisible(false);
		this.add(insertBtn);
		
		JButton insertBtn1 = new JButton("추가");
		insertBtn1.setFont(new Font("굴림", Font.PLAIN, 20));
		insertBtn1.setBounds(60, 545, 97, 86);
		this.add(insertBtn1);
		
		JLabel lblNewLabel = new JLabel("메뉴정보");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 40));
		lblNewLabel.setBounds(60, 50, 472, 63);
		this.add(lblNewLabel);
		
		JButton updateBtn = new JButton("수정");
		updateBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		updateBtn.setBounds(250, 400, 97, 86);
		updateBtn.setVisible(false);
		this.add(updateBtn);
		
		JButton updateBtn1 = new JButton("수정");
		updateBtn1.setFont(new Font("굴림", Font.PLAIN, 20));
		updateBtn1.setBounds(250, 545, 97, 86);
		this.add(updateBtn1);
		
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		deleteBtn.setBounds(440, 545, 97, 86);
		this.add(deleteBtn);
		
		JLabel lblNewLabel_1 = new JLabel("메뉴명");
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(79, 130, 127, 39);
		this.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("가격");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(79, 210, 127, 39);
		this.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("카테고리");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel_1_1_1.setBounds(79, 290, 127, 39);
		this.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("커피, 음료, 빵");
		lblNewLabel_1_1_1_2.setToolTipText("");
		lblNewLabel_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_2.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_1_1_1_2.setBounds(79, 310, 127, 39);
		this.add(lblNewLabel_1_1_1_2);
		
		JTextField menuTF = new JTextField();
		menuTF.setBounds(218, 130, 254, 42);
		this.add(menuTF);
		menuTF.setEditable(false);
		menuTF.setColumns(10);
		
		JTextField priceTF = new JTextField();
		priceTF.setColumns(10);
		priceTF.setEditable(false);
		priceTF.setBounds(218, 210, 254, 42);
		this.add(priceTF);
		
		JTextField categoryTF = new JTextField();
		categoryTF.setColumns(10);
		categoryTF.setEditable(false);
		categoryTF.setBounds(218, 290, 254, 42);
		this.add(categoryTF);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(60, 40, 472, 468);
		this.add(panel);
		
		JButton mainBtn = new JButton("메인으로");
		mainBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		mainBtn.setBounds(1144, 10, 97, 86);
		this.add(mainBtn);
		
		JLabel lblNewLabel_2 = new JLabel("메뉴 목록");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("굴림", Font.PLAIN, 40));
		lblNewLabel_2.setBackground(Color.WHITE);
		lblNewLabel_2.setBounds(635, 50, 472, 63);
		this.add(lblNewLabel_2);
		
		JTable table = new JTable(model);
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(662, 120, 430, 350);
		this.add(scrollpane);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.LIGHT_GRAY);
		panel_1.setBounds(645, 40, 472, 468);
		this.add(panel_1);
		
		JTextField successTF = new JTextField();
		successTF.setFont(new Font("굴림", Font.PLAIN, 16));
		successTF.setText("메뉴 추가(수정) 성공");
		successTF.setBounds(95, 41, 175, 44);
		successResult.add(successTF);
		successTF.setColumns(15);
		
		JButton closeBtn = new JButton("확인");
		closeBtn.setBounds(134, 137, 97, 34);
		successResult.add(closeBtn);
		
		JTextField successTF2 = new JTextField();
		successTF2.setFont(new Font("굴림", Font.PLAIN, 16));
		successTF2.setText("메뉴 삭제 성공");
		successTF2.setBounds(95, 41, 175, 44);
		successResult2.add(successTF2);
		successTF2.setColumns(15);
		
		JButton closeBtn2 = new JButton("확인");
		closeBtn2.setBounds(134, 137, 97, 34);
		successResult2.add(closeBtn2);
		
		JTextField deleteCheckTF = new JTextField();
		deleteCheckTF.setFont(new Font("굴림", Font.PLAIN, 16));
		deleteCheckTF.setText("정말 삭제 하시겠습니까?");
		deleteCheckTF.setBounds(95, 41, 175, 44);
		deleteCheck.add(deleteCheckTF);
		deleteCheckTF.setColumns(15);
		
		JButton deleteBtn1 = new JButton("확인");
		deleteBtn1.setBounds(134, 137, 97, 34);
		deleteCheck.add(deleteBtn1);
		
		JButton cancelBtn = new JButton("취소");
		cancelBtn.setBounds(134, 200, 97, 34);
		deleteCheck.add(cancelBtn);
		sameNameError.add(cancelBtn);
		
		/* select한 메뉴 리스트를 미리 선언한 배열에 담아 테이블 model에 행으로 추가 */
		menulist = selectMenu();
		for (int i = 0; i < menulist.size(); i++) {
			MenuCategoriSizeDTO menu = menulist.get(i);
			recode[0] = menu.getMenuName();
			recode[1] = menu.getUnitPrice();
			recode[2] = menu.getCategoryName();
			model.addRow(recode);
		}
		
		// 추가 가능 버튼(텍스트 필드 수정 제한 헤제)
		insertBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				menuTF.setText("");
				priceTF.setText("");
				categoryTF.setText("");
				
				insertBtn.setVisible(true);
				menuTF.setEditable(true);
				priceTF.setEditable(true);
				categoryTF.setEditable(true);
			}
		});
		
		// 수정 가능 버튼(텍스트 필드 수정 제한 헤제)
		updateBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(index == 5000) {
					 
				} else {
					updateBtn.setVisible(true);
					menuTF.setEditable(true);
					priceTF.setEditable(true);
					categoryTF.setEditable(true);
				}
				
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
		
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteCheck.setVisible(false);
				sameNameError.setVisible(false);
				
				insertBtn.setVisible(false);
				updateBtn.setVisible(false);
				menuTF.setEditable(false);
				priceTF.setEditable(false);
				categoryTF.setEditable(false);
			}
		});
		
		/* 메뉴 추가 팝업 확인 버튼 클릭 시 테이블 내용 리프레쉬 */
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				successResult.setVisible(false);
				
				insertBtn.setVisible(false);
				updateBtn.setVisible(false);
				menuTF.setEditable(false);
				priceTF.setEditable(false);
				categoryTF.setEditable(false);
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setNumRows(0);
				
				menulist = selectMenu();
				for (int i = 0; i < menulist.size(); i++) {
					MenuCategoriSizeDTO menu = menulist.get(i);
					recode[0] = menu.getMenuName();
					recode[1] = menu.getUnitPrice();
					recode[2] = menu.getCategoryName();
					
					model.addRow(recode);
				}
			}
		});
		
		/* 메뉴 삭제 팝업 확인 버튼 클릭 시 테이블 내용 리프레쉬 */
		closeBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				successResult2.setVisible(false);
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setNumRows(0);
				
				menulist = selectMenu();
				for (int i = 0; i < menulist.size(); i++) {
					MenuCategoriSizeDTO menu = menulist.get(i);
					recode[0] = menu.getMenuName();
					recode[1] = menu.getUnitPrice();
					recode[2] = menu.getCategoryName();
					
					model.addRow(recode);
					
					menuTF.setText("");
					priceTF.setText("");
					categoryTF.setText("");
				}
			}
		});
		
		/* 선택한 테이블 행의 정보를 text field에 담는 이벤트 */
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				index = table.getSelectedRow();
				
				MenuCategoriSizeDTO in = menulist.get(index);
				
//				String menuName = in.getMenuName();
				String price = Integer.toString(in.getUnitPrice()); 
//				String category = in.getCategoryName();
				
				menuTF.setText(in.getMenuName());
				priceTF.setText(price);
				categoryTF.setText(in.getCategoryName());
			}
		});
		
		/* 메뉴 추가 이벤트 */
		insertBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int price = Integer.parseInt(priceTF.getText());
				int category = categoryChange(categoryTF.getText());
				
				menu.setMenuName(menuTF.getText());
				menu.setUnitPrice(price);
				menu.setCategoryCode(category);
				
				inputMenu();
			}
		});
		
		/* 메뉴 수정 이벤트 */
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuCategoriSizeDTO selectmenu = menulist.get(index);
				
				int price = Integer.parseInt(priceTF.getText());
				int category = categoryChange(categoryTF.getText()); 
				
				menu.setMenuCode(selectmenu.getMenuCode());
				menu.setMenuName(menuTF.getText());
				menu.setUnitPrice(price);
				menu.setCategoryCode(category);
				
				updateMenu();
			}
		});
		
		/* 메뉴 삭제 이벤트 */
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteCheck.setVisible(true);
			}
		});
		
		deleteBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuCategoriSizeDTO selectmenu = menulist.get(index);
				
				menu.setMenuCode(selectmenu.getMenuCode());
				
				deleteCheck.setVisible(false);
				
				deleteMenu();
			}
		});
		
	} // gui 끝
	
	/* 메뉴 select 메소드 */
	public List<MenuCategoriSizeDTO> selectMenu() {
		MenuController menuController = new MenuController();
		
		List<MenuCategoriSizeDTO> menulist = menuController.selectMenu2();
		return menulist;
	}
	
	/* 메뉴 insert 메소드 */
	public void inputMenu() {
		int result = menuController.insertMenu(menu);
		
		displayDmlResult(result);
	}
	
	/* 메뉴 update 메소드 */
	public void updateMenu() {
		int result = menuController.updateMenu(menu);
		
		displayDmlResult(result);
	}
	
	/* 메뉴 delete 메소드 */
	public void deleteMenu() {
		int result = menuController.deleteMenu(menu);
		
		displaydeleteResult(result);
	}

	/* 메뉴 추가 시 팝업 창 띄우는 메소드 */
	public void displayDmlResult(int result) {
		System.out.println(result);
		if(result > 0 && result < 100) {
			successResult.setVisible(true);
		} else if(result == 7777) {
			sameNameError.setVisible(true);
		}
	}
	
	/* 메뉴 삭제 시 팝업 창 띄우는 메소드 */
	public void displaydeleteResult(int result) {
		if(result > 0) {
			successResult2.setVisible(true);
		} else {
//			failedResult.setvisible(true);
		}
	}
	
	/* 패널 변경 메소드(메인메소드로) */
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.repaint();
	}
	
	public int categoryChange(String category) {
		int category1 = 0;
		if (category.equals("커피")) {
			category1 = 1;
		} else if (category.equals("음료")) {
			category1 = 2;
		} else if (category.equals("빵")) {
			category1 = 3;
		}
		
		return category1;
	}

}
