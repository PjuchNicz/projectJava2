package projectJava2.formIsen.controllers;

import javafx.application.Platform;
import projectJava2.formIsen.App;

public class MainLayoutController {

	public void closeApplication() {
		Platform.exit();
	}

	public void gotoHome() {
		App.showView("HomeScreen");
	}
	
	public void gotoFormAdd() {
		App.showView("Form");
	}

}
