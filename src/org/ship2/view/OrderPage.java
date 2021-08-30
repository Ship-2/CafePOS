package org.ship2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.ship2.controller.DailySalesController;
import org.ship2.controller.MembershipController;
import org.ship2.controller.MenuController;
import org.ship2.controller.MenuOrderController;
import org.ship2.controller.MenuSizeController;
import org.ship2.controller.PosOrderController;
import org.ship2.model.dto.DailySalesDTO;
import org.ship2.model.dto.MembershipDTO;
import org.ship2.model.dto.MenuCategoriSizeDTO;
import org.ship2.model.dto.MenuDTO;
import org.ship2.model.dto.MenuOrderDTO;
import org.ship2.model.dto.MenuSizeDTO;
import org.ship2.model.dto.PosOrderDTO;

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
	
	private PosOrderController posOrderController = new PosOrderController();
	private MenuOrderController menuOrderController = new MenuOrderController();
	private MembershipController membershipController = new MembershipController();
	private MembershipDTO membershipDTO = new MembershipDTO();
	private JTextField memNumTF;
	private JTextField memPointTF;
	private boolean memFlag;
	private DailySalesController dailySalesController = new DailySalesController();
	private Date today;
	private JTextField orderCancleTF;
	
	private MenuSizeController menuSizeController = new MenuSizeController();
	
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
		
		JButton cashPayBtn = new JButton("현금 결제");
		cashPayBtn.setBounds(771, 586, 89, 61);
		this.add(cashPayBtn);
		
		JButton cardPayBtn = new JButton("카드 결제");
		cardPayBtn.setBounds(884, 586, 89, 61);
		this.add(cardPayBtn);
		
		JButton pointPayBtn = new JButton("포인트 결제");
		pointPayBtn.setBounds(995, 586, 103, 61);
		this.add(pointPayBtn);
		
		JLabel orderCancleLB = new JLabel("취소 주문 번호 :");
		orderCancleLB.setFont(new Font("굴림", Font.PLAIN, 15));
		orderCancleLB.setBounds(127, 586, 120, 49);
		this.add(orderCancleLB);
		
		orderCancleTF = new JTextField();
		orderCancleTF.setColumns(10);
		orderCancleTF.setBounds(240, 590, 200, 35);
		this.add(orderCancleTF);
		
		JButton orederCancleBtn = new JButton("결제 취소");
		orederCancleBtn.setBounds(450, 586, 89, 61);
		this.add(orederCancleBtn);
		
		memNumTF = new JTextField();
		memNumTF.setBounds(760, 450, 297, 41);
		memNumTF.setColumns(10);
		this.add(memNumTF);
		
		JLabel memNumLB = new JLabel("회원 전화번호");
		memNumLB.setFont(new Font("굴림", Font.PLAIN, 15));
		memNumLB.setBounds(655, 452, 116, 36);
		this.add(memNumLB);
		
		JButton selectMemBtn = new JButton("조회");
		selectMemBtn.setBounds(1060, 450, 60, 41);
		this.add(selectMemBtn);
		
		memPointTF = new JTextField();
		memPointTF.setEditable(false);
		memPointTF.setColumns(10);
		memPointTF.setBounds(760, 509, 297, 32);
		this.add(memPointTF);
		
		JLabel memPointLB = new JLabel("포인트 잔액 :");
		memPointLB.setFont(new Font("굴림", Font.PLAIN, 15));
		memPointLB.setBounds(655, 509, 116, 36);
		this.add(memPointLB);
		
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
		
		/* 메뉴 1개 감소 이벤트 */
		menuMinusBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				minusMenu();
			}
		});
		
		/* 멤버쉽 조회 버튼 이벤트 */
		selectMemBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectMem();
			}
		});
		
		/* 현금 결제 버튼 이벤트 */
		cashPayBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				payment();
			}
		});
		
		/* 카드 결제 버튼 이벤트 */
		cardPayBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				payment();
			}
		});
		
		/* 포인트 결제 버튼 이벤트 */ 
		pointPayBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				paymentPoint();
			}
		});
		
		/* 결제 취소 버튼 이벤트 */
		orederCancleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "주문 삭제 완료");
				deleteDailySales();
				orderCancleTF.setText("");
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
		menulist = menuController.selectMenu();			// 데이터베이스에 있는 메뉴 리스트 가쟈오는 메소드 호출
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
		int sizeCode = 0;
		
		for (int i = 0 ; i < model1.getRowCount(); i++) {
			int a = Integer.parseInt(String.valueOf(model1.getValueAt(i, 1))) + 1;	// 기존 수량에서 1을 더한 값을 변수에 저장
			if(model1.getValueAt(i, 0).equals(in.getMenuName()) && model1.getValueAt(i, 2).equals("large") && size == "large") {	// 동일 메뉴(사이즈)가 행에 존재하면서 라지 버튼을 눌렀을 때
				flag = false;	// 추가가 아니고 변경이므로 플래그를 false로 변환
				model1.setValueAt(a, i, 1);		// 기존수량에서 1을 더한 값을 행에 변경 저장
				
				if(model1.getValueAt(i, 2).equals("regular")) {
					sizeCode = 1;
				 } else if(model1.getValueAt(i, 2).equals("large")) {
					 sizeCode = 2;
				 } else if(model1.getValueAt(i, 2).equals("oneSize")) {
					 sizeCode = 3;
				 }
				
				MenuSizeDTO menuSize = menuSizeController.selectBySizeCode(sizeCode);
				model1.setValueAt(a * (in.getUnitPrice() + menuSize.getSizePrice()), i, 3);		// 메뉴 가격에 + 500 하여 수량만큼 곱한 값을 행에 변경 저장
				break;	// for문 종료
			} else if (model1.getValueAt(i, 0).equals(in.getMenuName()) && model1.getValueAt(i, 2).equals("regular") && size == "regular") {	// 동일 메뉴(사이즈)가 행에 존재하면서 레귤러 버튼을 눌렀을 때 
				flag = false;
				model1.setValueAt(a, i, 1);
				model1.setValueAt(a * (in.getUnitPrice()), i, 3);
				break;
			}
		}
		if (flag && size == "large") {	// 메뉴가 추가 되는 경우, 라지 선택 시
			recode1[0] = in.getMenuName();
			recode1[1] = 1;
			recode1[2] = size;
			recode1[3] = in.getUnitPrice() + plus;
			
			model1.addRow(recode1);
		} else if(flag && size == "regular") {	// 메뉴가 추가 되는 경우, 레귤러 선택 시
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
		
		if (flag) {		// 메뉴가 추가 되는 경우
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
	
	/* 일반 결제 메소드 */
	public void payment() {
		if(!memFlag) {
			PosOrderDTO order = new PosOrderDTO();
			order.setPayCode(1);
			int result = posOrderController.insertOrder(order);		// 인서트가 실패한 경우 result = 0
			insertOrderList();
			receipt();
		} else {
			PosOrderDTO order = new PosOrderDTO();
			order.setPayCode(1);
			order.setMemCode(membershipDTO.getMemCode());
			int result = posOrderController.insertMemOrder(order);		// 인서트가 실패한 경우 result = 0
			plusPoint();
			insertOrderList();
			memReceipt();
		}
		deleteAllMenu();
		setTFReset();
		insertDailySales();
		memFlag = false;
	}
	
	/* 결제 메뉴 insert 메소드 */
	public void insertOrderList() {
		List<MenuOrderDTO> menuOrderList = new ArrayList<>();
		for (int i = 0; i < model1.getRowCount(); i++) {
			 MenuOrderDTO menuOrder = new MenuOrderDTO();
			 int menuCode = menuController.seletMenuCode((String)model1.getValueAt(i, 0));
			 menuOrder.setMenuCode(menuCode);
			 
			 menuOrder.setQuan((int)model1.getValueAt(i, 1));
			 
			 if(model1.getValueAt(i, 2).equals("regular")) {
				 menuOrder.setSizeCode(1);
			 } else if(model1.getValueAt(i, 2).equals("large")) {
				 menuOrder.setSizeCode(2);
			 } else if(model1.getValueAt(i, 2).equals("oneSize")) {
				 menuOrder.setSizeCode(3);
			 }
			 menuOrderList.add(menuOrder);
		}
		insertOrder(menuOrderList);
	}
	
	/* 주문 insert 메소드 호출 */
	public void insertOrder(List<MenuOrderDTO> menuOrderList) {
		menuOrderController.insertMenuOrder(menuOrderList);
	}
	
	/* 멤버쉽 회원 조회 메소드 */
	public void selectMem() {
		membershipDTO = membershipController.selectMem(memNumTF.getText());
		memPointTF.setText(membershipDTO.getMemPoint() + "");
		memFlag = true;
	}
	
	/* 멤버쉽 조회 TF 초기화 메소드 */
	public void setTFReset() {
		memNumTF.setText("");
		memPointTF.setText("");
	}
	
	/* 멤버쉽 적립 메소드 */
	public void plusPoint() {
		MembershipDTO addPointMem = new MembershipDTO();
		addPointMem.setMemCode(membershipDTO.getMemCode());
		addPointMem.setMemPoint(membershipDTO.getMemPoint() + (int)(Integer.parseInt(totalPrice.getText()) * 0.05));
		membershipController.updateMemberPoint(addPointMem);
		
	}
	
	/* 멤버쉽 포인트 결제 메소드 */
	public void paymentPoint() {
		PosOrderDTO order = new PosOrderDTO();
		order.setPayCode(2);
		order.setMemCode(membershipDTO.getMemCode());
		int result = posOrderController.insertMemOrder(order);		// 인서트가 실패한 경우 result = 0
		insertOrderList();
		
		MembershipDTO addPointMem = new MembershipDTO();
		addPointMem.setMemCode(membershipDTO.getMemCode());
		addPointMem.setMemPoint(membershipDTO.getMemPoint() - Integer.valueOf(totalPrice.getText()));
		membershipController.updateMemberPoint(addPointMem);
		
		memPointReceipt();
		deleteAllMenu();
		setTFReset();
		insertDailySales();
		memFlag = false;
	}
	
	/* 미회원 결제 영수증 */
	public void receipt() {
		System.out.println("************ 영수증 ************");
		System.out.println("주문번호 : " + posOrderController.seletMenuCode());
		
		for (int i = 0; i < model1.getRowCount(); i++) {
			System.out.println(model1.getValueAt(i, 0) + "(" + model1.getValueAt(i, 2) + ") " + model1.getValueAt(i, 1) + "개,  가격 : " + model1.getValueAt(i, 3));
		}
		System.out.println("총 가격 : " + totalPrice.getText());
	}
	
	/* 회원 결제 영수증 */
	public void memReceipt() {
		System.out.println("************ 영수증 ************");
		System.out.println("주문번호 : " + posOrderController.seletMenuCode());
		
		for (int i = 0; i < model1.getRowCount(); i++) {
			System.out.println(model1.getValueAt(i, 0) + "(" + model1.getValueAt(i, 2) + ") " + model1.getValueAt(i, 1) + "개,  가격 : " + model1.getValueAt(i, 3));
		}
		System.out.println("총 가격 : " + totalPrice.getText());
		System.out.println("적립금 : " + (int)(Integer.parseInt(totalPrice.getText()) * 0.05));
		System.out.println("잔여 포인트 : " + (membershipDTO.getMemPoint() + (int)(Integer.parseInt(totalPrice.getText()) * 0.05)));
	}
	
	/* 포인트 결제 영수증 */
	public void memPointReceipt() {
		System.out.println("************ 영수증 ************");
		System.out.println("주문번호 : " + posOrderController.seletMenuCode());
		
		for (int i = 0; i < model1.getRowCount(); i++) {
			System.out.println(model1.getValueAt(i, 0) + "(" + model1.getValueAt(i, 2) + ") " + model1.getValueAt(i, 1) + "개,  가격 : " + model1.getValueAt(i, 3));
		}
		System.out.println("총 가격 : " + totalPrice.getText());
		System.out.println("잔여 포인트 : " + (membershipDTO.getMemPoint() - Integer.parseInt(totalPrice.getText())));
	}
	
	public void insertDailySales() {
		
		/* ==================== 해당 날짜의 DailySales select하기 ======================================================= */
		today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		String formDate = sdf.format(today);
		
		String dailySalesDate = formDate;
		
		DailySalesDTO dailySales = dailySalesController.selectDailySalesBySalesDate(dailySalesDate);
//		System.out.println(dailySales);
		
		//select한 결과를 이용해 첫주문인지 아닌지 판별
		if (dailySales.getSalesDate() == null) {
//			System.out.println("\n작성한 날짜에 해당되는 데이터가 없다.\n");
			
			/* 
			 * 당일 첫 주문시 DailySales에 insert
			 */
			String dateOfFirstOrder = dailySalesDate;
			int salesInsertResult =  dailySalesController.insertDailySales(dateOfFirstOrder);
			
		} else {
//			System.out.println("작성한 날짜에 해당되는 데이터가 있다.");
			/* 
			 * 당일 n번째 주문시 DailySales에 update
			 */
			String dateOfNthOrder = dailySalesDate;
			
			int insertOrderCode = posOrderController.seletMenuCode(); 
//			System.out.println(insertOrderCode);
			
			int salesUpdatetResult = dailySalesController.updateDailySalesByInsert(insertOrderCode, dateOfNthOrder);
		}
	}	
	
	public void deleteDailySales() {
		today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
		String formDate = sdf.format(today);
		
		String dateOfDelete = formDate;
		int deleteOrderCode = Integer.valueOf(orderCancleTF.getText()); 
		
		int refundUpdateResult = dailySalesController.updateDailySalesByDelete(deleteOrderCode, dateOfDelete);
	}
	
}
