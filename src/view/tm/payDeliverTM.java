package view.tm;

import java.util.Objects;

public class payDeliverTM {
    private String orderid;
    private String cusid;
    private String orderdate;
    private String ordertime;
    private double cost;
    private String status;

    public payDeliverTM(String orderid, String cusid, String orderdate, String ordertime, double cost, String status) {
        this.orderid = orderid;
        this.cusid = cusid;
        this.orderdate = orderdate;
        this.ordertime = ordertime;
        this.cost = cost;
        this.status = status;
    }

    public payDeliverTM() {
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "payDeliverTM{" +
                "orderid='" + orderid + '\'' +
                ", cusid='" + cusid + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", cost=" + cost +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        payDeliverTM that = (payDeliverTM) o;
        return Objects.equals(orderid, that.orderid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderid);
    }
}
