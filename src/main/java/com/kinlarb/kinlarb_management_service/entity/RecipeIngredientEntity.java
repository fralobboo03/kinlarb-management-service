package com.kinlarb.kinlarb_management_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "recipe_ingredients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeIngredientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private RecipeEntity recipe;

    @NotBlank(message = "Ingredient name is required")
    @Column(name = "name")
    private String name;

    @Min(0)
    @Column(name = "quantity_used")
    private Double quantityUsed;

    @Column(name = "unit")
    private String unit;

    @Min(0)
    @Column(name = "cost_per_unit")
    private Double costPerUnit;

    @Min(0)
    @Column(name = "total_cost")
    private Double totalCost;
}
