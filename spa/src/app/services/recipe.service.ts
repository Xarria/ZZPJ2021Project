import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { RecipeDetails } from '../model/RecipeDetails';
import { Observable } from 'rxjs';
import { Ingredient } from '../model/Ingredient';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  recipe: RecipeDetails = {
    id: 0,
    name: '',
    authorLogin: '',
    description: '',
    ingredients: [],
    rating: 0,
    ratingsCount: 0,
    tags: '',
    image: null,
    servings: 0,
    calories: 0,
    preparationTimeInMinutes: 0,
    difficulty: 0
  };

  private readonly url: string;

  constructor(private httpClient: HttpClient) {
    this.url = 'http://localhost:8080/recipes';
  }

  getRecipeDetails(id: number): Observable<RecipeDetails> {
    return this.httpClient.get<RecipeDetails>(this.url + '/' + encodeURIComponent(id), {
      observe: 'body',
      responseType: 'json',
      headers: {'Authorization': 'Bearer ' + localStorage.getItem('token')}
    });
  }

  sendRecipe(id: number): any {
    return this.httpClient.get<RecipeDetails>(this.url + '/save/' + encodeURIComponent(id), {
      observe: 'body',
      responseType: 'json',
      headers: {'Authorization': 'Bearer ' + localStorage.getItem('token')}
    });
  }
}
