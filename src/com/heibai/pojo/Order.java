package com.heibai.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author heibai
 * @date 2020/7/1 21:30
 */
public class Order {
    private String orderId;
    private Date createTime;
    private BigDecimal price;
    private Integer status=0;
    private Integer userId;

    public Order() {
    }

    public Order(String orderId, Date creatTime, BigDecimal price, Integer status, Integer userId) {
        this.orderId = orderId;
        this.createTime = creatTime;
        this.price = price;
        this.status = status;
        this.userId = userId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date creatTime) {
        this.createTime = creatTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", userId=" + userId +
                '}';
    }
}
