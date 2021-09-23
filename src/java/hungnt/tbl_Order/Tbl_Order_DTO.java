/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hungnt.tbl_Order;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author hungn
 */
public class Tbl_Order_DTO implements Serializable{
    private int orderId;
    private int total;
    private int paymentMethodId;
    private int paymentStatusId;
    private String email;
    private String customerName;
    private String address;
    private String phoneNumber;
    private Timestamp orderDate;

    public Tbl_Order_DTO(int total, String customerName, String address, String phoneNumber, Timestamp orderDate) {
        this.total = total;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
    }

    public Tbl_Order_DTO(int total, int paymentMethodId, int paymentStatusId, String customerName, String address, String phoneNumber, Timestamp orderDate) {
        this.total = total;
        this.paymentMethodId = paymentMethodId;
        this.paymentStatusId = paymentStatusId;
        this.customerName = customerName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
    }
    
    

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public int getPaymentStatusId() {
        return paymentStatusId;
    }

    public void setPaymentStatusId(int paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }
    
    
}
