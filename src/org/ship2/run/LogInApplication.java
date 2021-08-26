package org.ship2.run;

import org.ship2.view.LogIn;
import org.ship2.view.MainFrame;

public class LogInApplication {

	public static void main(String[] args) {
		new MainFrame();
		LogIn logIn = new LogIn();
		logIn.logInComplete();
	}
}