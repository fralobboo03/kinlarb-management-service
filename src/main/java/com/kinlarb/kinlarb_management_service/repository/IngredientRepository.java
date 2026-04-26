package com.kinlarb.kinlarb_management_service.repository;

import com.kinlarb.kinlarb_management_service.entity.RecipeIngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<RecipeIngredientEntity, Long> {
}
