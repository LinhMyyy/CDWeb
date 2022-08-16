package com.cdweb.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category", uniqueConstraints = { //
        @UniqueConstraint(name = "CATEGORY_UK", columnNames = "code")})
public class CategoryEntity  {

    //column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String code;
    @Column
    private String name;

    @OneToMany(mappedBy = "category")
    List<ProductEntity> bookList=new ArrayList<>();
    //getter,setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
