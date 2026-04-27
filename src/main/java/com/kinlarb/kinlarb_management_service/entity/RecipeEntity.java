package com.kinlarb.kinlarb_management_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recipes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "RecipeEntity name is required")
    private String name;

    @Column(name = "recipe_no")
    private String recipeNo;

    @Column(name = "date")
    private Date date;

    @Column(name = "category")
    private String category;

    @Column(name = "portions")
    private Integer portions;

    @Column(name = "prepared_by")
    private String preparedBy;

    private String description;

    @Min(0)
    @Column(name = "total_cost")
    private Double totalCost;

    @Min(0)
    @Column(name = "margin_percent")
    private Double marginPercent;

    @Min(0)
    @Column(name = "suggested_price")
    private Double suggestedPrice;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<RecipeIngredientEntity> ingredients = new ArrayList<>();

    public void addIngredient(RecipeIngredientEntity ingredient) {
        ingredients.add(ingredient);
        ingredient.setRecipe(this);
    }
}
