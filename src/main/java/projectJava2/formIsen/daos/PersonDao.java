package projectJava2.formIsen.daos;

import projectJava2.formIsen.person.Person;

import java.sql.*;
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
                                results.getDate("birth_date").toLocalDate());
                        listOfPersons.add(person);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfPersons;
    }

    public List<Person> listPersonsByFirstname() {
        throw new RuntimeException("Method is not yet implemented");
    }

    public Person addPerson(Person person) {
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "INSERT INTO person(lastname,firstname,nickname,phone_number,address,email_address,birth_date)" + "VALUES(?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(
                    sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, person.getLastname());
                statement.setString(2, person.getLastname());
                statement.setString(3, person.getNickname());
                statement.setString(4, person.getPhone_number());
                statement.setString(5, person.getAddress());
                statement.setString(6, person.getEmail_address());
                statement.setDate(7, Date.valueOf(person.getBirth_date()));
                statement.executeUpdate();
                ResultSet ids = statement.getGeneratedKeys();
                if (ids.next()) {
                    person.setId(ids.getInt(1));
                    return person;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}