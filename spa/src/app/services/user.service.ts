import { Injectable } from '@angular/core';
import { RecipeGeneral } from '../model/RecipeGeneral';
import { HttpClient } from '@angular/common/http';
import jwtDecode from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  recipes: RecipeGeneral[] = [];

  private readonly url: string;

  constructor(private httpClient: HttpClient) {
    this.url = 'http://localhost:8080/recipes';
  }

  auth(login: string, password: string): any {
    return this.httpClient.post('http://localhost:8080/auth', {
      username: login,
      password: password
    }, {observe: 'body', responseType: 'text', headers: {'Content-Type': 'application/json'} });
  }

  public setSession(token: string): void {
    localStorage.setItem('token', token);
    this.decodeTokenInfo(token);
  }

  decodeTokenInfo(token: string): void {
    const tokenInfo: any = jwtDecode(token);
    localStorage.setItem('login', tokenInfo.sub);
    localStorage.setItem('exp', tokenInfo.exp);
    localStorage.setItem('accessLevel', tokenInfo.auth);
  }

  signOut(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('login');
    localStorage.removeItem('exp');
    localStorage.removeItem('accessLevel');
  }

  getRecipes(): any {
    return this.httpClient.get<any>(this.url, {
      observe: 'body',
      responseType: 'json',
      headers: {'Authorization': 'Bearer ' + localStorage.getItem('token')}
    });
  }

  getRecommendation(): any {
    return this.httpClient.get<any>(this.url, {
      observe: 'body',
      responseType: 'json',
      headers: {'Authorization': 'Bearer ' + localStorage.getItem('token')}
    });
  }
}


