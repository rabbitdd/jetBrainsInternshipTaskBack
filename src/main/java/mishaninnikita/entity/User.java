package mishaninnikita.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micronaut.serde.annotation.Serdeable;

import javax.persistence.*;

@Serdeable
@Entity
@Table(name="users")
public class User {

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User() {

    }
    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Column(name="login")
    private String login;

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Column(name="password")
    private String password;

    @Override
    public String toString() {
        return "login: " + this.login + "\n" + "password: " + this.password;
    }
}
