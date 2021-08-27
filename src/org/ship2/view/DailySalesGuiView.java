package org.ship2.view;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import org.ship2.controller.DailySalesGuiController;
import org.ship2.model.dto.DailySalesDTO;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class DailySalesGuiView extends JFrame {

	private JPanel contentPane;

	public DailySalesGuiView() {
		
		DailySalesGuiController dailySalesGuiController = new DailySalesGuiController();
		
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		/*
		 * setDatePanel
		 */
		JPanel setDatePanel = new JPanel();
		
		JLabel setDateLabel = new JLabel("정산기간 선택 : ");
		setDateLabel.setFont(new Font("나눔고딕", Font.PLAIN, 30));
		setDatePanel.add(setDateLabel);
		
		
		/* DateModel */
		Calendar calendar = Calendar.getInstance();
	
		Date value = calendar.getTime();
		calendar.add(Calendar.YEAR, -50);
		Date start = calendar.getTime();
		calendar.add(Calendar.YEAR, 100);
		Date end = calendar.getTime();
		
		/* spinner */
		SpinnerDateModel dateModel1 = new SpinnerDateModel(value, start, end, Calendar.DATE);
		
		JSpinner startDateSpinner = new JSpinner(dateModel1);
		startDateSpinner.setFont(new Font("나눔고딕", Font.PLAIN, 30));
		startDateSpinner.setEditor(new JSpinner.DateEditor(startDateSpinner, "yyyy / MM / dd"));
		setDatePanel.add(startDateSpinner);
		

		SpinnerDateModel dateModel2 = new SpinnerDateModel(value, start, end, Calendar.DATE);
		
		JSpinner endDateSpinner = new JSpinner(dateModel2);
		endDateSpinner.setFont(new Font("나눔고딕", Font.PLAIN, 30));
		endDateSpinner.setEditor(new JSpinner.DateEditor(endDateSpinner, "yyyy / MM / dd"));
		setDatePanel.add(endDateSpinner);
		
		
		JButton button = new JButton("전송");
		button.setFont(new Font("나눔고딕", Font.PLAIN, 27));
		setDatePanel.add(button);
		
		
		/*
		 * resultPanel
		 */
		JPanel resultPanel = new JPanel();
		
//		JLabel resultLabel = new JLabel("안녕하세요");
//		resultPanel.add(resultLabel);
//		resultLabel.setVerticalAlignment(SwingConstants.BOTTOM);
//		resultLabel.setFont(new Font("나눔고딕", Font.PLAIN, 30));
		
		JTextArea resultTextArea = new JTextArea();
		resultTextArea.setRows(18);
		resultTextArea.setColumns(50);
		resultTextArea.setFont(new Font("나눔고딕", Font.PLAIN, 25));
		resultPanel.add(resultTextArea);
		
		
		/*
		 * infoPanel
		 */
		JPanel infoPanel = new JPanel();
		
		JLabel infoLable = new JLabel("원하시는 정산내역 기간을 선택하세요.");
		infoPanel.add(infoLable);
		infoLable.setFont(new Font("나눔고딕", Font.PLAIN, 25));
		
		
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				/* Date를 gui에 출력하기 위한 용도로 String 가공  */
//				SimpleDateFormat dateToPrint = new SimpleDateFormat("yyyy년 MM월 dd일");
//				infoLable.setText(dateToPrint.format(startDateSpinner.getValue()) + "   ~   " 
//							     + dateToPrint.format(endDateSpinner.getValue()) + "  정산내역");
				
				
				/* Date를 코드에 사용하기 위한 용도로 String 가공  */
				SimpleDateFormat dateToString = new SimpleDateFormat("yy/MM/dd");
				String startDate = dateToString.format(startDateSpinner.getValue()); 
				String endDate = dateToString.format(endDateSpinner.getValue()); 
				
				
		/* ==================== 해당 날짜들의 DailySales select하기 ==================== */
				List<DailySalesDTO> dailySalesList = dailySalesGuiController.selectDailySales(startDate, endDate);
				
				String salesDate = "";
				int sales = 0;
				int refund = 0;
				int totalSales = 0;
				
				int sumSales = 0;
				int sumRefund = 0;
				int sumTotalSales = 0;
				
				resultTextArea.setText("	날짜	매출금액	환불금액	총매출금액\n\n");

				for (int i = 0 ; i < dailySalesList.size() ; i++) {
					DailySalesDTO dailySales = dailySalesList.get(i);
					
					salesDate = dailySales.getSalesDate().toString();
					sales = dailySales.getSales();
					refund = dailySales.getRefund();
					totalSales = dailySales.getTotalSales();
					
//					System.out.println(dailySales);
					resultTextArea.append("                   " + salesDate + "  	" 
												  + sales +"	"
												  + refund + "	" 
												  + totalSales + "\n\n");
					
			/* ==================== 해당 날짜들의 DailySales **합계** select하기 ==================== */
					sumSales += sales;
					sumRefund += refund;
					sumTotalSales += totalSales;
					
				}
//				resultTextArea.append("\n\n" + sumSales + sumRefund + sumTotalSales);
				infoLable.setText("정산 합계   :   판매금액 합계 = " + sumSales + "원,  환불금액 합계 = " 
															 + sumRefund + "원,  총매출금액 합계 = " 
															 + sumTotalSales + "원");
										}
		});
		
		contentPane.add(setDatePanel, BorderLayout.NORTH);
		contentPane.add(resultPanel, BorderLayout.CENTER);
		contentPane.add(infoPanel, BorderLayout.SOUTH);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
