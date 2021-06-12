package com.zzpj.services;

import com.zzpj.repositories.IngredientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class IngredientServiceTest {

    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    private IngredientService ingredientService;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getIngredientsByKeyword() {
        //TODO
    }

    @Test
    void getRequest() {
        //TODO
    }

    @Test
    void replaceSpacesInKeyword() {
        //TODO
    }
}
