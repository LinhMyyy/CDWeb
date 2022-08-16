package com.cdweb.dto;

import java.text.DecimalFormat;

public class OrderedItemDTO {
    private Long id;
    private OrderedDTO ordered;
    private ProductDTO book;
    private Long quantity;
    private double totalPrice;

    public OrderedItemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderedDTO getOrdered() {
        return ordered;
    }

    public void setOrdered(OrderedDTO ordered) {
        this.ordered = ordered;
    }

    public ProductDTO getBook() {
        return book;
    }

    public void setBook(ProductDTO book) {
        this.book = book;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPriceFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(this.totalPrice) + " VND";
    }
}
