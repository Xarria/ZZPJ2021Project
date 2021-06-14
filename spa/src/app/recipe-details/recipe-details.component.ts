import { Component, OnInit } from '@angular/core';
import { RecipeDetails } from '../model/RecipeDetails';
import { RecipeService } from '../services/recipe.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.less']
})
export class RecipeDetailsComponent implements OnInit {

  id = 0;

  constructor(public recipeService: RecipeService,
              public router: Router,
              private route: ActivatedRoute) {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.getRecipeDetails();
  }

  ngOnInit(): void {
  }

  getRecipeDetails(): void {
    this.recipeService.getRecipeDetails(this.id).subscribe(
      (response: RecipeDetails) => {
        this.recipeService.recipe = response;
      });
  }

  sendRecipe(): void {
    this.recipeService.sendRecipe(this.id).subscribe();
  }
}
