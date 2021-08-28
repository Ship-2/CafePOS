package org.ship2.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

import org.ship2.controller.DailySalesGuiController;
import org.ship2.model.dto.DailySalesDTO;
import org.ship2.model.dto.EmployeeDTO;

public class DailySalesGuiView extends JPanel {

	private MainFrame mf;
	
	private String dailySalesColNames[] = {"날짜", "판매금액", "환불금액", "총매출금액"};	// 테이블 컬럼 배열 생성
	private DefaultTableModel dailySalesModel = new DefaultTableModel(dailySalesColNames, 0);  // 테이블 모델 객체 생성(타이틀 포함)
	private JScrollPane dailySalesScrollpane = new JScrollPane();  // 테이블을 붙일 스크롤팬 생성
	private JTable dailySalesTable;
	private List<DailySalesDTO> dailySalesList;

	public DailySalesGuiView(MainFrame mainFrame) {
		
		DailySalesGuiController dailySalesGuiController = new DailySalesGuiController();
		
		this.mf = mainFrame;
		
		this.setSize(1280, 720);
		this.setBackground(Color.LIGHT_GRAY);
		this.setLayout(null);
		
		JButton mainBtn = new JButton("메인으로");
		mainBtn.setFont(new Font("굴림", Font.PLAIN, 15));
		mainBtn.setBounds(1144, 10, 97, 86);
		this.add(mainBtn);
		
		JLabel setDateLabel = new JLabel("정산기간 선택 : ");
		setDateLabel.setBounds(69, 74, 214, 50);
		setDateLabel.setFont(new Font("나눔고딕", Font.PLAIN, 30));
		this.add(setDateLabel);
		
		/* DateModel */
		Calendar calendar = Calendar.getInstance();
	
		Date value = calendar.getTime();
		calendar.add(Calendar.YEAR, -50);
		Date start = calendar.getTime();
		calendar.add(Calendar.YEAR, 100);
		Date end = calendar.getTime();
		
		SpinnerDateModel dateModel1 = new SpinnerDateModel(value, start, end, Calendar.DATE);
		
		JSpinner startDateSpinner = new JSpinner(dateModel1);
		startDateSpinner.setBounds(300, 80, 270, 50);
		startDateSpinner.setFont(new Font("나눔고딕", Font.PLAIN, 30));
		startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy / MM / dd"));
		this.add(startDateSpinner);
		
		SpinnerDateModel dateModel2 = new SpinnerDateModel(value, start, end, Calendar.DATE);
		
		JSpinner endDateSpinner = new JSpinner(dateModel2);
		endDateSpinner.setBounds(580, 80, 270, 50);
		endDateSpinner.setFont(new Font("나눔고딕", Font.PLAIN, 30));
		endDateSpinner.setEditor(new JSpinner.DateEditor(endDateSpinner, "yyyy / MM / dd"));
		this.add(endDateSpinner);
		
		JButton sendbutton = new JButton("정산하기");
		sendbutton.setBounds(900, 80, 138, 50);
		sendbutton.setFont(new Font("나눔고딕", Font.PLAIN, 27));
		this.add(sendbutton);
		
		JTable dailySaleTable = new JTable(dailySalesModel);
		dailySalesScrollpane = new JScrollPane(dailySaleTable);
		dailySalesScrollpane.setBounds(150, 185, 980, 350);
		this.add(dailySalesScrollpane);
		
		JLabel infoLable = new JLabel("                                               '정산하기'을 누르면 당일 정산이 가능합니다.");
		infoLable.setBounds(51, 593, 1190, 48);
		infoLable.setFont(new Font("나눔고딕", Font.PLAIN, 25));
		this.add(infoLable);
		
		

		sendbutton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					
				/* Date를 코드에 사용하기 위한 용도로 String 가공  */
				SimpleDateFormat dateToString = new SimpleDateFormat("yy/MM/dd");
				String startDate = dateToString.format(startDateSpinner.getValue()); 
				String endDate = dateToString.format(endDateSpinner.getValue()); 
				
				
		/* ==================== 해당 날짜들의 DailySales select하기 ==================== */
				List<DailySalesDTO> dailySalesList = dailySalesGuiController.selectDailySales(startDate, endDate);
				
				dailySalesModel.setNumRows(0);
				int sumSales = 0;
				int sumRefund = 0;
				int sumTotalSales = 0;
					
				for (int i = 0; i < dailySalesList.size(); i++) {
					DailySalesDTO dailySales = dailySalesList.get(i);
						
					Object recode3[] = new Object[4];
					recode3[0] = dailySales.getSalesDate();
					recode3[1] = String.format("%,d", dailySales.getSales());
					recode3[2] = String.format("%,d", dailySales.getRefund());
					recode3[3] = String.format("%,d", dailySales.getTotalSales());
					dailySalesModel.addRow(recode3);
					
					
			/* ==================== 해당 날짜들의 DailySales **합계** select하기 ==================== */
					sumSales += dailySales.getSales();
					sumRefund += dailySales.getRefund();
					sumTotalSales += dailySales.getTotalSales();
				}
				infoLable.setText("정산 합계   :   판매금액 합계 = "
										+ String.format("%,d", sumSales) + "원,  환불금액 합계 = " 
										+ String.format("%,d", sumRefund) + "원,  총매출금액 합계 = " 
										+ String.format("%,d", sumTotalSales) + "원");
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
	}
	
	/* 패널 변경 메소드(메인메소드로) */
	public void changePanel(JPanel panel) {
		
		mf.remove(this);
		mf.getContentPane().add(panel);
		mf.repaint();
	}
}
