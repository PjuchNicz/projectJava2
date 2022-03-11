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
import java.util.Arrays;
import java.util.List;

import projectJava2.formIsen.daos.PersonDao;
import projectJava2.formIsen.person.Person;

public class Import {
	String separator;
	String file;
	PersonDao dao = new PersonDao();

	public Import(String file,String separator){
		this.file = file;
		this.separator = separator;
	}

	public void importDataBase() throws IOException {
		String projectDirectory = System.getProperty("user.dir");
		Path path = Paths.get(projectDirectory).resolve(file);
		BufferedReader bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
		String line;
		while ((line = bufferedReader.readLine()) != null) {
			stringToPersonDataBase(line);
		}
		dao.listPersons().forEach(p -> System.out.println(p.toString(",")));
	}
	public void stringToPersonDataBase(String ligne){
		String[] stringArray = ligne.split(separator);
		Person p = dao.addPerson(stringArray[1],
		stringArray[2],
		stringArray[3],
		stringArray[4],
		stringArray[5],
		stringArray[6],
		LocalDate.parse(stringArray[7]),
		stringArray[8].replaceAll("[\\[\\](){}\\s]","").split(","));
	}
	
}
