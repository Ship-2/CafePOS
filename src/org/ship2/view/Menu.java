package org.ship2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Scanner;

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
import org.ship2.model.dto.MenuCategoriSizeDTO;
import org.ship2.model.dto.MenuDTO;

public class Menu extends JPanel {
	
	private Scanner sc = new Scanner(System.in);
	
	private MainFrame mf;	// 프레임 객체 생성
	private String colNames[] = {"메뉴명", "가격", "카테고리"};	// 테이블 컬럼 배열 생성
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);	// 테이블 모델 객체 생성(타이틀 포함)
	private JScrollPane scrollpane = new JScrollPane();		// 테이블을 붙일 스크롤팬 생성
	private Object recode[] = new Object[3];	// 메뉴 리스트 담을 배열 생성
	private MenuDTO menu = new MenuDTO();	// 메뉴DTO 객체 생성
	private MenuController menuController = new MenuController();	// 메뉴 컨트롤러 객체 생성
	private JPanel successResult;	// 팝업 패널 생성
	private JPanel successResult2;	// 팝업 패널
	private JPanel deleteCheck;		// 팝업 패널
	private JPanel sameNameError;	// 팝업 패널
	private List<MenuCategoriSizeDTO> menulist; // 메뉴 리스트 객체
	private int index = 5000;	// 테이블 행 값 초기화(예외상황 대비)
	private JTextField menuTF;		// 메뉴 텍스트 필드
	private JTextField categoryTF;	// 메뉴 텍스트 필드
	private JTextField priceTF;		// 가격 텍스트 필드
	private JButton updateBtn;		// 수정 버튼
	private JButton insertBtn;		// 추가 버튼
	private JLabel sameNameErrorTF;	// 팝업 메세지
	private JLabel tochTable;		// 팝업 메세지
	private JLabel notInsert; 		// 팝업 메세지
	private JLabel notcategory; 	// 팝업 메세지
	
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
		
		JLabel successTF = new JLabel();
		successTF.setFont(new Font("굴림", Font.PLAIN, 16));
		successTF.setText("메뉴 추가(수정) 성공");
		successTF.setBounds(95, 41, 175, 44);
		successResult.add(successTF);
		
		JButton closeBtn = new JButton("확인");
		closeBtn.setBounds(134, 137, 97, 34);
		successResult.add(closeBtn);
		
		successResult2 = new JPanel();
		successResult2.setBackground(Color.LIGHT_GRAY);
		successResult2.setBounds(377, 182, 361, 212);
		successResult2.setVisible(false);
		this.add(successResult2);
		
		JLabel successTF2 = new JLabel();
		successTF2.setFont(new Font("굴림", Font.PLAIN, 16));
		successTF2.setText("메뉴 삭제 성공");
		successTF2.setBounds(95, 41, 175, 44);
		successResult2.add(successTF2);
		
		JButton closeBtn2 = new JButton("확인");
		closeBtn2.setBounds(134, 137, 97, 34);
		successResult2.add(closeBtn2);
		
		sameNameError = new JPanel();
		sameNameError.setBackground(Color.LIGHT_GRAY);
		sameNameError.setBounds(377, 182, 361, 212);
		sameNameError.setVisible(false);
		this.add(sameNameError);
		
		deleteCheck = new JPanel();
		deleteCheck.setBackground(Color.LIGHT_GRAY);
		deleteCheck.setBounds(377, 182, 361, 212);
		deleteCheck.setVisible(false);
		this.add(deleteCheck);
		
		JLabel deleteCheckTF = new JLabel();
		deleteCheckTF.setFont(new Font("굴림", Font.PLAIN, 16));
		deleteCheckTF.setText("정말 삭제 하시겠습니까?");
		deleteCheckTF.setBounds(95, 41, 175, 44);
		deleteCheck.add(deleteCheckTF);
		
		JButton deleteBtn1 = new JButton("확인");
		deleteBtn1.setBounds(134, 137, 97, 34);
		deleteCheck.add(deleteBtn1);
		
		sameNameErrorTF = new JLabel();
		sameNameErrorTF.setFont(new Font("굴림", Font.PLAIN, 16));
		sameNameErrorTF.setText("이미 있는 메뉴입니다.");
		sameNameErrorTF.setBounds(95, 41, 175, 44);
		sameNameErrorTF.setVisible(false);
		sameNameError.add(sameNameErrorTF);
		
		tochTable = new JLabel();
		tochTable.setFont(new Font("굴림", Font.PLAIN, 16));
		tochTable.setText("메뉴를 먼저 선택해주세요");
		tochTable.setBounds(95, 41, 175, 44);
		tochTable.setVisible(false);
		sameNameError.add(tochTable);
		
		notInsert = new JLabel();
		notInsert.setFont(new Font("굴림", Font.PLAIN, 16));
		notInsert.setText("값을 다 입력해 주세요");
		notInsert.setBounds(95, 41, 175, 44);
		notInsert.setVisible(false);
		sameNameError.add(notInsert);
		
		notcategory = new JLabel();
		notcategory.setFont(new Font("굴림", Font.PLAIN, 16));
		notcategory.setText("없는 카테고리 입니다.");
		notcategory.setBounds(95, 41, 175, 44);
		notcategory.setVisible(false);
		sameNameError.add(notcategory);
		
		JButton cancelBtn = new JButton("취소");
		cancelBtn.setBounds(134, 200, 97, 34);
		deleteCheck.add(cancelBtn);
		sameNameError.add(cancelBtn);
		
		insertBtn = new JButton("추가");
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
		
		updateBtn = new JButton("수정");
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
		
		menuTF = new JTextField();
		menuTF.setBounds(218, 130, 254, 42);
		this.add(menuTF);
		menuTF.setEditable(false);
		menuTF.setColumns(10);
		
		priceTF = new NumberField();
		priceTF.setColumns(10);
		priceTF.setEditable(false);
		priceTF.setBounds(218, 210, 254, 42);
		this.add(priceTF);
		
		categoryTF = new JTextField();
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
		
		/* select한 메뉴 리스트를 미리 선언한 배열에 담아 테이블 model에 행으로 추가 */
		selectMenu();
		
		// 추가 가능 버튼(텍스트 필드 수정 제한 헤제)
		insertBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				notSetTF();
				insertBtn.setVisible(true);
				setTF();
			}
		});
		
		// 수정 가능 버튼(텍스트 필드 수정 제한 헤제)
		updateBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(index == 5000) {
					sameNameError.setVisible(true);
					tochTable.setVisible(true);
				} else {
					updateBtn.setVisible(true);
					setTF();
				}
			}
		});
		
		/* 메인화면으로 돌아갈 이벤트 */
		mainBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage mainpage = new MainPage(mf);
				if (true) {
					mainpage.isManager = true;
					changePanel(mainpage);
				}
			}
		});
		
		/* 팝업 확인 버튼 클릭 시 해당 팝업 안보이게 처리하는 이벤트 */
		cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteCheck.setVisible(false);
				sameNameError.setVisible(false);
				sameNameErrorTF.setVisible(false);
				tochTable.setVisible(false);
				notcategory.setVisible(false);
				notInsert.setVisible(false);
				insertBtn.setVisible(false);
				updateBtn.setVisible(false);
				notSetTF();
			}
		});
		
		/* 메뉴 추가, 수정 팝업 확인 버튼 클릭 시 테이블 내용 리프레쉬 */
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
				
				selectMenu();
			}
		});
		
		/* 메뉴 삭제 팝업 확인 버튼 클릭 시 테이블 내용 리프레쉬 */
		closeBtn2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				successResult2.setVisible(false);
				
				DefaultTableModel model = (DefaultTableModel)table.getModel();
				model.setNumRows(0);
				
				selectMenu();
			}
		});
		
		/* 선택한 테이블 행의 정보를 text field에 담는 이벤트 */
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				index = table.getSelectedRow();
				MenuCategoriSizeDTO in = menulist.get(index);
				
				menuTF.setText(in.getMenuName());
				priceTF.setText(Integer.toString(in.getUnitPrice()));
				categoryTF.setText(in.getCategoryName());
			}
		});
		
		/* 메뉴 추가 이벤트 */
		insertBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* 예외 처리 */
				if(isTFSet() == 1)  {					// 빈 셀로 추가하려는 경우 예외 처리
					sameNameError.setVisible(true); 
					notInsert.setVisible(true);

				} else if(isTFSet() == 2) {				// 없는 카테고리로 추가하려는 경우 예외 처리
					sameNameError.setVisible(true); 
					notcategory.setVisible(true);
				} else {
					/* 멤버 변수로 선언해놓은 menuDTO 객체에 값 담기*/
					menu.setMenuName(menuTF.getText());
					menu.setUnitPrice(Integer.parseInt(priceTF.getText()));		// 형변환
					menu.setCategoryCode(categoryChange(categoryTF.getText()));	// 카테고리 코드로 변환
					
					inputMenu();	// 메뉴 추가 메소드 호출
				}
			}
		});
		
		/* 메뉴 수정 이벤트 */
		updateBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/* 예외 처리 */
				if(isTFSet() == 1)  {
					sameNameError.setVisible(true); 
					notInsert.setVisible(true);
				} else if(isTFSet() == 2) {
					sameNameError.setVisible(true); 
					notcategory.setVisible(true);
				} else {
					/* 메뉴 수정 */
					MenuCategoriSizeDTO selectmenu = menulist.get(index);	// 선택한 메뉴의 DTO 객체 생성
					/* 멤버 변수로 선언해 놓은 menuDTO 객체에 값 담기*/
					menu.setMenuCode(selectmenu.getMenuCode());		
					menu.setMenuName(menuTF.getText());
					menu.setUnitPrice(Integer.parseInt(priceTF.getText()));
					menu.setCategoryCode(categoryChange(categoryTF.getText()));
					
					updateMenu();	// 메뉴 수정 메소드 호출
				} 
			}
		});
		
		/* 메뉴 삭제 이벤트 */
		deleteBtn1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MenuCategoriSizeDTO selectmenu = menulist.get(index); // 선택한 메뉴의 DTO 객체 생성
				
				menu.setMenuCode(selectmenu.getMenuCode()); // 선택한 메뉴의 MenuCode(PK) 추출
				
				deleteCheck.setVisible(false);	// 삭제 확인 팝업 닫기
				
				deleteMenu();	// 메뉴 삭제 메소드 호출
			}
		});
		
		/* 메뉴 삭제 확인 메세지 */
		deleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(index == 5000) {
					sameNameError.setVisible(true);
					tochTable.setVisible(true);
				} else {
					deleteCheck.setVisible(true);
				}
			}
		});
		
	} // gui 끝
	
	/* 메뉴 리스트를 테이블 객체에 담는 메소드 */
	public void selectMenu() {
		menulist = menuController.selectMenu();			// 데이터베이스에 있는 메뉴 리스트 가져오는 메소드 호출
		for (int i = 0; i < menulist.size(); i++) {
			MenuCategoriSizeDTO menu = menulist.get(i);		// 데이터베이스에서 selet한 메뉴리스트를 DTO에 저장(메뉴 단위)
			recode[0] = menu.getMenuName();					// 테이블에 들어갈 배열에 메뉴 정보를 담음
			recode[1] = menu.getUnitPrice();
			recode[2] = menu.getCategoryName();
			model.addRow(recode);							// 테이블 모델에 해당 배열 추가
		}
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
		if(result > 0 && result < 1000) {
			successResult.setVisible(true);
		} else if(result == 7777) {		// 동일 메뉴명 추가 에러 발생
			sameNameErrorTF.setVisible(true);
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
	
	/* TF 값 입력 가능하게 하는 메소드 */
	public void setTF() {
		menuTF.setEditable(true);
		priceTF.setEditable(true);
		categoryTF.setEditable(true);
	}
	
	/* 추가, 수정, 삭제 완료 후 TF 값 비우고, 버튼 안보이게 처리하는 메소드 */
	public void notSetTF() {
		menuTF.setEditable(false);
		priceTF.setEditable(false);
		categoryTF.setEditable(false);
		index = 5000;
		menuTF.setText("");
		priceTF.setText("");
		categoryTF.setText("");
	}
	
	/* 입력 값 카테고리 코드로 변환 메소드 */ 
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
	
	/* 빈 셀(없는 카테고리)로 추가, 수정 하려는 경우 에러 메소드 */
	public int isTFSet() {
		int a = 0;
		/* 빈 셀로 추가 하려는 경우 */
		if(menuTF.getText().equals("") || priceTF.getText().equals("") || categoryTF.getText().equals("")) {
			a = 1;
		/* 없는 카테고리로 추가 하려는 경우 */
		} else if(!(categoryTF.getText().equals("커피") || categoryTF.getText().equals("음료") || categoryTF.getText().equals("빵"))) {
			a = 2;
		}
		return a;
	}

}

