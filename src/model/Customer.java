package model;

import java.util.Objects;

public class Customer {
    private String id;
    private String name;
    private String Mobilenum;
    private String nic;

    public Customer(String id, String name, String mobilenum, String nic) {
        this.id = id;
        this.name = name;
        Mobilenum = mobilenum;
        this.nic = nic;
    }

    public Customer() {
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

    public String getMobilenum() {
        return Mobilenum;
    }

    public void setMobilenum(String mobilenum) {
        Mobilenum = mobilenum;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", Mobilenum='" + Mobilenum + '\'' +
                ", nic='" + nic + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
