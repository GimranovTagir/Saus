package user;

public class User {
    private String email;
    private String password;

    public User(String password, String email) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}