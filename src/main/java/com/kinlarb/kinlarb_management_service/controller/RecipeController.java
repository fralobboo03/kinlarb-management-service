package com.kinlarb.kinlarb_management_service.controller;

import com.kinlarb.kinlarb_management_service.dto.RecipeRequestDTO;
import com.kinlarb.kinlarb_management_service.entity.Recipe;
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

    @PostMapping
    public ResponseEntity<Recipe> createRecipe(@Valid @RequestBody RecipeRequestDTO requestDTO) {
        Recipe savedRecipe = recipeService.saveRecipe(requestDTO);
        return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        List<Recipe> recipes = recipeService.getAllRecipes();
        return ResponseEntity.ok(recipes);
    }
}
