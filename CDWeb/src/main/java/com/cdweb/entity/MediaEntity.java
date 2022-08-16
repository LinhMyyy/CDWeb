package com.cdweb.entity;

import javax.persistence.*;

@Entity
@Table(name = "media")
public class MediaEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String path;

    @ManyToOne(fetch = FetchType.LAZY)
    private ProductEntity product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProductEntity getBook() {
        return product;
    }

    public void setBook(ProductEntity book) {
        this.product = book;
    }
}
