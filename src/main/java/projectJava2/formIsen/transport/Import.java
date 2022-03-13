package projectJava2.formIsen.transport;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import projectJava2.formIsen.daos.PersonDao;
import projectJava2.formIsen.person.Person;
/**
* Import est une class qui regroupe les méthodes pour importer une base donnée depuis un fichier
*/
public class Import {
	String separator;
	String file;
	PersonDao dao = new PersonDao();
	/**
	 * Builder de la class import.
	 * @param file le nom.extension du fichier
	 * @param separator le caractère qui séparera les différentes variables dans le fichier
	 */
	public Import(String file,String separator){
		this.file = file;
		this.separator = separator;
	}
	/**
	 * Methode pour importer la base de donnée depuis un fichier en insérant dans la bdd chaque personne
	 * @throws IOException 
	 */
	public void importDataBase() throws IOException {
		String projectDirectory = System.getProperty("user.dir");
		Path path = Paths.get(projectDirectory).resolve(file);
		BufferedReader bufferedReader;
		try {
			bufferedReader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringToPersonDataBase(line);
			}
			dao.listPersons().forEach(p -> System.out.println(p.toString(",")));
		} catch (IOException e) {
			throw new IOException("File not exist!");
		}
		
	}
	/**
	 * Transforme une ligne d'un fichier en personne dans la base de donnée
	 * @param ligne ligne dans un fichier
	 */
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
