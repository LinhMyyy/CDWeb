package com.cdweb.dto;

public class MediaDTO {
    private Long id;
    private String path;
    private ProductDTO book;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public void setBook(ProductDTO book) {
        this.book = book;
    }
}
