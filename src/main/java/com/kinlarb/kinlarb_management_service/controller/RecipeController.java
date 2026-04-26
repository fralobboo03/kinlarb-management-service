package com.kinlarb.kinlarb_management_service.controller;

import com.kinlarb.kinlarb_management_service.dto.RecipeRequestDTO;
import com.kinlarb.kinlarb_management_service.entity.RecipeEntity;
import com.kinlarb.kinlarb_management_service.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RecipeController {

    private final RecipeService recipeService;

    @PostMapping("/create-recipes")
    public ResponseEntity<RecipeEntity> createRecipe(@Valid @RequestBody RecipeRequestDTO requestDTO) {
        RecipeEntity savedRecipe = recipeService.saveRecipe(requestDTO);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-recipes")
    public ResponseEntity<List<RecipeEntity>> getAllRecipes() {
        List<RecipeEntity> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }

    @PostMapping("/update-recipes/{id}")
    public ResponseEntity<RecipeEntity> updateRecipe(@PathVariable Long id, @Valid @RequestBody RecipeRequestDTO requestDTO) {
        RecipeEntity updatedRecipe = recipeService.updateRecipe(id, requestDTO);
        return ResponseEntity.ok(updatedRecipe);
    }

    @DeleteMapping("/delete-recipes-byid/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }
}
