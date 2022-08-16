package com.cdweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
public class ProductEntity {
    //column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private boolean active;

    @Column(columnDefinition = "TEXT NOT NULL" )
    private String description;
    @Column(columnDefinition = "float default 0")
    private double discount;
    @Column
    private Long price;
    @Column
    private Long quantity;
    @Column
    private String publisher;
    @Column(columnDefinition = "bit default 0")
    private boolean newProduct;
    @Column(columnDefinition = "bit default 0")
    private boolean hotProduct;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "product")
    private List<MediaEntity> mediaList = new ArrayList<>();

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<MediaEntity> getMediaList() {
        return mediaList;
    }

    public void setMediaList(List<MediaEntity> mediaList) {
        this.mediaList = mediaList;
    }

    public boolean isNewProduct() {
        return newProduct;
    }

    public void setNewProduct(boolean newProduct) {
        this.newProduct = newProduct;
    }

    public boolean isHotProduct() {
        return hotProduct;
    }

    public void setHotProduct(boolean hotProduct) {
        this.hotProduct = hotProduct;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
