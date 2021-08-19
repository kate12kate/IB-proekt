package mk.ukim.finki.ibproekt.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String name;

    private  String surname;

    private String ssn;

    private String address;

    private Integer years;

    private boolean voted;

    private String email;

    public Voter(String username, String password, String name, String surname, String ssn, String address, Integer years, boolean voted,String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.address = address;
        this.years = years;
        this.voted = voted;
        this.email=email;
    }

    public Voter() {
    }
}
