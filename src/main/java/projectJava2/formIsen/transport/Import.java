package projectJava2.formIsen.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import projectJava2.formIsen.person.Person;

public class Import {
	List<Person> personList = new ArrayList<Person>();
	public Import(){
		
	}
	public void reader() throws IOException {
		String projectDirectory = System.getProperty("user.dir");
		Path root = Paths.get(projectDirectory);
		Path path = root.resolve("test.csv");
		BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		String line;
		   while ((line = bufferedReader.readLine()) != null) {
			   personList.add(stringToPerson(line));
		   }
	}
	public Person stringToPerson(String ligne){
		String[] stringArray = ligne.split(",");
		Person personne = new Person(
		Integer.parseInt(stringArray[0]),
		stringArray[1],
		stringArray[2],
		stringArray[3],
		stringArray[4],
		stringArray[5],
		stringArray[6],
		LocalDate.parse(stringArray[7]));
		return personne;
	}
    public void printList() {
    	personList.forEach(p -> System.out.println(p.getFirstname()));
    }


	
}
