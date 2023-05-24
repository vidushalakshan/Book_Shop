package model;

public class payDeliver {
    private String orderid;
    private String cusid;
    private String orderdate;
    private String ordertime;
    private double cost;
    private String status;

    public payDeliver(String orderid, String cusid, String orderdate, String ordertime, double cost, String status) {
        this.orderid = orderid;
        this.cusid = cusid;
        this.orderdate = orderdate;
        this.ordertime = ordertime;
        this.cost = cost;
        this.status = status;
    }

    public payDeliver() {
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
        return "payDeliver{" +
                "orderid='" + orderid + '\'' +
                ", cusid='" + cusid + '\'' +
                ", orderdate='" + orderdate + '\'' +
                ", ordertime='" + ordertime + '\'' +
                ", cost=" + cost +
                ", status='" + status + '\'' +
                '}';
    }
}
