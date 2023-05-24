package model;

public class Delivered {
    private String orderid;
    private String customerid;
    private String orderdate;
    private String ordertime;
    private double cost;

    public Delivered(String orderid, String customerid, String orderdate, String ordertime, double cost) {
        this.orderid = orderid;
        this.customerid = customerid;
        this.orderdate = orderdate;
        this.ordertime = ordertime;
        this.cost = cost;
    }

    public Delivered() {
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

    @Override
    public String toString() {
        return "Delivered{" +
                "orderid='" + orderid + '\'' +
                ", customerid='" + customerid + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", cost=" + cost +
                '}';
    }
}
