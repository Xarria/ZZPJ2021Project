//package com.zzpj.controllers;
//
//import com.zzpj.exceptions.IngredientNotFoundException;
//import com.zzpj.exceptions.URLNotFoundException;
//import com.zzpj.model.DTOs.IngredientDTO;
//import com.zzpj.model.mappers.IngredientsMapper;
//import com.zzpj.services.interfaces.IngredientServiceInterface;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.IOException;
//
//@RestController
//public class IngredientController {
//
//    private final IngredientServiceInterface ingredientService;
//
//    @Autowired
//    public IngredientController(IngredientServiceInterface ingredientService) {
//        this.ingredientService = ingredientService;
//    }
//
//    @GetMapping(path = "/ingredients/{name}", consumes = "application/json")
//    public ResponseEntity<IngredientDTO> getIngredient(@PathVariable String name) {
//        try {
//            return ResponseEntity.ok(IngredientsMapper.entityToDTO(ingredientService.getIngredientsByKeyword(name)));
//        } catch (IOException | IngredientNotFoundException | URLNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//    }
//}
