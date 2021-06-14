export interface RecipeGeneral {
  id: number;
  name: string;
  authorLogin: string;
  description: string;
  rating: number;
  ratingsCount: number;
  tags: string;
  image: object;
  servings: number;
  calories: number;
  preparationTimeInMinutes: number;
  difficulty: number;
}
