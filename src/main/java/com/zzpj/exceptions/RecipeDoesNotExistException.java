package com.zzpj.exceptions;

public class RecipeDoesNotExistException extends Exception {
    public RecipeDoesNotExistException(String message) {
        super(message);
    }
}
