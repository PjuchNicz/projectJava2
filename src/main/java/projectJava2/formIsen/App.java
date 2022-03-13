package projectJava2.formIsen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import projectJava2.formIsen.daos.DataSourceFactory;

import static projectJava2.formIsen.daos.DataSourceFactory.getDataSource;
import static projectJava2.formIsen.daos.DataSourceFactory.initDb;

/**
 * @author Pierre Juchniewicz, Maël Nivel, Léo Arnoult De Almeida, Foucauld Bergerault
 * 
 *
 */
public class App extends Application {

	private static Scene scene;
	private static BorderPane mainlayout;

	@Override
	public void start(Stage stage) throws IOException {
		stage.setTitle("FormIsen");
		mainlayout = loadFXML("MainLayout");
		scene = new Scene(mainlayout, 1000, 480);
		stage.setScene(scene);
		stage.show();
		App.showView("HomeScreen");
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	private static <T> T loadFXML(String fxml) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("controllers/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		initDb();
		launch();
	}

	/**
	 * @param rootElement updates the center of our layout with the @rootElement
	 *                    passed in parameter
	 */
	public static void showView(String rootElement) {
		try {
			mainlayout.setCenter(loadFXML(rootElement));
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
