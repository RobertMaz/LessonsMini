package persist;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int age;

    public User(int id, String name, int age, City city) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public User() {
    }

    @ManyToOne
    private City city;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
