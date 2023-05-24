package model;

public class OrderDetails {
    private String itemcode;
    private double uniteprice;
    private int   qty;

    public OrderDetails(String itemcode, double uniteprice, int qty) {
        this.itemcode = itemcode;
        this.uniteprice = uniteprice;
        this.qty = qty;
    }

    public OrderDetails() {
    }

    public String getItemcode() {
        return itemcode;
    }

    public void setItemcode(String itemcode) {
        this.itemcode = itemcode;
    }

    public double getUniteprice() {
        return uniteprice;
    }

    public void setUniteprice(double uniteprice) {
        this.uniteprice = uniteprice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "itemcode='" + itemcode + '\'' +
                ", uniteprice=" + uniteprice +
                ", qty=" + qty +
                '}';
    }
}
