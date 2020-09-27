package persist;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cityes")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private int size;

    public City() {
    }

    public City(int id, String name, int size, List<User> userList) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.userList = userList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @OneToMany(mappedBy = "city",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<User> userList;
}
