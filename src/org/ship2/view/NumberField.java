package org.ship2.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextField;

public class NumberField extends JTextField implements KeyListener {
	private static final long serialVersionUID = 1;
 
	public NumberField() {
		addKeyListener(this);
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
 
		if (!Character.isDigit(c)) {
			e.consume();
			return;
		}
	}
}
