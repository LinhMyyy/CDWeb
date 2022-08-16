package com.cdweb.dto;


import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class OrderedDTO {
    private Long id;
    private UserDTO user;
    private String status;
    private Timestamp orderedDate;
    private Timestamp receivedDate;
    private double totalPrice;
    private String name;
    private String phone;
    private String address;
    private List<OrderedItemDTO> orderedItemList = new ArrayList<>();

    public OrderedDTO() {

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Timestamp getOrderedDate() {
        return orderedDate;
    }

    public String getOrderedDateString() {
        return orderedDate.toString();
    }

    public void setOrderedDate(Timestamp orderedDate) {
        this.orderedDate = orderedDate;
    }

    public Timestamp getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Timestamp receivedDate) {
        this.receivedDate = receivedDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderedItemDTO> getOrderedItemList() {
        return orderedItemList;
    }

    public void setOrderedItemList(List<OrderedItemDTO> orderedItemList) {
        this.orderedItemList = orderedItemList;
    }

    public String getPriceFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(this.totalPrice) + " VND";
    }
}
