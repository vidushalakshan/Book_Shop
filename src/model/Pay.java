package model;

public class Pay {
    private String orderid;
    private String itemid;
    private String cusid;
    private String orderdate;
    private String ordertime;
    private int qty;
    private double price;
    private double cost;
    private String paymethod;

    public Pay(String orderid, String itemid, String cusid, String orderdate, String ordertime, int qty, double price, double cost, String paymethod) {
        this.orderid = orderid;
        this.itemid = itemid;
        this.cusid = cusid;
        this.orderdate = orderdate;
        this.ordertime = ordertime;
        this.qty = qty;
        this.price = price;
        this.cost = cost;
        this.paymethod = paymethod;
    }

    public Pay(String text, String setStatus) {
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getCusid() {
        return cusid;
    }

    public void setCusid(String cusid) {
        this.cusid = cusid;
    }

    public String getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(String orderdate) {
        this.orderdate = orderdate;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getPaymethod() {
        return paymethod;
    }

    public void setPaymethod(String paymethod) {
        this.paymethod = paymethod;
    }

    @Override
    public String toString() {
        return "Pay{" +
                "orderid='" + orderid + '\'' +
                ", itemid='" + itemid + '\'' +
                ", cusid='" + cusid + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", cost=" + cost +
                ", paymethod='" + paymethod + '\'' +
                '}';
    }

}
