package com.cdweb.dto;

import java.text.DecimalFormat;

public class ShoppingCartDTO {
    private Long id;
    private long quantity;
    private UserDTO user;
    private ProductDTO book;

    public String getTotal() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(this.quantity * this.book.getPrice() * (1 - this.book.getDiscount() / 100)) + " VND";
    }
    public String getTotal(double p) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(p) + " VND";
    }
    public UserDTO getUser() {
        return user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public ProductDTO getBook() {
        return book;
    }

    public void setBook(ProductDTO book) {
        this.book = book;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
