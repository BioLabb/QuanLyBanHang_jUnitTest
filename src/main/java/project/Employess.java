package project;

import java.util.Date;

public class Employess {
    private int id;
    private String lastName;
    private String firstName;

    private Date birth;

    public Employess(int id,String user, String lastName, String firstName, String email, String phone) {
        this.id = id;
        this.user = user;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
    }

    private String email;
    private String phone;
    private String address;
    private String user;
    private String password;
    private boolean maganer;

    public Employess(int id, String lastName, String firstName, String email, String phone, String address, String user, String password, boolean maganer) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.user = user;
        this.password = password;
        this.maganer = maganer;
    }

    public Employess(String lastName, String firstName, Date birth, String email, String phone, String address, String user, String password, boolean maganer) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birth = birth;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.user = user;
        this.password = password;
        this.maganer = maganer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isMaganer() {
        return maganer;
    }

    public void setMaganer(boolean maganer) {
        this.maganer = maganer;
    }
}
