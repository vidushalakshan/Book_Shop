package view.tm;

public class CartTM {
    private String id;
    private String cusid;
    private String categorie;
    private int qty;
    private double unitePrice;
    private double discount;
    private double cost;

    public CartTM(String id, String cusid, String categorie, int qty, double unitePrice, double discount, double cost) {
        this.id = id;
        this.cusid = cusid;
        this.categorie = categorie;
        this.qty = qty;
        this.unitePrice = unitePrice;
        this.discount = discount;
        this.cost = cost;
    }

    public CartTM() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitePrice() {
        return unitePrice;
    }

    public void setUnitePrice(double unitePrice) {
        this.unitePrice = unitePrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "CartTM{" +
                "id='" + id + '\'' +
                ", cusid='" + cusid + '\'' +
                ", categorie='" + categorie + '\'' +
                ", qty=" + qty +
                ", unitePrice=" + unitePrice +
                ", discount=" + discount +
                ", cost=" + cost +
                '}';
    }
}
