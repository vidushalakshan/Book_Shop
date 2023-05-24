package view.tm;

import java.util.Objects;

public class CustomerTM {
    private String id;
    private String name;
    private String Mobilenum;
    private String nic;

    public CustomerTM(String id, String name, String mobilenum, String nic) {
        this.id = id;
        this.name = name;
        this.Mobilenum = mobilenum;
        this.nic = nic;
    }

    public CustomerTM() {
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
        this.Mobilenum = mobilenum;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    @Override
    public String toString() {
        return "CustomerTM{" +
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
        CustomerTM that = (CustomerTM) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
