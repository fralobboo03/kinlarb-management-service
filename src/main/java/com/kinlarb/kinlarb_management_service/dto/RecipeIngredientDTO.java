package com.kinlarb.kinlarb_management_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecipeIngredientDTO {

    @NotBlank(message = "Ingredient name is required")
    private String name;

    @Min(0)
    private Double quantityUsed;

    private String unit;

    @Min(0)
    private Double costPerUnit;
    
    @Min(0)
    private Double totalCost;
}
