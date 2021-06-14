import { Component, OnInit } from '@angular/core';
import { RecipeDetails } from '../model/RecipeDetails';
import { RecipeService } from '../services/recipe.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-recipe-details',
  templateUrl: './recipe-details.component.html',
  styleUrls: ['./recipe-details.component.less']
})
export class RecipeDetailsComponent implements OnInit {

  id = 0;

  constructor(public recipeService: RecipeService,
              public router: Router,
              private route: ActivatedRoute,
              private userService: UserService) {
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

  signOut(): void {
    this.router.navigate(['/']);
    this.userService.signOut();
  }

  sendRecipe(): void {
    this.recipeService.sendRecipe(this.id).subscribe();
  }
}
