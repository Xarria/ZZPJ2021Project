import { Ingredient } from './Ingredient';

export interface RecipeDetails {
  id: number;
  name: string;
  authorLogin: string;
  description: string;
  ingredients: Ingredient[];
  rating: number;
  ratingsCount: number;
  tags: string;
  image: object;
  servings: number;
  calories: number;
  preparationTimeInMinutes: number;
  difficulty: number;
}
