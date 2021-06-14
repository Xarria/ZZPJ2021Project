import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';
import { RecipeGeneral } from '../model/RecipeGeneral';

@Component({
  selector: 'app-user-panel',
  templateUrl: './user-panel.component.html',
  styleUrls: ['./user-panel.component.less']
})

export class UserPanelComponent implements OnInit {

  unwantedTags: string | undefined;

  constructor(private router: Router,
              public userService: UserService) {
  }

  ngOnInit(): void {
    this.getRecipes();
  }

  getRecipes(): void {
    this.userService.getRecipes().subscribe(
      (response: RecipeGeneral[]) => {
        this.userService.recipes = response;
      }
    );
  }

  openRecipeDetails(id: number): void {
    this.router.navigate(['/recipe', id]);
  }

  signOut(): void {
    this.router.navigate(['/']);
  }

  getMyRecipes(): void {
    this.userService.getMyRecipes().subscribe(
      (response: RecipeGeneral[]) => {
        this.userService.recipes = response;
      }
    )
  }

  getMyFavouriteRecipes(): void {
    this.userService.getMyFavouriteRecipes().subscribe(
      (response: RecipeGeneral[]) => {
        this.userService.recipes = response;
      }
    )
  }

  getRecommendation(): void {
    if(this.unwantedTags === undefined){
      this.userService.getRecommendation([]).subscribe(
        (response: RecipeGeneral[]) => {
          this.userService.recipes = response;
        }
      )
    }
    const tagArray = this.unwantedTags.split(',');
    if(tagArray[0].length > 1){
      this.userService.getRecommendation(tagArray).subscribe(
        (response: RecipeGeneral[]) => {
          this.userService.recipes = response;
        }
      )
    }
    if(tagArray[0].length == 0){
      this.userService.getRecommendation([]).subscribe(
        (response: RecipeGeneral[]) => {
          this.userService.recipes = response;
        }
      )
    }
  }
}



