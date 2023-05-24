package view.tm;

import java.util.Objects;

public class payTM {
    private String itemid;
    private String orderid;
    private String customerid;
    private String date;
    private String time;
    private int qty;
    private double price;
    private double cost;
    private String paymethod;

    public payTM(String itemid, String orderid, String customerid, String date, String time, int qty, double price, double cost, String paymethod) {
        this.itemid = itemid;
        this.orderid = orderid;
        this.customerid = customerid;
        this.date = date;
        this.time = time;
        this.qty = qty;
        this.price = price;
        this.cost = cost;
        this.paymethod = paymethod;
    }

    public payTM() {
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
        return "payTM{" +
                "itemid='" + itemid + '\'' +
                ", orderid='" + orderid + '\'' +
                ", customerid='" + customerid + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", cost=" + cost +
                ", paymethod='" + paymethod + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        payTM payTM = (payTM) o;
        return Objects.equals(itemid, payTM.itemid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemid);
    }
}
