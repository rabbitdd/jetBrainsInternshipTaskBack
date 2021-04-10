package mishaninnikita.data;


import javax.persistence.*;

@Entity
@Table(name="TASKS")
public class Task {

    @Id
    @GeneratedValue
    private Integer id;

    public Task() {}

    public Task(String name, String owner, String color) {
        this.name = name;
        this.owner = owner;
        this.color = color;
    }

    @Column(name="task_name")
    private String name;

    @Column(name="owner")
    private String owner;

    @Column(name="color")
    private String color;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Task: " + this.name + " " + this.color + " " + this.owner;
    }
}
