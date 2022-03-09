package projectJava2.formIsen.daos;

import projectJava2.formIsen.person.Person;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static projectJava2.formIsen.daos.DataSourceFactory.getDataSource;

public class PersonDao {

    public List<Person> listPersons() {
        List<Person> listOfPersons = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (Statement statement = connection.createStatement()) {
                try (ResultSet results = statement.executeQuery("SELECT * FROM person")) {
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

    public List<Person> listPersonsByFirstname(String firstname) {
<<<<<<< Updated upstream
<<<<<<< Updated upstream
        //TODO listPersonByFirstname
        List<Person> listOfPersons = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM person WHERE firstname LIKE ?")) {
                statement.setString(1, firstname);
                try (ResultSet results = statement.executeQuery()) {
                    while (results.next()) {
                        System.out.println(results.getString("firstname"));
=======
=======
>>>>>>> Stashed changes
        List<Person> listOfPersons = new ArrayList<>();
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "SELECT * FROM person WHERE firstname=?";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, firstname);
                try (ResultSet results = statement.executeQuery()) {
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
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listOfPersons;
    }

    public Person addPerson(String lastname, String firstname, String nickname, String phone_number, String address, String email_address, LocalDate birth_date) {
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "INSERT INTO person(lastname,firstname,nickname,phone_number,address,email_address,birth_date)" + "VALUES(?,?,?,?,?,?,?)";
            try (PreparedStatement statement = connection.prepareStatement(
                    sqlQuery, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, lastname);
                statement.setString(2, firstname);
                statement.setString(3, nickname);
                statement.setString(4, phone_number);
                statement.setString(5, address);
                statement.setString(6, email_address);
                statement.setDate(7, Date.valueOf(birth_date));
                statement.executeUpdate();
                ResultSet ids = statement.getGeneratedKeys();
                if (ids.next()) {
                    return new Person(ids.getInt(1),lastname, firstname, nickname, phone_number, address, email_address, birth_date);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void modifyPerson(Person person) {
        try (Connection connection = getDataSource().getConnection()) {
            String sqlQuery = "UPDATE book set lastname=?, firstname=?, nickname=?, phone_number=?, address=?," +
                    "email_address=?, birth_date=? WHERE idperson=?";
            try (PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
                statement.setString(1, person.getLastname());
                statement.setString(2, person.getLastname());
                statement.setString(3, person.getNickname());
                statement.setString(4, person.getPhone_number());
                statement.setString(5, person.getAddress());
                statement.setString(6, person.getEmail_address());
                statement.setDate(7, Date.valueOf(person.getBirth_date()));
                statement.setInt(8, person.getId());
                int nbRows = statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Person deletePerson(Person person) {
        //TODO deletePerson
        throw new RuntimeException("Method is not yet implemented");
    }
}