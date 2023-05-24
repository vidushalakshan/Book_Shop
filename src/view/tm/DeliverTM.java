package view.tm;

import java.util.Objects;

public class DeliverTM {
    private String orderid;
    private String customerid;
    private String orderdate;
    private String ordertime;
    private double cost;

    public DeliverTM(String orderid, String customerid, String orderdate, String ordertime, double cost) {
        this.orderid = orderid;
        this.customerid = customerid;
        this.orderdate = orderdate;
        this.ordertime = ordertime;
        this.cost = cost;
    }

    public DeliverTM() {
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
        return "DeliverTM{" +
                "orderid='" + orderid + '\'' +
                ", customerid='" + customerid + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", cost=" + cost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliverTM deliverTM = (DeliverTM) o;
        return Objects.equals(orderid, deliverTM.orderid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderid);
    }
}
