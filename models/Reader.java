package models;

public class Reader {
    private int id;
    private String name;
    private String surname;
    private String email;
    private String password;


    public Reader(int id, String name, String surname, String email) {
        setId(id);
        setName(name);
        setSurname(surname);
        setEmail(email);
    }
    public Reader(String name, String surname, String email, String password) {
        setName(name);
        setSurname(surname);
        setEmail(email);
        setPassword(password);
    }
    public Reader(int id, String name, String surname, String email, String password) {
        this(id, name, surname, email);
        setPassword(password);
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
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "id: " + id + ", name: '" + name + "', surname: '" + surname + "', email: '" + email ;
    }
}
