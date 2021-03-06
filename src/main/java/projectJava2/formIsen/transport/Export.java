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
/**
* Export est une class qui regroupe les méthodes pour exporter la base donnée vers différents fichiers
*/
public class Export {
	String filename;
	String separator;
	String extension;
	/**
	 * Builder de la class export.
	 * @param file le nom.extension du fichier
	 * @param separator le caractère qui séparera les différentes variables dans les fichiers
	 */
	public Export(String file,String separator) {
		this.filename = file.split("\\.")[0];
		this.extension = file.split("\\.")[1];
		this.separator = separator;
	}

	/**
	 * Methode pour ecrire une ligne dans un ficher
	 * @param liste liste de string sur laquelle la méthode va itérer pour ecrire chaque ligne
	 */
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
	/**
	 * Methode pour exporter la base de donnée dans un certain type de fichier csv,txt ou Vcard
	 */
	public void exportDataBase() throws IOException {
		PersonDao dao = new PersonDao();
		List<Person> listePersons = dao.listPersons();
		if(extension.equals("txt") || extension.equals("csv") ) {
			List<String> listestring = new ArrayList<String>();
			for(Person p : listePersons) {
				listestring.add(p.toString(separator));
			}
			writeFile(listestring);
		}
		else if(extension.equals("vcf")) {
			VcardFactory v = new VcardFactory();
			for(Person p : listePersons) {
				v.vcardCreator(p);
			}

		}
		else {
			System.out.println("Export : unknown extension");
		}




	}
}
