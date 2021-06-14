describe('Recipe REST API Tests', () => {

    let jwt
    let lastRecipeId
    const userCredentials = {
        "username": "user1",
        "password": "password"
    }
    const unwantedTags = ["meat"];
    const recipeInitialRating = 5
    const recipeInitialCount = 1
    const newRecipeRating = 1.0
    const recipeId = 1;
    const recipeDetails = {
        "name": "Spaghetti carbonara",
        "authorLogin": "user1",
        "description": "pychotka",
        "ingredients": [
            {
                "name": "flour",
                "quantity": 500.0,
                "calories": 2.0,
                "protein": 3.0,
                "carbohydrates": 4.0,
                "fats": 5.0
            },
            {
                "name": "water",
                "quantity": 200.0,
                "calories": 2.0,
                "protein": 3.0,
                "carbohydrates": 4.0,
                "fats": 5.0
            },
            {
                "name": "sugar",
                "quantity": 220.0,
                "calories": 2.0,
                "protein": 3.0,
                "carbohydrates": 4.0,
                "fats": 5.0
            },
            {
                "name": "salt",
                "quantity": 2.0,
                "calories": 2.0,
                "protein": 3.0,
                "carbohydrates": 4.0,
                "fats": 5.0
            }
        ],
        "rating": 5.0,
        "ratingsCount": 2,
        "tags": "meat,noodle,pasta,savoury,gluten",
        "image": null,
        "servings": 4,
        "calories": 1000,
        "preparationTimeInMinutes": 50,
        "difficulty": "2"
    }
    const newRecipe = {
        "name": "Spaghetti aglio e olio",
        "authorLogin": "user1",
        "description": "Szybki i prosty przepis na pyszne spaghetti",
        "ingredients": [
            {
                "name": "makaron spaghetti",
                "quantity": 500,
                "calories": 785,
                "protein": 30,
                "carbohydrates": 155,
                "fats": 4.5
            },
            {
                "name": "ząbki czosnku",
                "quantity": 6,
                "calories": 36,
                "protein": 2,
                "carbohydrates": 2,
                "fats": 0.2
            },
            {
                "name": "ząbki czosnku",
                "quantity": 100,
                "calories": 884,
                "protein": 0,
                "carbohydrates": 0,
                "fats": 100
            },
            {
                "name": "natka pietruszki",
                "quantity": 20,
                "calories": 7,
                "protein": 0.6,
                "carbohydrates": 1.2,
                "fats": 0.16
            }
        ],
        "rating": recipeInitialRating,
        "ratingsCount": recipeInitialCount,
        "tags": "pasta,garlic,olive oil",
        "image": null,
        "servings": 4,
        "calories": 4812,
        "preparationTimeInMinutes": 20,
        "difficulty": "2"
    }
    const favouriteRecipes = [
        {
            "id": 3,
            "name": "Crepes",
            "authorLogin": "user1",
            "description": "pychotka",
            "rating": 5.0,
            "ratingsCount": 4,
            "tags": "vegan,sweet,gluten,dairy",
            "image": null,
            "servings": 4,
            "calories": 400,
            "preparationTimeInMinutes": 50,
            "difficulty": "1"
        },
        {
            "id": 5,
            "name": "Spaghetti aglio olio",
            "authorLogin": "user2",
            "description": "pychotka",
            "rating": 5.0,
            "ratingsCount": 13,
            "tags": "vegan,noodle,pasta,savoury,gluten",
            "image": null,
            "servings": 4,
            "calories": 800,
            "preparationTimeInMinutes": 50,
            "difficulty": "2"
        },
        {
            "id": 7,
            "name": "Cherry soup",
            "authorLogin": "user2",
            "description": "pychotka",
            "rating": 5.0,
            "ratingsCount": 13,
            "tags": "vegan,soup,sweet,fruit",
            "image": null,
            "servings": 4,
            "calories": 800,
            "preparationTimeInMinutes": 50,
            "difficulty": "2"
        }
    ]
    const newIngredient = {
        "name": "Vodka",
        "quantity": 0.5
    }

    beforeEach('Authenticate', () => {
        cy.request({
            method: 'POST',
            url: '/auth',
            body: userCredentials
        }).then((response) => {
            jwt = response.body;
        })
    })

    it('Get all recipes', () => {
        cy.request({
            method: 'GET',
            url: '/recipes',
            headers: {
                'Authorization': 'Bearer ' + jwt
            }
        }).then((response) => {
            expect(response.status).equal(200)
            expect(response.body).to.be.an('array')
            lastRecipeId = response.body[response.body.length - 1].id
        })
    })

    it('Get recipe details', () => {
        cy.request({
            method: 'GET',
            url: '/recipes/' + recipeId,
            headers: {
                Authorization: 'Bearer ' + jwt
            }
        }).then((response) => {
            expect(response.status).equal(200)
            expect(response.body).to.deep.include(recipeDetails)
        })
    })

    it('Get recommendations with unwanted tags', () => {
        cy.request({
            method: 'GET',
            url: '/recipes/recommendation',
            body: unwantedTags,
            headers: {
                Authorization: 'Bearer ' + jwt
            }
        }).then((response) => {
            expect(response.body).to.be.an('array')
            response.body.forEach((recipe) => {
                expect(recipe.tags).to.not.contain('meat')
            })
        })
    });

    it('Create recipe, and get it', () => {
        cy.request({
            method: 'POST',
            url: '/recipes',
            body: newRecipe,
            headers: {
                Authorization: 'Bearer ' + jwt
            }
        }).then((response) => {
            expect(response.status).equal(200)

            cy.request({
                method: 'GET',
                url: '/recipes',
                headers: {
                    'Authorization': 'Bearer ' + jwt
                }
            }).then((response) => {
                lastRecipeId = response.body[response.body.length - 1].id

                cy.request({
                    method: 'GET',
                    url: '/recipes/' + lastRecipeId,
                    headers: {
                        Authorization: 'Bearer ' + jwt
                    }
                }).then((response) => {
                    expect(response.status).equal(200)
                    expect(response.body).to.deep.include(newRecipe)
                })
            })
        })
    })

    it('Get all recipes created by account', () => {
        cy.request({
            method: 'GET',
            url: '/recipes/account/' + userCredentials.username,
            headers: {
                Authorization: 'Bearer ' + jwt
            }
        }).then((response) => {
            expect(response.status).to.equal(200)
            expect(response.body).to.be.an('array')
            response.body.forEach((recipe) => {
                expect(recipe.authorLogin).to.eql(userCredentials.username)
            })
        })
    });

    it('Get favourite recipes for account', () => {
        cy.request({
            method: 'GET',
            url: '/recipes/favourite/' + userCredentials.username,
            headers: {
                Authorization: 'Bearer ' + jwt
            }
        }).then((response) => {
            expect(response.status).to.equal(200)
            expect(response.body).to.be.an('array')
            expect(response.body).to.eql(favouriteRecipes)
        })
    });

    it('Update recipe', () => {
        cy.request({
            method: 'GET',
            url: '/recipes',
            headers: {
                'Authorization': 'Bearer ' + jwt
            }
        }).then((response) => {
            lastRecipeId = response.body[response.body.length - 1].id

            cy.request({
                method: 'PUT',
                url: '/recipes/' + lastRecipeId,
                body: {
                    "description": "Changed text"
                },
                headers: {
                    Authorization: 'Bearer ' + jwt
                }
            }).then((response) => {
                expect(response.status).to.equal(200)

                cy.request({
                    method: 'GET',
                    url: '/recipes/' + lastRecipeId,
                    headers: {
                        Authorization: 'Bearer ' + jwt
                    }
                }).then((response) => {
                    expect(response.body.description).to.equal("Changed text")
                })
            })

        })
    });

    it('Add rating to recipe', () => {
        cy.request({
            method: 'GET',
            url: '/recipes',
            headers: {
                'Authorization': 'Bearer ' + jwt
            }
        }).then((response) => {
            lastRecipeId = response.body[response.body.length - 1].id

            cy.request({
                method: 'PUT',
                url: '/recipes/ratings/' + lastRecipeId,
                body: "1.0",
                headers: {
                    Authorization: 'Bearer ' + jwt,
                    'content-type': 'application/json'
                },
            }).then((response) => {
                expect(response.status).to.equal(200)

                cy.request({
                    method: 'GET',
                    url: '/recipes/' + lastRecipeId,
                    headers: {
                        Authorization: 'Bearer ' + jwt
                    }
                }).then((response) => {
                    expect(response.body.rating).to.eql(
                        (recipeInitialRating * recipeInitialCount + newRecipeRating) / (recipeInitialCount + 1)
                    )
                    expect(response.body.ratingsCount).to.eql(recipeInitialCount + 1)
                })
            })
        })
    });

    it('Add ingredient to recipe', () => {
        cy.request({
            method: 'GET',
            url: '/recipes/' + lastRecipeId,
            headers: {
                Authorization: 'Bearer ' + jwt
            }
        }).then((response) => {
            let ingredientsCount = response.body.ingredients.length;

            cy.request({
                method: 'POST',
                url: '/recipes/ingredients/' + lastRecipeId,
                body: newIngredient,
                headers: {
                    'Authorization': 'Bearer ' + jwt,
                    'content-type': 'application/json'
                }
            }).then((response) => {
                expect(response.status).to.equal(200)

                cy.request({
                    method: 'GET',
                    url: '/recipes/' + lastRecipeId,
                    headers: {
                        Authorization: 'Bearer ' + jwt
                    }
                }).then((response) => {
                    expect(response.body.ingredients.length).to.equal(ingredientsCount + 1)
                })
            })
        })
    })

    it('Remove ingredient from recipe', () => {
        cy.request({
            method: 'GET',
            url: '/recipes/' + lastRecipeId,
            headers: {
                Authorization: 'Bearer ' + jwt
            }
        }).then((response) => {
            let ingredientsCount = response.body.ingredients.length;

            cy.request({
                method: 'DELETE',
                url: '/recipes/ingredients/' + lastRecipeId,
                body: "Vodka",
                headers: {
                    Authorization: 'Bearer ' + jwt
                }
            }).then((response) => {
                expect(response.status).to.equal(200)

                cy.request({
                    method: 'GET',
                    url: '/recipes/' + lastRecipeId,
                    headers: {
                        Authorization: 'Bearer ' + jwt
                    }
                }).then((response) => {
                    expect(response.body.ingredients.length).to.equal(ingredientsCount - 1)
                })
            })
        })
    });

    it('Add recipe to favourites', () => {
        let initialFavouriteRecipesCount;
        cy.request({
            method: 'GET',
            url: '/recipes/favourite/' + userCredentials.username,
            headers: {
                Authorization: 'Bearer ' + jwt
            }
        }).then((response) => {
            initialFavouriteRecipesCount = response.body.length;

            cy.request({
                method: 'PUT',
                url: '/recipes/favourite/add/' + lastRecipeId,
                headers: {
                    'Authorization': 'Bearer ' + jwt,
                }
            }).then((response) => {
                expect(response.status).to.equal(200)
            })
        })
    });

    it('Delete previously created recipe', () => {
        cy.request({
            method: 'GET',
            url: '/recipes',
            headers: {
                'Authorization': 'Bearer ' + jwt
            }
        }).then((response) => {
            lastRecipeId = response.body[response.body.length - 1].id

            cy.request({
                method: 'DELETE',
                url: '/recipes/' + lastRecipeId,
                headers: {
                    'Authorization': 'Bearer ' + jwt,
                }
            }).then((response) => {
                expect(response.status).to.equal(200)

                cy.request({
                    method: 'GET',
                    url: '/recipes',
                    headers: {
                        'Authorization': 'Bearer ' + jwt
                    }
                }).then((response) => {
                    expect(response.body).to.not.deep.include(newRecipe)
                })
            })
        })
    });
})
