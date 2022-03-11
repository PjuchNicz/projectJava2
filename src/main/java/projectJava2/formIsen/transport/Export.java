package projectJava2.formIsen.transport;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import projectJava2.formIsen.daos.PersonDao;
import projectJava2.formIsen.person.Person;
public class Export {
	String filename;
	String separator;
	String extension;
	public Export(String file,String separator) {
		this.filename = file.split("\\.")[0];
		this.extension = file.split("\\.")[1];
		this.separator = separator;


		String[] leo_friend = {"mael.nivel@student.junia.com","a"};
		String[] mael_friend = {"leo.arnoult-de-almeidal@student.junia.com","b"};
		PersonDao dao = new PersonDao();
		Person leo = dao.addPerson("Ada","Léo","Leotarie","0781436035","Boulogne","leo.arnoult-de-almeida@student.junia.com",LocalDate.now(),leo_friend);
		Person mael = dao.addPerson("Nivel","Mael","Rage","0646627429","Arras","mael.nivel@student.junia.com",LocalDate.now(),mael_friend);		
    }
	
	
	public void writeFile(List<String> liste) throws IOException {
		String projectDirectory = System.getProperty("user.dir");
		Path root = Paths.get(projectDirectory);
		BufferedWriter bufferedWriter = Files.newBufferedWriter(root.resolve(filename.concat("."+extension)), StandardCharsets.UTF_8);
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
	public void exportDataBase() throws IOException {
		PersonDao dao = new PersonDao();
		List<Person> listePersons = dao.listPersons();
		if(extension.equals("txt") || extension.equals("csv") ) {
			List<String> listestring = new ArrayList<String>();
			for(Person p : listePersons) { //Pour tout les utilisateurs mettre la version string dans la liste
				listestring.add(p.toString(separator));
			}
			writeFile(listestring);
		}
		else if(extension.equals("vcf")) {
			
		}
		else {
			System.out.println("Export : unknown extension");
		}
		
		
		
		
	}
}
