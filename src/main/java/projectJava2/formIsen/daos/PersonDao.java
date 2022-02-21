package projectJava2.formIsen.daos;

import projectJava2.formIsen.person.Person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static projectJava2.formIsen.daos.DataSourceFactory.getDataSource;

public class PersonDao {

    public List<Person> listPersons() {
        List<Person> listOfPersons = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet results = statement.executeQuery("SELECT * from person")) {
                    while (results.next()) {
                        Person person = new Person(results.getInt("idperson"),
                                results.getString("lastname"),
                                results.getString("firstname"),
                                results.getString("nickname"),
                                results.getString("phone_number"),
                                results.getString("address"),
                                results.getString("email_address"),
                                results.getDate("birth_date"));
                        System.out.println(person.getBirth_date());
                        listOfPersons.add(person);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfPersons;
    }
}