package org.ship2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.ship2.controller.MenuController;
import org.ship2.model.dto.MenuCategoriSizeDTO;
import org.ship2.model.dto.MenuDTO;

public class OrderPage extends JPanel{
	private Scanner sc = new Scanner(System.in);
	
	private MainFrame mf;	// 프레임 객체 생성
	private String colNames[] = {"메뉴명", "가격", "카테고리"};	// 테이블 컬럼 배열 생성
	private DefaultTableModel model = new DefaultTableModel(colNames, 0);	// 테이블 모델 객체 생성(타이틀 포함)
	private JScrollPane scrollpane = new JScrollPane();		// 테이블을 붙일 스크롤팬 생성
	private Object recode[] = new Object[3];	// 메뉴 리스트 담을 배열 생성
	
	private String colNames1[] = {"메뉴명", "수량", "사이즈", "가격"};	// 테이블 컬럼 배열 생성
	private DefaultTableModel model1 = new DefaultTableModel(colNames1, 0);	// 테이블 모델 객체 생성(타이틀 포함)
	private JScrollPane scrollpane1 = new JScrollPane();		// 테이블을 붙일 스크롤팬 생성
	private Object recode1[] = new Object[4];	// 메뉴 리스트 담을 배열 생성
	
	private int index;
	private int index1;
	
	private MenuController menuController = new MenuController();	// 메뉴 컨트롤러 객체 생성
	private List<MenuCategoriSizeDTO> menulist; // 메뉴 리스트 객체
	private JPanel sizeChoice;
	private JTextField totalPrice;
	
	public OrderPage() {}
	
	public OrderPage(MainFrame mainFrame) {
		this.mf = mainFrame;
		this.setSize(1280, 720);
		this.setLayout(null);
		
		/* 컴포넌트 생성 */
		sizeChoice = new JPanel();
		sizeChoice.setBackground(Color.LIGHT_GRAY);
		sizeChoice.setBounds(445, 183, 361, 212);
		sizeChoice.setVisible(false);
		sizeChoice.setLayout(null);
		this.add(sizeChoice);
		
		JButton largeBtn = new JButton("Large");
		largeBtn.setBounds(241, 64, 95, 80);
		sizeChoice.add(largeBtn);
		
		JButton regularbtn = new JButton("Regular");
		regularbtn.setBounds(31, 64, 95, 80);
		sizeChoice.add(regularbtn);
		
		JButton mainBtn = new JButton("메인으로");
		mainBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		mainBtn.setBounds(1144, 10, 97, 86);
		this.add(mainBtn);
		
		totalPrice = new JTextField();
		totalPrice.setBounds(230, 474, 284, 49);
		totalPrice.setEditable(false);
		totalPrice.setColumns(10);
		this.add(totalPrice);
		
		JLabel totalPriceTF = new JLabel("총 금액 :");
		totalPriceTF.setFont(new Font("굴림", Font.PLAIN, 20));
		totalPriceTF.setBounds(127, 474, 116, 49);
		this.add(totalPriceTF);
		
		JButton allDeleteBtn = new JButton("전체 취소");
		allDeleteBtn.setBounds(120, 430, 95, 34);
		this.add(allDeleteBtn);
		
		JButton lineDeleteBtn = new JButton("라인 취소");
		lineDeleteBtn.setBounds(225, 430, 95, 34);
		this.add(lineDeleteBtn);
		
		JButton menuPlusBtn = new JButton("+");
		menuPlusBtn.setBounds(330, 430, 95, 34);
		this.add(menuPlusBtn);
		
		JButton menuMinusBtn = new JButton("-");
		menuMinusBtn.setBounds(435, 430, 95, 34);
		this.add(menuMinusBtn);
		
		JTable table = new JTable(model);
		scrollpane = new JScrollPane(table);
		scrollpane.setBounds(658, 73, 430, 350);
		this.add(scrollpane);
		
		JTable table1 = new JTable(model1);
		scrollpane1 = new JScrollPane(table1);
		scrollpane1.setBounds(116, 73, 430, 350);
		this.add(scrollpane1);
		
		/* 메뉴리스트를 테이블에 담아 표출하는 메소드 호출 */
		menuTable();
		
		/* 메인화면으로 돌아갈 이벤트 */
		mainBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainPage mainpage = new MainPage(mf);
				changePanel(mainpage);
			}
		});
		
		/* 선택한 테이블 행의 정보를 주문 항목에 담는 이벤트 */
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				index = table.getSelectedRow();
				MenuCategoriSizeDTO in = menulist.get(index);
				if(in.getCategoryCode() != 3) {
					popupPenel();
				} else if(in.getCategoryCode() == 3) {
					addBreadMenu();
				}
			}
		});
		
		/* 주문 메뉴 선택시 해당 행 변수에 담기 */
		table1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				index1 = table1.getSelectedRow();
			}
		});
		
		/* 라지 사이즈 메뉴 추가 이벤트 */
		largeBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMenu("large");
			}
		});
		
		/* 레귤러 사이즈 메뉴 추가 이벤트 */
		regularbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMenu("regular");
			}
		});
		
		/* 메뉴 라인 취소 이벤트 */ 
		lineDeleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteMenuLine();
			}
		});
		
		/* 메뉴 전체 취소 이벤트 */
		allDeleteBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteAllMenu();
			}
		});
		
		/* 메뉴 추가 이벤트 */
		menuPlusBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				plusMenu();
			}
		});
		
		menuMinusBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				minusMenu();
			}
		});
	}
	
	/* 메소드 모음 */
	
	/* 패널 변경 메소드(메인메소드로) */
	public void changePanel(JPanel panel) {
		mf.remove(this);
		mf.add(panel);
		mf.repaint();
	}
	
	/* 메뉴 리스트를 테이블 객체에 담는 메소드 */
	public void menuTable() {
		menulist = menuController.selectMenu2();			// 데이터베이스에 있는 메뉴 리스트 가쟈오는 메소드 호출
		for (int i = 0; i < menulist.size(); i++) {
			MenuCategoriSizeDTO menu = menulist.get(i);		// 데이터베이스에서 selet한 메뉴리스트를 DTO에 저장(메뉴 단위)
			recode[0] = menu.getMenuName();					// 테이블에 들어갈 배열에 메뉴 정보를 담음
			recode[1] = menu.getUnitPrice();
			recode[2] = menu.getCategoryName();
			model.addRow(recode);							// 테이블 모델에 해당 배열 추가
		}
	}
	
	/* 메뉴 선택 시 사이즈 팝업 띄우는 메소드 */
	public void popupPenel() {
		sizeChoice.setVisible(true);
	}
	
	/* 메뉴 라인 취소 메소드 */
	public void deleteMenuLine() {
		model1.removeRow(index1);	// 행 삭제
		totalPrice();	// 총 금액 변경 메소드 호출
	}
	
	/* 메뉴 전부 취소하는 메소드 */
	public void deleteAllMenu() {
		model1.setRowCount(0);	// 테이블 초기화
		totalPrice();	// 총 금액 변경 메소드 호출
	}
	
	/* 메뉴 수량 1개 추가 메소드 */
	public void plusMenu() {
		int count = Integer.parseInt(String.valueOf(model1.getValueAt(index1, 1))) + 1;		// 기존 수량에서 1을 더한 값을 변수에 저장
		int price = (int)model1.getValueAt(index1, 3) / (int)model1.getValueAt(index1, 1);	// 기존 가격에서 수량을 나누어 개당 가격을 변수에 저장
		
		model1.setValueAt(count, index1, 1);	// 기존 수량에서 1을 더한 값을 행에 변경 저장
		model1.setValueAt((count * price), index1, 3);	// 가격 변경 저장
		totalPrice();	// 변경 금액을 총 금액에 반영하는 메소드 호출
	}
	
	/* 메뉴 수량 1개 취소 메소드 */
	public void minusMenu() {
		int count = Integer.parseInt(String.valueOf(model1.getValueAt(index1, 1))) - 1;		// 기존 수량에서 1을 뺀 값을 변수에 저장
		int price = (int)model1.getValueAt(index1, 3) / (int)model1.getValueAt(index1, 1);	// 개당 가격을 변수에 저장
		
		if(count != 0) {
			model1.setValueAt(count, index1, 1);	// 기존 수량에서 1을 뺀 값을 행에 변경 저장
			model1.setValueAt((count * price), index1, 3);	// 가격 변경 저장
		} if(count == 0) {		
			deleteMenuLine();	// 수량이 0이되면 해당 행을 삭제 하는 메소드 호출
		}
		totalPrice();	// 변경 금액을 총 금액에 반영하는 메소드 호출
	}
	
	/* 사이즈가 있는 메뉴 추가 메소드 */
	public void addMenu(String size) {
		MenuCategoriSizeDTO in = menulist.get(index); 	// 선택한 행의 메뉴를 DTO에 담음
		boolean flag = true;	// 중복되는 메뉴가 없을 경우 수량,가격 변경이 아닌 추가하기 위한 플래그 
		int plus = 500;			// 라지 사이즈 선택 시 500원 추가
		
		for (int i = 0 ; i < model1.getRowCount(); i++) {
			int a = Integer.parseInt(String.valueOf(model1.getValueAt(i, 1))) + 1;	// 기존 수량에서 1을 더한 값을 변수에 저장
			if(model1.getValueAt(i, 0).equals(in.getMenuName()) && model1.getValueAt(i, 2).equals("large") && size == "large") {	// 동일 메뉴(사이즈)가 행에 존재하면서 라지 버튼을 눌렀을 때
				flag = false;	// 추가가 아니고 변경이므로 플래그를 false로 변환
				model1.setValueAt(a, i, 1);		// 기존수량에서 1을 더한 값을 행에 변경 저장
				model1.setValueAt(a * (in.getUnitPrice() + 500), i, 3);		// 메뉴 가격에 + 500 하여 수량만큼 곱한 값을 행에 변경 저장
				break;	// for문 종료
			} else if (model1.getValueAt(i, 0).equals(in.getMenuName()) && model1.getValueAt(i, 2).equals("regular") && size == "regular") {	// 동일 메뉴(사이즈)가 행에 존재하면서 레귤러 버튼을 눌렀을 때 
				flag = false;
				model1.setValueAt(a, i, 1);
				model1.setValueAt(a * (in.getUnitPrice()), i, 3);
				break;
			}
		}
		if (flag && size == "large") {	// 메뉴가 신규 추가 되는 경우, 라지 선택 시
			recode1[0] = in.getMenuName();
			recode1[1] = 1;
			recode1[2] = size;
			recode1[3] = in.getUnitPrice() + plus;
			
			model1.addRow(recode1);
		} else if(flag && size == "regular") {
			recode1[0] = in.getMenuName();
			recode1[1] = 1;
			recode1[2] = size;
			recode1[3] = in.getUnitPrice();
			
			model1.addRow(recode1);
		}
		sizeChoice.setVisible(false);	// 사이즈 선택 팝업 안보이게 처리
		totalPrice();	// 총 금액 값 표시 메소드 호출
	}
	
	/* 사이즈가 없는 메뉴 추가 메소드 */
	public void addBreadMenu() {
		MenuCategoriSizeDTO in = menulist.get(index);	// 선택한 행의 메뉴를 DTO에 담음
		boolean flag = true;	// 중복되는 메뉴가 없을 경우 수량,가격 변경이 아닌 추가하기 위한 플래그
		
		for (int i = 0 ; i < model1.getRowCount(); i++) {
			int a = Integer.parseInt(String.valueOf(model1.getValueAt(i, 1))) + 1;	// 기존 수량에서 1을 더한 값을 변수에 저장
			if(model1.getValueAt(i, 0).equals(in.getMenuName())) {		// 동일 메뉴 행에 존재할 때
				flag = false;	// 추가가 아니고 변경이므로 플래그를 false로 변환
				model1.setValueAt(a, i, 1);		// 기존수량에서 1을 더한 값을 행에 변경 저장
				model1.setValueAt(a * (in.getUnitPrice()), i, 3);	// 메뉴 가격에 수량만큼 곱한 값을 행에 변경 저장
				break;		// for문 종료
			}
		}
		
		if (flag) {		// 메뉴가 신규 추가 되는 경우
			recode1[0] = in.getMenuName();
			recode1[1] = 1;
			recode1[2] = "oneSize";
			recode1[3] = in.getUnitPrice();
			
			model1.addRow(recode1);
		}
		totalPrice();	// 총 금액 값 표시 메소드 호출
	}
	
	/* 총 금액 계산 메소드 */
	public void totalPrice() {
		int price = 0;
		for (int i = 0; i < model1.getRowCount(); i++) {	
			price += Integer.parseInt(String.valueOf(model1.getValueAt(i, 3)));		// 행의 갯수 만큼 for문을 돌려서 메뉴주문별 값을 누적 저장
		}
		totalPrice.setText(Integer.toString(price));	// Sting으로 변환하여 textfield에 저장(출력)
	}
}