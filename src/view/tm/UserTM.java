package view.tm;

import java.util.Objects;

public class UserTM {
    private String id;
    private String name;
    private String address;
    private String Email;
    private String mobileno;
    private String password;

    public UserTM(String id, String name, String address, String email, String mobileno, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        Email = email;
        this.mobileno = mobileno;
        this.password = password;
    }

    public UserTM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", Email='" + Email + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTM userTM = (UserTM) o;
        return Objects.equals(id, userTM.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
