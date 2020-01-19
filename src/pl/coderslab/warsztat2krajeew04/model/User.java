package pl.coderslab.warsztat2krajeew04.model;

import org.mindrot.jbcrypt.BCrypt;

public class User {
   private int  id;
   private String  username;
   private String  email;
   private String  password;

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        setPassword(password);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = BCrypt.hashpw(password, BCrypt.gensalt());
    }
    public boolean checkPassword(String plain){
        return BCrypt.checkpw(plain, this.password);
    }

    public User(){

    }
}
