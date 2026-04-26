package com.kinlarb.kinlarb_management_service.service;

import com.kinlarb.kinlarb_management_service.dto.RecipeIngredientDTO;
import com.kinlarb.kinlarb_management_service.dto.RecipeRequestDTO;
import com.kinlarb.kinlarb_management_service.entity.Recipe;
import com.kinlarb.kinlarb_management_service.entity.RecipeIngredient;
import com.kinlarb.kinlarb_management_service.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeService {

    private final RecipeRepository recipeRepository;

    @Transactional
    public Recipe saveRecipe(RecipeRequestDTO requestDTO) {
        Recipe recipe = new Recipe();
        recipe.setName(requestDTO.getName());
        recipe.setDescription(requestDTO.getDescription());
        recipe.setTargetMargin(requestDTO.getTargetMargin());
        recipe.setSellingPrice(requestDTO.getSellingPrice());

        double totalRecipeCost = 0.0;

        if (requestDTO.getIngredients() != null) {
            for (RecipeIngredientDTO ingredientDTO : requestDTO.getIngredients()) {
                RecipeIngredient ingredient = new RecipeIngredient();
                ingredient.setIngredientName(ingredientDTO.getIngredientName());
                ingredient.setQuantity(ingredientDTO.getQuantity());
                ingredient.setUnit(ingredientDTO.getUnit());
                ingredient.setUnitCost(ingredientDTO.getUnitCost());

                // Calculate subtotal
                double qty = ingredientDTO.getQuantity() != null ? ingredientDTO.getQuantity() : 0.0;
                double cost = ingredientDTO.getUnitCost() != null ? ingredientDTO.getUnitCost() : 0.0;
                double subTotal = qty * cost;
                ingredient.setSubTotal(subTotal);

                totalRecipeCost += subTotal;

                recipe.addIngredient(ingredient);
            }
        }

        recipe.setTotalCost(totalRecipeCost);

        return recipeRepository.save(recipe);
    }

    @Transactional(readOnly = true)
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }
}
