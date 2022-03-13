package projectJava2.formIsen.controllers;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import projectJava2.formIsen.App;

public class MainLayoutController {
	
	@FXML
	public TabPane mainTab;
	
	public void closeApplication() {
		Platform.exit();
	}

	public void gotoHome() throws IOException {
		try {
		App.showView("HomeScreen");
		}
		catch(Exception e){
			System.out.println("Loaded");
		}
	}
	
	public void gotoFormAdd() {
		//System.out.println(mainTab.getTabs());
		App.showView("Form");
	}
	
	public void gotoResearchPerson() {
		App.showView("ResearchPerson");
	}
	
	public void gotoImportExportDatabase() {
		App.showView("ImportExportDatabase");
	}
	
	public void addTab() {
		System.out.println(mainTab.getTabs());
		//final Tab tab = new Tab("Tab " + (mainTab.getTabs().size() + 1));
		//mainTab.getTabs().add(tab);
		//mainTab.getSelectionModel().select(tab);
	}
}
