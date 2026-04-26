package com.kinlarb.kinlarb_management_service.repository;

import com.kinlarb.kinlarb_management_service.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<RecipeIngredient, Long> {
}
