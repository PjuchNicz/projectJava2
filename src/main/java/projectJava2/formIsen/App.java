package projectJava2.formIsen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import projectJava2.formIsen.daos.PersonDao;
import projectJava2.formIsen.person.Person;
import projectJava2.formIsen.transport.Export;
import projectJava2.formIsen.transport.Import;
import projectJava2.formIsen.transport.VcardFactory;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("controllers/primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException {
    	Export f = new Export();
    	//f.exportDataBase(".csv");
    	Import i = new Import();
    	i.reader();
    	VcardFactory vcardfactory = new VcardFactory();
    	PersonDao personFactory = new PersonDao();
    	for(Person p : personFactory.listPersons()) {
    		vcardfactory.vcardCreator(p);
    	}
    	//launch();
    }

}