package org.ship2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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

import org.ship2.controller.MenuController;
import org.ship2.model.dto.MenuCategoruSizeDTO;
import org.ship2.model.dto.MenuDTO;

public class Menu extends JPanel {
	
	private Scanner sc = new Scanner(System.in);
	
	private MainFrame mf;	// 프레임 객체 생성
	private String colNames[] = {"메뉴명", "가격", "카테고리", "사이즈"};	// 테이블 컬럼 배열 생성
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);	// 테이블 모델 객체 생성(타이틀 포함)
	private JScrollPane scrollpane = new JScrollPane();		// 테이블 에 붙일 스크롤팬 생성
	private Object recode[] = new Object[4];	// 메뉴 리스트 담을 배열 생성
	private MenuDTO menu = new MenuDTO();	// 메뉴DTO 객체 생성
	private MenuController menuController = new MenuController();	// 메뉴 컨트롤러 객체 생성
	private JPanel successResult;	// 팝업 패널 생성
	
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
		
		JButton insertBtn = new JButton("추가");
		insertBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		insertBtn.setBounds(60, 545, 97, 86);
		this.add(insertBtn);
		
		JLabel lblNewLabel = new JLabel("메뉴정보");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 40));
		lblNewLabel.setBounds(60, 50, 472, 63);
		this.add(lblNewLabel);
		
		JButton updateBtn = new JButton("수정");
		updateBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		updateBtn.setBounds(250, 545, 97, 86);
		this.add(updateBtn);
		
		JButton delteBtn = new JButton("삭제");
		delteBtn.setFont(new Font("굴림", Font.PLAIN, 20));
		delteBtn.setBounds(440, 545, 97, 86);
		this.add(delteBtn);
		
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
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("사이즈");
		lblNewLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_1.setFont(new Font("굴림", Font.PLAIN, 25));
		lblNewLabel_1_1_1_1.setBounds(79, 370, 127, 39);
		this.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("1:레귤러 2:라지 3:원사이즈");
		lblNewLabel_1_1_1_2_1.setToolTipText("");
		lblNewLabel_1_1_1_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_2_1.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_1_1_1_2_1.setBounds(60, 390, 162, 39);
		this.add(lblNewLabel_1_1_1_2_1);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("1:커피 2:음료 3:빵");
		lblNewLabel_1_1_1_2.setToolTipText("");
		lblNewLabel_1_1_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1_2.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_1_1_1_2.setBounds(79, 310, 127, 39);
		this.add(lblNewLabel_1_1_1_2);
		
		JTextField menuTF = new JTextField();
		menuTF.setBounds(218, 130, 254, 42);
		this.add(menuTF);
		menuTF.setColumns(10);
		
		JTextField priceTF = new JTextField();
		priceTF.setColumns(10);
		priceTF.setBounds(218, 210, 254, 42);
		this.add(priceTF);
		
		JTextField categoryTF = new JTextField();
		categoryTF.setColumns(10);
		categoryTF.setBounds(218, 290, 254, 42);
		this.add(categoryTF);
		
		JTextField sizeTF = new JTextField();
		sizeTF.setColumns(10);
		sizeTF.setBounds(218, 370, 254, 42);
		this.add(sizeTF);
		
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
		successTF.setFont(new Font("굴림", Font.PLAIN, 25));
		successTF.setText("메뉴 추가 성공");
		successTF.setBounds(95, 41, 175, 44);
		successResult.add(successTF);
		successTF.setColumns(10);
		
		JButton closeBtn = new JButton("확인");
		closeBtn.setBounds(134, 137, 97, 34);
		successResult.add(closeBtn);
		
		/* select한 메뉴 리스트를 미리 선언한 배열에 담아 테이블 model에 행으로 추가 */
		List<MenuCategoruSizeDTO> menulist = selectMenu();
		for (int i = 0; i < menulist.size(); i++) {
			MenuCategoruSizeDTO menu = menulist.get(i);
			recode[0] = menu.getMenuName();
			recode[1] = menu.getUnitPrice();
			recode[2] = menu.getCategoryName();
			recode[3] = menu.getSizeName();
			model.addRow(recode);
		}
		
		/* 메인화면으로 돌아갈 이벤트 */
		mainBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage mainpage = new MainPage(mf);
				changePanel(mainpage);
			}
		});
		
		/* 메뉴 추가 팝업 확인 버튼 클릭 시 테이블 내용 리프레쉬 */
		closeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				successResult.setVisible(false);
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setNumRows(0);
				
				List<MenuCategoruSizeDTO> menulist = selectMenu();
				for (int i = 0; i < menulist.size(); i++) {
					MenuCategoruSizeDTO menu = menulist.get(i);
					recode[0] = menu.getMenuName();
					recode[1] = menu.getUnitPrice();
					recode[2] = menu.getCategoryName();
					recode[3] = menu.getSizeName();
					
					model.addRow(recode);
				}
			}
		});
		
		/* 선택한 테이블 행의 정보를 textfield에 담는 이벤트 */
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int index = table.getSelectedRow();
				
				MenuCategoruSizeDTO in = menulist.get(index);
				
//				String menuName = in.getMenuName();
				String price = Integer.toString(in.getUnitPrice()); 
//				String category = in.getCategoryName();
//				String size = in.getSizeName();
				
				menuTF.setText(in.getMenuName());
				priceTF.setText(price);
				categoryTF.setText(in.getCategoryName());
				sizeTF.setText(in.getSizeName());
						
			}
		});
		
		/* 메뉴 추가 이벤트 */
		insertBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int price = Integer.parseInt(priceTF.getText());
				int category = Integer.parseInt(categoryTF.getText()); 
				int size = Integer.parseInt(sizeTF.getText()); 
				
				menu.setMenuName(menuTF.getText());
				menu.setUnitPrice(price);
				menu.setCategoryCode(category);
				menu.setSizeCode(size);
				
				inputMenu();
			}
		});
	}

	/* 메뉴 select 메소드 */
	public List<MenuCategoruSizeDTO> selectMenu() {
		MenuController menuController = new MenuController();
		
		List<MenuCategoruSizeDTO> menulist = menuController.selectMenu2();
		return menulist;
	}
	
	/* 메뉴 insert 메소드 */
	public void inputMenu() {
		int result = menuController.insertMenu(menu);
		
		displayDmlResult(result);
	}
	
	/* 메뉴 update 메소드 */
	public MenuDTO updateMenu() {
		
		MenuDTO menuDTO = new MenuDTO();
		
		sc.nextLine();
		System.out.println("메뉴명 : ");
		String menuName = sc.nextLine();
		System.out.println("가격 : ");
		int unitPrice = sc.nextInt();
		System.out.println("카테고리(커피:1, 음료:2, 빵:3) : ");
		int category = sc.nextInt();
		System.out.println("사이즈(Regular:1, large:2, one size:3) : ");
		int menuSize = sc.nextInt();
		
		menuDTO.setMenuName(menuName);
		menuDTO.setUnitPrice(unitPrice);
		menuDTO.setCategoryCode(category);
		menuDTO.setSizeCode(menuSize);
		
		return menuDTO;
		
	}

	/* 메뉴 추가 시 팝업 창 띄우는 메소드 */
	public void displayDmlResult(int result) {
		if(result > 0) {
			successResult.setVisible(true);
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

}
