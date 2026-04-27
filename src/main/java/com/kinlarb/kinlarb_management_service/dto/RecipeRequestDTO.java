package com.kinlarb.kinlarb_management_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class RecipeRequestDTO {

    @NotBlank(message = "RecipeEntity name is required")
    private String name;

    private String description;

    private String recipeNo;

    private Date date;

    private String category;

    private Integer portions;

    private String preparedBy;    

    @Min(0)
    private Double marginPercent;

    @Min(0)
    private Double suggestedPrice;
    
    @Min(0)
    private Double totalCost;

    @NotEmpty(message = "At least one ingredient is required")
    @Valid
    private List<RecipeIngredientDTO> ingredients;
}
