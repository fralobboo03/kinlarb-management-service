package com.kinlarb.kinlarb_management_service.service;

import com.kinlarb.kinlarb_management_service.dto.RecipeIngredientDTO;
import com.kinlarb.kinlarb_management_service.dto.RecipeRequestDTO;
import com.kinlarb.kinlarb_management_service.entity.RecipeEntity;
import com.kinlarb.kinlarb_management_service.entity.RecipeIngredientEntity;
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
    public RecipeEntity saveRecipe(RecipeRequestDTO requestDTO) {
        RecipeEntity recipe = new RecipeEntity();
        recipe.setName(requestDTO.getName());
        recipe.setDescription(requestDTO.getDescription());
        recipe.setTargetMargin(requestDTO.getTargetMargin());
        recipe.setSellingPrice(requestDTO.getSellingPrice());

        double totalRecipeCost = 0.0;

        if (requestDTO.getIngredients() != null) {
            for (RecipeIngredientDTO ingredientDTO : requestDTO.getIngredients()) {
                RecipeIngredientEntity ingredient = new RecipeIngredientEntity();
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
    public List<RecipeEntity> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @Transactional
    public RecipeEntity updateRecipe(Long id, RecipeRequestDTO requestDTO) {
        RecipeEntity recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RecipeEntity not found"));
        
        recipe.setName(requestDTO.getName());
        recipe.setDescription(requestDTO.getDescription());
        recipe.setTargetMargin(requestDTO.getTargetMargin());
        recipe.setSellingPrice(requestDTO.getSellingPrice());

        // Clear existing ingredients and recreate
        recipe.getIngredients().clear();
        double totalRecipeCost = 0.0;

        if (requestDTO.getIngredients() != null) {
            for (RecipeIngredientDTO ingredientDTO : requestDTO.getIngredients()) {
                RecipeIngredientEntity ingredient = new RecipeIngredientEntity();
                ingredient.setIngredientName(ingredientDTO.getIngredientName());
                ingredient.setQuantity(ingredientDTO.getQuantity());
                ingredient.setUnit(ingredientDTO.getUnit());
                ingredient.setUnitCost(ingredientDTO.getUnitCost());

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

    @Transactional
    public void deleteRecipe(Long id) {
        if (!recipeRepository.existsById(id)) {
            throw new RuntimeException("RecipeEntity not found");
        }
        recipeRepository.deleteById(id);
    }
}
