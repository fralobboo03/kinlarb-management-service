package com.kinlarb.kinlarb_management_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecipeIngredientDTO {

    @NotBlank(message = "Ingredient name is required")
    @JsonProperty("name")
    private String ingredientName;

    @Min(0)
    @JsonProperty("quantityUsed")
    private Double quantity;

    private String unit;

    @Min(0)
    @JsonProperty("costPerUnit")
    private Double unitCost;
    
    @Min(0)
    @JsonProperty("totalCost")
    private Double subTotal;
}
