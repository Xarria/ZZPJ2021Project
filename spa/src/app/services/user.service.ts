import { Injectable } from '@angular/core';
import { RecipeGeneral } from '../model/RecipeGeneral';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  recipes: RecipeGeneral[] = [];

  private readonly url: string;

  constructor(private httpClient: HttpClient) {
    this.url = 'http://localhost:8080/recipes';
  }

  getRecipes(): any {
    return this.httpClient.get<any>(this.url, {
      observe: 'body',
      responseType: 'json'
    });
  }
}


