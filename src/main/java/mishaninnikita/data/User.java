package mishaninnikita.data;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="USERS")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

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
