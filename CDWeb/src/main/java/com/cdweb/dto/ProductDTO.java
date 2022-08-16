package com.cdweb.dto;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ProductDTO {
    private Long id;
    private boolean active;
    private String title;
    private String description;
    private double discount;
    private Long price;
    private Long quantity;
    private boolean newProduct;
    private boolean hotProduct;
    private String publisher;
    private CategoryDTO category;
    private List<MediaDTO> mediaList = new ArrayList<>();


    public ProductDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public List<MediaDTO> getMediaList() {
        return mediaList;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setMediaList(List<MediaDTO> mediaList) {
        this.mediaList = mediaList;
    }

    public String getPriceFormat() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(this.price) + " VND";
    }

    public String getPriceDiscount() {
        double price = this.price * (1 - this.discount / 100);
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price) + " VND";
    }

    public String getDiscountFormat() {
        DecimalFormat formatter = new DecimalFormat("##.##");
        return formatter.format(discount) + "%";
    }

    public static String formatPrice(double price) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(price) + " VND";
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
