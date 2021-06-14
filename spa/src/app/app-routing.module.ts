import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UserPanelComponent } from './user-panel/user-panel.component';
import { RecipeDetailsComponent } from './recipe-details/recipe-details.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'user', component: UserPanelComponent },
  { path: 'recipe/:id', component: RecipeDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
