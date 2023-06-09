package com.mvcapp.shawarma.model.entity;

import jakarta.persistence.*;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;
}
