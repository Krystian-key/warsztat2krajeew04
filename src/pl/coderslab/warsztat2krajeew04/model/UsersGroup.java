package pl.coderslab.warsztat2krajeew04.model;

public class UsersGroup {
    private int id;
    private String name;

    public UsersGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UsersGroup() {
    }

    public int getId() {
        return id;
    }

    public UsersGroup(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "UsersGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
