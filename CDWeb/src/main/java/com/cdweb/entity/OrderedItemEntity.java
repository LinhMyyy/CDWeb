package com.cdweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "ordered_item")
public class OrderedItemEntity {
    //column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Long quantity;
    @Column
    private double totalPrice;
    //map
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity book;

    @ManyToOne
    @JoinColumn(name = "ordered_id")
    private OrderedEntity ordered;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //getter,setter


    public void setOrdered(OrderedEntity ordered) {
        this.ordered = ordered;
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

    public ProductEntity getBook() {
        return book;
    }

    public void setBook(ProductEntity book) {
        this.book = book;
    }

}
