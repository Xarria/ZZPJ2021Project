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
}



