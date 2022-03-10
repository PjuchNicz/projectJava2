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
	String separator;
	String file;
	List<Person> personList = new ArrayList<>();

	public Import(String file,String separator){
		this.file = file;
		this.separator = separator;
	}

	public void reader() throws IOException {
		String projectDirectory = System.getProperty("user.dir");
		Path path = Paths.get(projectDirectory).resolve(file);
		BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			getPersonList().add(stringToPerson(line));
		}
	}
	public Person stringToPerson(String ligne){
		String[] stringArray = ligne.split(separator);
		return new Person(
		Integer.parseInt(stringArray[0]),
		stringArray[1],
		stringArray[2],
		stringArray[3],
		stringArray[4],
		stringArray[5],
		stringArray[6],
		LocalDate.parse(stringArray[7]));
	}

    public void printList() {
    	getPersonList().forEach(p -> System.out.println(p.getFirstname()));
    }

    private void toDataBase() {
    	//TODO to data base ?
    }

	public List<Person> getPersonList() {
		return personList;
	}

	
}
