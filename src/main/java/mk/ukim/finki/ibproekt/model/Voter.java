package mk.ukim.finki.ibproekt.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Data
public class Voter implements UserDetails {

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
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public Voter(String username,Role role, String password, String name, String surname, String ssn, String address, Integer years, boolean voted,String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
        this.address = address;
        this.years = years;
        this.voted = voted;
        this.email=email;
        this.role = role;
    }

    public Voter() {
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

}
