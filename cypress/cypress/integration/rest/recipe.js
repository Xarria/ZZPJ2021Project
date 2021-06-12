describe('Recipe REST API Tests', () => {

    const userCredentials = {
        "username": "user1",
        "password": "password"
    }
    let recipesArrayLength;

    let jwt;
    const recipeId = 1;
    const recipeGeneral1 = {
        "name": "Carbonara",
        "authorLogin": "user1",
        "rating": 5.0,
        "ratingsCount": 2,
        "calories": 1000,
        "preparationTimeInMinutes": 50,
        "difficulty": "2",
        "image": null
    }
    const recipeDetails = {
        "name": "Carbonara",
        "authorLogin": "user1",
        "description": "pychotka",
        "ingredients": [
            {
                "name": "a",
                "quantity": 1.0,
                "calories": 2,
                "protein": 3,
                "carbohydrates": 4,
                "fats": 5
            },
            {
                "name": "a",
                "quantity": 1.0,
                "calories": 2,
                "protein": 3,
                "carbohydrates": 4,
                "fats": 5
            },
            {
                "name": "c",
                "quantity": 1.0,
                "calories": 2,
                "protein": 3,
                "carbohydrates": 4,
                "fats": 5
            },
            {
                "name": "c",
                "quantity": 4.0,
                "calories": 2,
                "protein": 3,
                "carbohydrates": 4,
                "fats": 5
            }
        ],
        "rating": 5.0,
        "ratingsCount": 2,
        "tags": "meat,noodle",
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
        "rating": 5,
        "ratingsCount": 0,
        "tags": "pasta,garlic,olive oil",
        "image": null,
        "servings": 4,
        "calories": 755,
        "preparationTimeInMinutes": 20,
        "difficulty": "2"
    }


    beforeEach('Authenticate', () => {
        cy.request({
            method: 'POST',
            url: '/auth',
            body: userCredentials
        }).then((response) => {
            jwt = response.body;
            console.log(jwt)
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
            expect(response.body).deep.contains(recipeGeneral1)
            recipesArrayLength = response.body.length
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
            expect(response.body).deep.contains(recipeDetails)
        })
    })

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
                url: '/recipes/' + (recipesArrayLength + 1),
                headers: {
                    Authorization: 'Bearer ' + jwt
                }
            }).then((response) => {
                expect(response.status).equal(200)
                expect(response.body).to.deep.equal(newRecipe)
            })
        })
    })

})
