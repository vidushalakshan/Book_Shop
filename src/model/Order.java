package model;

import java.util.ArrayList;

public class Order {
    private String orderid;
    private String customerid;
    private String orderdate;
    private String ordertime;
    private double cost;
    private  String paymethod;
    private ArrayList<OrderDetails> items;

    public Order(String orderid, String customerid, String orderdate, String ordertime, double cost, String paymethod, ArrayList<OrderDetails> items) {
        this.orderid = orderid;
        this.customerid = customerid;
        this.orderdate = orderdate;
        this.ordertime = ordertime;
        this.cost = cost;
        this.paymethod = paymethod;
        this.items = items;
    }

    public Order() {
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

    public ArrayList<OrderDetails> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderDetails> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid='" + orderid + '\'' +
                ", customerid='" + customerid + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", cost=" + cost +
                ", paymethod='" + paymethod + '\'' +
                ", items=" + items +
                '}';
    }
}
