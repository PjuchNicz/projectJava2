package projectJava2.formIsen.person;

import java.time.LocalDate;
import java.util.Date;

public class Person {
    private Integer idperson;
    private String lastname;
    private String firstname;
    private String nickname;
    private String phone_number;
    private String address;
    private String email_address;
    private LocalDate birth_date;

    public Person() {
    }

    public Person(Integer idperson, String lastname, String firstname, String nickname, String phone_number, String address,
                  String email_address, LocalDate birth_date) {
        this.idperson = idperson;
        this.lastname = lastname;
        this.firstname = firstname;
        this.nickname = nickname;
        this.phone_number = phone_number;
        this.address = address;
        this.email_address = email_address;
        this.birth_date = birth_date;
    }

    public Integer getId() {
        return idperson;
    }

    public void setId(Integer id) {
        this.idperson = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(LocalDate birth_date) {
        this.birth_date = birth_date;
    }


}

