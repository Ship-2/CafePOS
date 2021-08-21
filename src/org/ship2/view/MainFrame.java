package org.ship2.view;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	public MainFrame() {
		this.setSize(1280, 720);
		
		new MainPage(this);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
