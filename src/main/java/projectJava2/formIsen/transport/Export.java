package projectJava2.formIsen.transport;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import projectJava2.formIsen.daos.PersonDao;
import projectJava2.formIsen.person.Person;
public class Export {
	String filename = "test";
	
	public Export() {
		PersonDao dao = new PersonDao();
		Person leo = new Person(2,"Ada","Léo","Leotarie","0781436035","Boulogne","leo.arnoult-de-almeida@student.junia.com",LocalDate.now());
		Person mael = new Person(3,"Nivel","Mael","Rage","0646627429","Arras","mael.nivel@student.junia.com",LocalDate.now());
		//dao.addPerson(leo);
		//dao.addPerson(mael);		
    }
	
	
	public void writeFile(List<String> liste,String extension) throws IOException {
		String projectDirectory = System.getProperty("user.dir");
		Path root = Paths.get(projectDirectory);
		BufferedWriter bufferedWriter = Files.newBufferedWriter(root.resolve(filename.concat(extension)), StandardCharsets.UTF_8);
		liste.forEach(p -> {
			try {
				bufferedWriter.write(p);
				bufferedWriter.write("\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
		bufferedWriter.flush();

	}
	public void exportDataBase(String extension) throws IOException {
		String separator = ",";
		
		PersonDao dao = new PersonDao();
		List<Person> listePersons = dao.listPersons();
		List<String> listestring = new ArrayList<String>();
		for(Person p : listePersons) {
			String personString= "";
			for(String s : p.toStringList()) {
				personString = personString.concat(s+separator);
			}
			personString = personString.substring(0, personString.length() - 1);
			listestring.add(personString);
		}
		writeFile(listestring,extension);
	}
}
