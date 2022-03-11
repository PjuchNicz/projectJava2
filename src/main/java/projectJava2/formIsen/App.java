package projectJava2.formIsen;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import projectJava2.formIsen.daos.PersonDao;
import projectJava2.formIsen.person.Person;
import projectJava2.formIsen.transport.Export;
import projectJava2.formIsen.transport.Import;
import projectJava2.formIsen.transport.VcardFactory;

/**
 * @author Pierre Juchniewicz
 * 
 *
 */
public class App extends Application {

	private static Scene scene;
	
	// Lets add the main layout of our application as a static member, shall we ?
	// This will help us avoiding dodgy explicit casts
	private static BorderPane mainlayout;

	@Override
	public void start(Stage stage) throws IOException {
		// Nothing new here
		stage.setTitle("FormIsen");
		// Load the main layout from file
		mainlayout = loadFXML("MainLayout");
		// Back to normal, except we use our newly defined member. Seems cumbersome, but
		// it will make sense in two seconds
		scene = new Scene(mainlayout, 640, 480);
		stage.setScene(scene);
		stage.show();
		// This is also new for this PW : you have to call the default view you want to
		// see when the application launches
		App.showView("HomeScreen");
	}

	public static void setRoot(String fxml) throws IOException {
		scene.setRoot(loadFXML(fxml));
	}

	// Here is the real game changer: let's change Parent with something more, let's
	// say, generic. We can do this as fxmlLoader.load() specifically returns a
	// generic object.
	// Doing this allows us to call loadFXML to populate whatever object type we
	// think relevant, not only Parent objects (here, we also are looking for Nodes
	// ones indeed)
	private static <T> T loadFXML(String fxml) throws IOException {
		// As we will use this method a lot, and we have all our view in a specific
		// package, let's put it there to save some typing :)
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("controllers/" + fxml + ".fxml"));
		return fxmlLoader.load();
	}

	public static void main(String[] args) {
		//launch();
//		String[] leo_friend = {"mael.nivel@student.junia.com","a"};
//		String[] mael_friend = {"leo.arnoult-de-almeida@student.junia.com","b"};
//		PersonDao dao = new PersonDao();
//		Person leo = dao.addPerson("Ada","Léo","Leotarie","0781436035","Boulogne","leo.arnoult-de-almeida@student.junia.com",LocalDate.now(),leo_friend);
//		Person mael = dao.addPerson("Nivel","Mael","Rage","0646627429","Arras","mael.nivel@student.junia.com",LocalDate.now(),mael_friend);	
//		
//		Export e = new Export("export.vcf",";");
//		try {
//			e.exportDataBase();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		VcardFactory v = new VcardFactory();
//		
//		try {
//			v.cardToPerson("Léo","Ada");
//			v.cardToPerson("Mael","Nivel");
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		
//		Import i = new Import("export.csv",";");
//		try {
//			i.importDataBase();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		
	}

	/**
	 * @param rootElement updates the center of our layout with the @rootElement
	 *                    passed in parameter
	 */
	public static void showView(String rootElement) {
		try {
			// We can only set the center of a borderPane, not a Parent, so we rely on
			// either an explicit cast or our better generics implementation to convert our
			// scene and modify it.
			mainlayout.setCenter(loadFXML(rootElement));
		} catch (IOException e) {
			// Chances are that the file is not found. Nothing we can do, really, but as
			// IOException is checked, it would require us to add nasty support all over our
			// code
			// Instead, a better practice is to convert this checked exception into an
			// unchecked exception, and let it bubble up to the main thread, killing the
			// JVM. We could also close the app, but it would be dangerous, as some
			// resources could be opened somewhere and not correctly closed...
			// BEWARE ! you should ALWAYS keep the stacktrace complete. using the original
			// exception as an argument allows that !
			throw new IllegalArgumentException(e);
		}
	}
}
