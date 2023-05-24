package view.tm;

import javafx.collections.ObservableList;

import java.util.Objects;

public class ItemTM {
    private String id;
    private String name;
    private String qtyonhand;
    private String categorie;
    private String description;
    private double price;

    public ItemTM(String id, String name, String qtyonhand, String categorie, String description, double price) {
        this.id = id;
        this.name = name;
        this.qtyonhand = qtyonhand;
        this.categorie = categorie;
        this.description = description;
        this.price = price;
    }

    public ItemTM() {
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

    public String getQtyonhand() {
        return qtyonhand;
    }

    public void setQtyonhand(String qtyonhand) {
        this.qtyonhand = qtyonhand;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ItemTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", qtyonhand='" + qtyonhand + '\'' +
                ", categorie='" + categorie + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemTM itemTM = (ItemTM) o;
        return Objects.equals(id, itemTM.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
