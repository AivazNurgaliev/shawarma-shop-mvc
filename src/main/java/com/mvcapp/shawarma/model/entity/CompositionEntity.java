package com.mvcapp.shawarma.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "composition")
public class CompositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name =  "ingredient_id")
    private Integer ingredientId;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "ingredient_id", referencedColumnName = "id", insertable = false, updatable = false)
    private IngredientEntity ingredient;
}
