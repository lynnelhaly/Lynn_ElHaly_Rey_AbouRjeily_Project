package com.example.cookup.data.local

import com.example.cookup.data.model.IngredientEntity
import com.example.cookup.data.model.RecipeEntity
import com.example.cookup.data.model.RecipeIngredientCrossRef
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

object DatabaseSeeder {

    fun seed(database: CookUpDatabase) = runBlocking(Dispatchers.IO) {
        try {
            println("🔥🔥🔥 SEEDER STARTED 🔥🔥🔥")

            val dao = database.recipeDao()

            val existingCount = dao.getAllIngredients().size
            println("📌 Ingredient count BEFORE seeding = $existingCount")

            if (existingCount > 0) {
                println("⛔ Seeder stopped — ingredients already exist.")
                return@runBlocking
            }

            // 1. Ingredients
            // 1. Ingredients
            val ingredientNames = listOf(
                // Dairy & Eggs
                "Eggs", "Milk", "Butter", "Cheese", "Cream", "Yogurt", "Mozzarella",
                "Cheddar", "Parmesan", "Ricotta", "Feta", "Cream Cheese",

                // Meat & Poultry
                "Chicken Breast", "Chicken Thighs", "Ground Beef", "Beef Steak",
                "Pork Chops", "Bacon", "Ham", "Sausage", "Turkey",

                // Seafood
                "Salmon", "Shrimp", "Tuna", "Cod", "Sardines", "Crab", "Mussels",

                // Vegetables
                "Tomato", "Onion", "Garlic", "Potato", "Carrot", "Bell Pepper",
                "Cucumber", "Broccoli", "Cauliflower", "Spinach", "Lettuce",
                "Zucchini", "Mushrooms", "Green Beans", "Peas", "Corn", "Kale",
                "Celery", "Ginger", "Chili Pepper", "Sweet Potato",

                // Fruits
                "Lemon", "Apple", "Banana", "Orange", "Strawberries", "Blueberries",
                "Pineapple", "Mango", "Avocado",

                // Grains & Starches
                "Rice", "Pasta", "Bread", "Flour", "Oats", "Couscous",
                "Quinoa", "Noodles", "Tortilla", "Bread Crumbs",

                // Canned Goods
                "Tomato Sauce", "Tomato Paste", "Canned Beans", "Canned Corn",
                "Coconut Milk", "Chickpeas", "Black Beans", "Kidney Beans",
                "Canned Tuna",

                // Herbs & Spices
                "Salt", "Pepper", "Paprika", "Cumin", "Oregano", "Basil",
                "Thyme", "Rosemary", "Turmeric", "Cinnamon", "Nutmeg",
                "Curry Powder", "Chili Powder", "Parsley", "Bay Leaves",

                // Oils & Condiments
                "Olive Oil", "Vegetable Oil", "Vinegar", "Soy Sauce",
                "Worcestershire Sauce", "Honey", "Sugar", "Brown Sugar",

                // Baking
                "Baking Powder", "Baking Soda", "Yeast", "Cornstarch",
                "Cocoa Powder", "Chocolate Chips",

                // Legumes
                "Lentils", "Split Peas", "White Beans",

                // Extra Vegetables
                "Eggplant", "Artichoke", "Asparagus", "Pumpkin", "Leek",
                "Green Onion", "Arugula", "Beetroot",

                // More Dairy
                "Sour Cream", "Ghee", "Buttermilk", "Evaporated Milk",

                // More Meat & Poultry
                "Lamb Chops", "Minced Lamb", "Turkey Mince", "Chicken Wings",
                "Beef Mince", "Chicken Drumsticks",

                // More Seafood
                "Tilapia", "Sea Bass", "Calamari", "Clams",

                // More Vegetables & Peppers
                "Red Cabbage", "White Cabbage", "Radish", "Turnip", "Parsnip",
                "Brussel Sprouts", "Okra", "Jalapeño", "Serrano Pepper", "Habanero",
                "Spring Onion", "Shallots", "Rocket", "Red Onion",

                // More Fruits
                "Grapes", "Peach", "Pear", "Kiwi", "Watermelon",
                "Dates", "Figs", "Pomegranate",

                // More Grains & Starches
                "Barley", "Bulgur", "Semolina", "Whole Wheat Flour",
                "Lasagna Sheets", "Pita Bread", "Rice Noodles",

                // Nuts & Seeds
                "Almonds", "Walnuts", "Peanuts", "Cashews", "Hazelnuts",
                "Pistachios", "Sunflower Seeds", "Pumpkin Seeds",
                "Sesame Seeds", "Chia Seeds", "Flaxseeds",

                // More Herbs & Spices
                "Coriander", "Mint", "Dill", "Sage",
                "Tarragon", "Chives", "Marjoram", "Fenugreek",

                // Sauces & Condiments
                "Ketchup", "Dijon Mustard", "Mayonnaise",
                "Harissa", "Sriracha", "Fish Sauce", "Oyster Sauce", "Hoisin Sauce",
                "Tahini", "Pesto", "Balsamic Vinegar", "Mirin",

                // Stocks & Broths
                "Chicken Stock", "Beef Stock", "Vegetable Stock",

                // Baking & Sweet Extras
                "Powdered Sugar", "Vanilla Extract", "Gelatin", "Maple Syrup",
                "Peanut Butter", "Strawberry Jam"
            )



            val ingredientIds = mutableListOf<Int>()

            for (name in ingredientNames) {
                val id = dao.insertIngredient(IngredientEntity(name = name)).toInt()
                ingredientIds.add(id)
            }

            println("🥚 Ingredients inserted: ${ingredientIds.size}")

            // 2. Recipes
            val recipes = listOf(
                RecipeEntity(
                    title = "Classic Pancakes",
                    cookingTimeMinutes = 15,
                    instructions = "Mix flour, eggs, milk, sugar, and baking powder. Cook on a hot buttered pan until golden.",
                    category = "Breakfast",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Chicken Alfredo Pasta",
                    cookingTimeMinutes = 25,
                    instructions = "Cook pasta. Sauté chicken, add cream and Parmesan. Combine and serve.",
                    category = "Dinner",
                    dietaryTags = "High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Garlic Butter Shrimp",
                    cookingTimeMinutes = 12,
                    instructions = "Cook shrimp in butter, garlic, and lemon. Serve with rice or pasta.",
                    category = "Dinner",
                    dietaryTags = "Seafood",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Vegetable Stir-Fry",
                    cookingTimeMinutes = 18,
                    instructions = "Stir-fry mixed vegetables with soy sauce, garlic, and ginger.",
                    category = "Lunch",
                    dietaryTags = "Vegan",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Beef Tacos",
                    cookingTimeMinutes = 20,
                    instructions = "Cook ground beef with spices. Serve in tortillas with cheese and lettuce.",
                    category = "Dinner",
                    dietaryTags = "Mexican",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Avocado Toast",
                    cookingTimeMinutes = 5,
                    instructions = "Mash avocado with lemon, salt, and pepper. Spread on toasted bread.",
                    category = "Breakfast",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Tomato Basil Soup",
                    cookingTimeMinutes = 30,
                    instructions = "Cook tomatoes with garlic, onions, and basil. Blend until smooth.",
                    category = "Lunch",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Chicken Caesar Salad",
                    cookingTimeMinutes = 15,
                    instructions = "Mix lettuce, grilled chicken, Parmesan, croutons, and Caesar dressing.",
                    category = "Lunch",
                    dietaryTags = "High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Lemon Herb Salmon",
                    cookingTimeMinutes = 22,
                    instructions = "Bake salmon with lemon, garlic, olive oil, and herbs.",
                    category = "Dinner",
                    dietaryTags = "Seafood, Healthy",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Garlic Mashed Potatoes",
                    cookingTimeMinutes = 25,
                    instructions = "Boil potatoes, mash with butter, garlic, cream, salt, and pepper.",
                    category = "Side",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Greek Salad",
                    cookingTimeMinutes = 10,
                    instructions = "Mix cucumbers, tomatoes, feta cheese, olives, and olive oil.",
                    category = "Lunch",
                    dietaryTags = "Mediterranean, Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Chocolate Chip Cookies",
                    cookingTimeMinutes = 15,
                    instructions = "Mix flour, butter, sugar, eggs, and chocolate chips. Bake until golden.",
                    category = "Dessert",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Baked Chicken Drumsticks",
                    cookingTimeMinutes = 40,
                    instructions = "Season drumsticks and bake until crispy.",
                    category = "Dinner",
                    dietaryTags = "High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Mushroom Risotto",
                    cookingTimeMinutes = 30,
                    instructions = "Cook rice with stock, butter, onions, and mushrooms.",
                    category = "Dinner",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Spicy Chili Beans",
                    cookingTimeMinutes = 35,
                    instructions = "Cook beans with tomatoes, chili powder, onions, and garlic.",
                    category = "Dinner",
                    dietaryTags = "Vegan",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Tuna Sandwich",
                    cookingTimeMinutes = 7,
                    instructions = "Mix tuna with mayonnaise. Add lettuce and serve in bread.",
                    category = "Lunch",
                    dietaryTags = "High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Banana Smoothie",
                    cookingTimeMinutes = 5,
                    instructions = "Blend banana, yogurt, honey, and milk.",
                    category = "Breakfast",
                    dietaryTags = "Vegetarian, Healthy",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Vegetable Omelette",
                    cookingTimeMinutes = 10,
                    instructions = "Whisk eggs and cook with tomatoes, onions, mushrooms, and cheese.",
                    category = "Breakfast",
                    dietaryTags = "High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Sweet Potato Fries",
                    cookingTimeMinutes = 20,
                    instructions = "Cut sweet potatoes, season, and bake until crispy.",
                    category = "Side",
                    dietaryTags = "Vegan",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Garlic Bread",
                    cookingTimeMinutes = 12,
                    instructions = "Spread garlic butter on bread and bake.",
                    category = "Side",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Spaghetti Bolognese",
                    cookingTimeMinutes = 35,
                    instructions = "Cook pasta. Prepare meat sauce with beef, tomato sauce, onion, and garlic.",
                    category = "Dinner",
                    dietaryTags = "High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Grilled Cheese Sandwich",
                    cookingTimeMinutes = 8,
                    instructions = "Butter bread, add cheese, grill until golden.",
                    category = "Lunch",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Chicken Curry",
                    cookingTimeMinutes = 30,
                    instructions = "Cook chicken with curry powder, coconut milk, onion, and garlic.",
                    category = "Dinner",
                    dietaryTags = "Asian Fusion",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Mediterranean Chickpea Salad",
                    cookingTimeMinutes = 10,
                    instructions = "Mix chickpeas, tomato, cucumber, parsley, lemon, and olive oil.",
                    category = "Lunch",
                    dietaryTags = "Vegan, Mediterranean",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Beef Stir-Fry",
                    cookingTimeMinutes = 18,
                    instructions = "Stir-fry beef with vegetables, soy sauce, garlic, and ginger.",
                    category = "Dinner",
                    dietaryTags = "High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "French Toast",
                    cookingTimeMinutes = 12,
                    instructions = "Dip bread in egg-milk mix, fry in butter, serve with sugar.",
                    category = "Breakfast",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Margarita Pizza",
                    cookingTimeMinutes = 25,
                    instructions = "Spread tomato sauce on dough, add mozzarella and basil, bake.",
                    category = "Dinner",
                    dietaryTags = "Vegetarian, Italian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Fried Rice",
                    cookingTimeMinutes = 12,
                    instructions = "Stir-fry rice with eggs, vegetables, soy sauce, and sesame.",
                    category = "Lunch",
                    dietaryTags = "Asian Fusion",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Tuna Pasta Salad",
                    cookingTimeMinutes = 15,
                    instructions = "Mix cooked pasta with tuna, corn, mayonnaise, and lemon.",
                    category = "Lunch",
                    dietaryTags = "High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "BBQ Chicken Wings",
                    cookingTimeMinutes = 35,
                    instructions = "Season wings and bake, glaze with BBQ sauce.",
                    category = "Dinner",
                    dietaryTags = "High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Shakshuka",
                    cookingTimeMinutes = 20,
                    instructions = "Simmer tomatoes with onions and spices. Add eggs and cook.",
                    category = "Breakfast",
                    dietaryTags = "Mediterranean, Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Vegetable Soup",
                    cookingTimeMinutes = 25,
                    instructions = "Boil vegetables in broth until soft. Season and serve.",
                    category = "Lunch",
                    dietaryTags = "Vegan",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Creamy Mushroom Pasta",
                    cookingTimeMinutes = 22,
                    instructions = "Cook pasta. Prepare creamy mushroom sauce and mix.",
                    category = "Dinner",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Falafel Wrap",
                    cookingTimeMinutes = 20,
                    instructions = "Fill pita with falafel, lettuce, tomato, tahini.",
                    category = "Lunch",
                    dietaryTags = "Vegan, Middle Eastern",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Beef Burger",
                    cookingTimeMinutes = 15,
                    instructions = "Grill beef patty, add cheese, lettuce, tomato, assemble.",
                    category = "Dinner",
                    dietaryTags = "High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Lentil Stew",
                    cookingTimeMinutes = 30,
                    instructions = "Simmer lentils with onions, garlic, carrots, cumin.",
                    category = "Dinner",
                    dietaryTags = "Vegan, Healthy",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Baked Sweet Chicken",
                    cookingTimeMinutes = 30,
                    instructions = "Bake chicken with honey, soy sauce, and garlic.",
                    category = "Dinner",
                    dietaryTags = "Asian Fusion",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Fruit Salad",
                    cookingTimeMinutes = 5,
                    instructions = "Combine mixed fresh fruits with lemon and honey.",
                    category = "Snack",
                    dietaryTags = "Vegetarian, Healthy",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Stuffed Bell Peppers",
                    cookingTimeMinutes = 35,
                    instructions = "Fill peppers with rice, tomato, onion, bake.",
                    category = "Dinner",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Chicken Noodle Soup",
                    cookingTimeMinutes = 25,
                    instructions = "Cook chicken, noodles, carrots, celery in broth.",
                    category = "Dinner",
                    dietaryTags = "High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Tomato Garlic Bruschetta",
                    cookingTimeMinutes = 10,
                    instructions = "Toast bread, top with chopped tomatoes, garlic, basil, and olive oil.",
                    category = "Snack",
                    dietaryTags = "Vegetarian, Mediterranean",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Chicken Fajitas",
                    cookingTimeMinutes = 20,
                    instructions = "Sauté chicken with bell peppers, onions, and spices. Serve in tortillas.",
                    category = "Dinner",
                    dietaryTags = "Mexican",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Vegetable Quesadilla",
                    cookingTimeMinutes = 12,
                    instructions = "Fill tortilla with cheese and sautéed vegetables. Grill until crispy.",
                    category = "Lunch",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Creamy Spinach Dip",
                    cookingTimeMinutes = 15,
                    instructions = "Mix spinach, cream cheese, garlic, and spices. Heat until creamy.",
                    category = "Snack",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Lemon Garlic Chicken",
                    cookingTimeMinutes = 25,
                    instructions = "Bake chicken with lemon, garlic, olive oil, and herbs.",
                    category = "Dinner",
                    dietaryTags = "Healthy",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Vegetable Fried Noodles",
                    cookingTimeMinutes = 15,
                    instructions = "Stir-fry noodles with carrots, cabbage, soy sauce, and garlic.",
                    category = "Dinner",
                    dietaryTags = "Asian Fusion",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Beef Shawarma Wrap",
                    cookingTimeMinutes = 20,
                    instructions = "Cook beef with spices. Wrap with garlic sauce, lettuce, tomato.",
                    category = "Lunch",
                    dietaryTags = "Middle Eastern",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Grilled Salmon with Herbs",
                    cookingTimeMinutes = 18,
                    instructions = "Grill salmon with olive oil, lemon, parsley, and dill.",
                    category = "Dinner",
                    dietaryTags = "Seafood",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Potato Wedges",
                    cookingTimeMinutes = 30,
                    instructions = "Cut potatoes, season, bake until crispy.",
                    category = "Side",
                    dietaryTags = "Vegan",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Homemade Hummus",
                    cookingTimeMinutes = 8,
                    instructions = "Blend chickpeas, tahini, garlic, lemon, olive oil.",
                    category = "Snack",
                    dietaryTags = "Vegan, Middle Eastern",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Broccoli Cheese Soup",
                    cookingTimeMinutes = 25,
                    instructions = "Cook broccoli with milk, cheddar, and spices. Blend lightly.",
                    category = "Lunch",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Crispy Fish Fillet",
                    cookingTimeMinutes = 15,
                    instructions = "Coat fish with flour and spices, pan-fry until golden.",
                    category = "Dinner",
                    dietaryTags = "Seafood",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Pasta Primavera",
                    cookingTimeMinutes = 20,
                    instructions = "Mix pasta with sautéed vegetables, garlic, and olive oil.",
                    category = "Lunch",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Chicken Stir-Fry Noodles",
                    cookingTimeMinutes = 15,
                    instructions = "Stir-fry chicken with noodles, soy sauce, garlic, and peppers.",
                    category = "Dinner",
                    dietaryTags = "Asian Fusion, High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Greek Yogurt Parfait",
                    cookingTimeMinutes = 5,
                    instructions = "Layer yogurt with fruits, honey, and nuts.",
                    category = "Breakfast",
                    dietaryTags = "Vegetarian, Healthy",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Beef Stew",
                    cookingTimeMinutes = 45,
                    instructions = "Slow-cook beef with potatoes, carrots, onions, and broth.",
                    category = "Dinner",
                    dietaryTags = "High Protein",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Lentil Soup",
                    cookingTimeMinutes = 30,
                    instructions = "Simmer lentils with onions, carrots, garlic, and spices.",
                    category = "Dinner",
                    dietaryTags = "Vegan, Healthy",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Chicken Pita Pocket",
                    cookingTimeMinutes = 15,
                    instructions = "Fill pita with grilled chicken, tomato, lettuce, garlic sauce.",
                    category = "Lunch",
                    dietaryTags = "Middle Eastern",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Mango Smoothie",
                    cookingTimeMinutes = 5,
                    instructions = "Blend mango with yogurt, milk, and honey.",
                    category = "Breakfast",
                    dietaryTags = "Vegetarian, Healthy",
                    isFavorite = false
                ),
                RecipeEntity(
                    title = "Garlic Butter Green Beans",
                    cookingTimeMinutes = 10,
                    instructions = "Sauté green beans with garlic and butter.",
                    category = "Side",
                    dietaryTags = "Vegetarian",
                    isFavorite = false
                )




            )




            val recipeIds = recipes.map { recipe ->
                dao.insertRecipe(recipe).toInt()
            }

            println("🍽 Recipes inserted: ${recipeIds.size}")

            // 3. Cross refs
            val crossRefs = listOf(
                /* 1. Classic Pancakes */
                RecipeIngredientCrossRef(recipeIds[0], ingredientIds[3]),   // Flour
                RecipeIngredientCrossRef(recipeIds[0], ingredientIds[0]),   // Eggs
                RecipeIngredientCrossRef(recipeIds[0], ingredientIds[1]),   // Milk
                RecipeIngredientCrossRef(recipeIds[0], ingredientIds[79]),  // Sugar
                RecipeIngredientCrossRef(recipeIds[0], ingredientIds[72]),  // Baking Powder
                RecipeIngredientCrossRef(recipeIds[0], ingredientIds[2]),   // Butter

                /* 2. Chicken Alfredo Pasta */
                RecipeIngredientCrossRef(recipeIds[1], ingredientIds[52]),  // Pasta
                RecipeIngredientCrossRef(recipeIds[1], ingredientIds[12]),  // Chicken Breast
                RecipeIngredientCrossRef(recipeIds[1], ingredientIds[4]),   // Cream
                RecipeIngredientCrossRef(recipeIds[1], ingredientIds[8]),   // Parmesan
                RecipeIngredientCrossRef(recipeIds[1], ingredientIds[0]),   // Garlic (index 28)
                RecipeIngredientCrossRef(recipeIds[1], ingredientIds[86]),  // Olive Oil

                /* 3. Garlic Butter Shrimp */
                RecipeIngredientCrossRef(recipeIds[2], ingredientIds[22]),  // Shrimp
                RecipeIngredientCrossRef(recipeIds[2], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[2], ingredientIds[86]),  // Olive Oil
                RecipeIngredientCrossRef(recipeIds[2], ingredientIds[55]),  // Lemon
                RecipeIngredientCrossRef(recipeIds[2], ingredientIds[2]),   // Butter

                /* 4. Vegetable Stir-Fry */
                RecipeIngredientCrossRef(recipeIds[3], ingredientIds[32]),  // Carrot
                RecipeIngredientCrossRef(recipeIds[3], ingredientIds[33]),  // Bell Pepper
                RecipeIngredientCrossRef(recipeIds[3], ingredientIds[35]),  // Broccoli
                RecipeIngredientCrossRef(recipeIds[3], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[3], ingredientIds[74]),  // Soy Sauce
                RecipeIngredientCrossRef(recipeIds[3], ingredientIds[41]),  // Ginger

                /* 5. Beef Tacos */
                RecipeIngredientCrossRef(recipeIds[4], ingredientIds[14]),  // Ground Beef
                RecipeIngredientCrossRef(recipeIds[4], ingredientIds[60]),  // Tortilla
                RecipeIngredientCrossRef(recipeIds[4], ingredientIds[79]),  // Sugar
                RecipeIngredientCrossRef(recipeIds[4], ingredientIds[68]),  // Chili Powder
                RecipeIngredientCrossRef(recipeIds[4], ingredientIds[33]),  // Bell Pepper
                RecipeIngredientCrossRef(recipeIds[4], ingredientIds[28]),  // Onion

                /* 6. Avocado Toast */
                RecipeIngredientCrossRef(recipeIds[5], ingredientIds[59]),  // Bread
                RecipeIngredientCrossRef(recipeIds[5], ingredientIds[58]),  // Avocado
                RecipeIngredientCrossRef(recipeIds[5], ingredientIds[55]),  // Lemon
                RecipeIngredientCrossRef(recipeIds[5], ingredientIds[71]),  // Salt
                RecipeIngredientCrossRef(recipeIds[5], ingredientIds[72]),  // Pepper

                /* 7. Tomato Basil Soup */
                RecipeIngredientCrossRef(recipeIds[6], ingredientIds[26]),  // Tomato
                RecipeIngredientCrossRef(recipeIds[6], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[6], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[6], ingredientIds[88]),  // Basil
                RecipeIngredientCrossRef(recipeIds[6], ingredientIds[86]),  // Olive Oil

                /* 8. Chicken Caesar Salad */
                RecipeIngredientCrossRef(recipeIds[7], ingredientIds[12]),  // Chicken Breast
                RecipeIngredientCrossRef(recipeIds[7], ingredientIds[37]),  // Lettuce
                RecipeIngredientCrossRef(recipeIds[7], ingredientIds[8]),   // Parmesan
                RecipeIngredientCrossRef(recipeIds[7], ingredientIds[84]),  // Vinegar
                RecipeIngredientCrossRef(recipeIds[7], ingredientIds[86]),  // Olive Oil

                /* 9. Lemon Herb Salmon */
                RecipeIngredientCrossRef(recipeIds[8], ingredientIds[20]),  // Salmon
                RecipeIngredientCrossRef(recipeIds[8], ingredientIds[55]),  // Lemon
                RecipeIngredientCrossRef(recipeIds[8], ingredientIds[90]),  // Thyme
                RecipeIngredientCrossRef(recipeIds[8], ingredientIds[91]),  // Rosemary
                RecipeIngredientCrossRef(recipeIds[8], ingredientIds[86]),  // Olive Oil

                /* 10. Garlic Mashed Potatoes */
                RecipeIngredientCrossRef(recipeIds[9], ingredientIds[30]),  // Potato
                RecipeIngredientCrossRef(recipeIds[9], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[9], ingredientIds[2]),   // Butter
                RecipeIngredientCrossRef(recipeIds[9], ingredientIds[1]),   // Milk
                RecipeIngredientCrossRef(recipeIds[9], ingredientIds[71]),  // Salt

                /* 11. Greek Salad */
                RecipeIngredientCrossRef(recipeIds[10], ingredientIds[26]), // Tomato
                RecipeIngredientCrossRef(recipeIds[10], ingredientIds[56]), // Cucumber
                RecipeIngredientCrossRef(recipeIds[10], ingredientIds[10]), // Feta
                RecipeIngredientCrossRef(recipeIds[10], ingredientIds[58]), // Avocado
                RecipeIngredientCrossRef(recipeIds[10], ingredientIds[86]), // Olive Oil

                /* 12. Chocolate Chip Cookies */
                RecipeIngredientCrossRef(recipeIds[11], ingredientIds[3]),  // Flour
                RecipeIngredientCrossRef(recipeIds[11], ingredientIds[2]),  // Butter
                RecipeIngredientCrossRef(recipeIds[11], ingredientIds[80]), // Brown Sugar
                RecipeIngredientCrossRef(recipeIds[11], ingredientIds[0]),  // Eggs
                RecipeIngredientCrossRef(recipeIds[11], ingredientIds[77]), // Chocolate Chips

                /* 13. Baked Chicken Drumsticks */
                RecipeIngredientCrossRef(recipeIds[12], ingredientIds[18]), // Chicken Drumsticks
                RecipeIngredientCrossRef(recipeIds[12], ingredientIds[71]), // Salt
                RecipeIngredientCrossRef(recipeIds[12], ingredientIds[72]), // Pepper
                RecipeIngredientCrossRef(recipeIds[12], ingredientIds[88]), // Paprika
                RecipeIngredientCrossRef(recipeIds[12], ingredientIds[86]), // Olive Oil

                /* 14. Mushroom Risotto */
                RecipeIngredientCrossRef(recipeIds[13], ingredientIds[57]), // Mushrooms
                RecipeIngredientCrossRef(recipeIds[13], ingredientIds[54]), // Rice
                RecipeIngredientCrossRef(recipeIds[13], ingredientIds[28]), // Onion
                RecipeIngredientCrossRef(recipeIds[13], ingredientIds[2]),  // Butter
                RecipeIngredientCrossRef(recipeIds[13], ingredientIds[1]),  // Milk

                /* 15. Spicy Chili Beans */
                RecipeIngredientCrossRef(recipeIds[14], ingredientIds[63]), // Kidney Beans
                RecipeIngredientCrossRef(recipeIds[14], ingredientIds[67]), // Chili Pepper
                RecipeIngredientCrossRef(recipeIds[14], ingredientIds[26]), // Tomato
                RecipeIngredientCrossRef(recipeIds[14], ingredientIds[28]), // Onion
                RecipeIngredientCrossRef(recipeIds[14], ingredientIds[68]), // Chili Powder

                /* 16. Tuna Sandwich */
                RecipeIngredientCrossRef(recipeIds[15], ingredientIds[64]), // Canned Tuna
                RecipeIngredientCrossRef(recipeIds[15], ingredientIds[59]), // Bread
                RecipeIngredientCrossRef(recipeIds[15], ingredientIds[83]), // Mayonnaise
                RecipeIngredientCrossRef(recipeIds[15], ingredientIds[37]), // Lettuce

                /* 17. Banana Smoothie */
                RecipeIngredientCrossRef(recipeIds[16], ingredientIds[57]), // Banana
                RecipeIngredientCrossRef(recipeIds[16], ingredientIds[5]),  // Yogurt
                RecipeIngredientCrossRef(recipeIds[16], ingredientIds[1]),  // Milk
                RecipeIngredientCrossRef(recipeIds[16], ingredientIds[79]), // Honey

                /* 18. Vegetable Omelette */
                RecipeIngredientCrossRef(recipeIds[17], ingredientIds[0]),  // Eggs
                RecipeIngredientCrossRef(recipeIds[17], ingredientIds[26]), // Tomato
                RecipeIngredientCrossRef(recipeIds[17], ingredientIds[28]), // Onion
                RecipeIngredientCrossRef(recipeIds[17], ingredientIds[57]), // Mushrooms
                RecipeIngredientCrossRef(recipeIds[17], ingredientIds[7]),  // Cheddar

                /* 19. Sweet Potato Fries */
                RecipeIngredientCrossRef(recipeIds[18], ingredientIds[42]), // Sweet Potato
                RecipeIngredientCrossRef(recipeIds[18], ingredientIds[86]), // Olive Oil
                RecipeIngredientCrossRef(recipeIds[18], ingredientIds[68]), // Chili Powder

                /* 20. Garlic Bread */
                RecipeIngredientCrossRef(recipeIds[19], ingredientIds[59]), // Bread
                RecipeIngredientCrossRef(recipeIds[19], ingredientIds[2]),  // Butter
                RecipeIngredientCrossRef(recipeIds[19], ingredientIds[29]), // Garlic
                RecipeIngredientCrossRef(recipeIds[19], ingredientIds[88]),  // Parsley

                RecipeIngredientCrossRef(recipeIds[20], ingredientIds[52]),  // Pasta
                RecipeIngredientCrossRef(recipeIds[20], ingredientIds[14]),  // Ground Beef
                RecipeIngredientCrossRef(recipeIds[20], ingredientIds[26]),  // Tomato
                RecipeIngredientCrossRef(recipeIds[20], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[20], ingredientIds[29]),   // Garlic

                RecipeIngredientCrossRef(recipeIds[21], ingredientIds[59]),  // Bread
                RecipeIngredientCrossRef(recipeIds[21], ingredientIds[7]),   // Cheddar
                RecipeIngredientCrossRef(recipeIds[21], ingredientIds[2]),    // Butter

                RecipeIngredientCrossRef(recipeIds[22], ingredientIds[12]),  // Chicken Breast
                RecipeIngredientCrossRef(recipeIds[22], ingredientIds[74]),  // Curry Powder
                RecipeIngredientCrossRef(recipeIds[22], ingredientIds[48]),  // Coconut Milk
                RecipeIngredientCrossRef(recipeIds[22], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[22], ingredientIds[29]),   // Garlic

                RecipeIngredientCrossRef(recipeIds[22], ingredientIds[12]),  // Chicken Breast
                RecipeIngredientCrossRef(recipeIds[22], ingredientIds[74]),  // Curry Powder
                RecipeIngredientCrossRef(recipeIds[22], ingredientIds[48]),  // Coconut Milk
                RecipeIngredientCrossRef(recipeIds[22], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[22], ingredientIds[29]),   // Garlic

                RecipeIngredientCrossRef(recipeIds[23], ingredientIds[49]),  // Chickpeas
                RecipeIngredientCrossRef(recipeIds[23], ingredientIds[26]),  // Tomato
                RecipeIngredientCrossRef(recipeIds[23], ingredientIds[56]),  // Cucumber
                RecipeIngredientCrossRef(recipeIds[23], ingredientIds[93]),  // Parsley
                RecipeIngredientCrossRef(recipeIds[23], ingredientIds[55]),  // Lemon
                RecipeIngredientCrossRef(recipeIds[23], ingredientIds[86]),   // Olive Oil

                RecipeIngredientCrossRef(recipeIds[24], ingredientIds[14]),  // Ground Beef
                RecipeIngredientCrossRef(recipeIds[24], ingredientIds[33]),  // Bell Pepper
                RecipeIngredientCrossRef(recipeIds[24], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[24], ingredientIds[41]),  // Ginger
                RecipeIngredientCrossRef(recipeIds[24], ingredientIds[74]),   // Soy Sauce

                RecipeIngredientCrossRef(recipeIds[25], ingredientIds[59]),  // Bread
                RecipeIngredientCrossRef(recipeIds[25], ingredientIds[0]),   // Eggs
                RecipeIngredientCrossRef(recipeIds[25], ingredientIds[1]),   // Milk
                RecipeIngredientCrossRef(recipeIds[25], ingredientIds[2]),   // Butter
                RecipeIngredientCrossRef(recipeIds[25], ingredientIds[79]),   // Sugar

                RecipeIngredientCrossRef(recipeIds[26], ingredientIds[65]),  // Tomato Sauce
                RecipeIngredientCrossRef(recipeIds[26], ingredientIds[6]),   // Mozzarella
                RecipeIngredientCrossRef(recipeIds[26], ingredientIds[88]),  // Basil
                RecipeIngredientCrossRef(recipeIds[26], ingredientIds[3]),    // Flour (for dough)

                RecipeIngredientCrossRef(recipeIds[27], ingredientIds[54]),  // Rice
                RecipeIngredientCrossRef(recipeIds[27], ingredientIds[0]),   // Eggs
                RecipeIngredientCrossRef(recipeIds[27], ingredientIds[33]),  // Bell Pepper
                RecipeIngredientCrossRef(recipeIds[27], ingredientIds[74]),  // Soy Sauce
                RecipeIngredientCrossRef(recipeIds[27], ingredientIds[99]),   // Sesame Seeds

                RecipeIngredientCrossRef(recipeIds[28], ingredientIds[52]),  // Pasta
                RecipeIngredientCrossRef(recipeIds[28], ingredientIds[64]),  // Canned Tuna
                RecipeIngredientCrossRef(recipeIds[28], ingredientIds[66]),  // Canned Corn
                RecipeIngredientCrossRef(recipeIds[28], ingredientIds[83]),  // Mayonnaise
                RecipeIngredientCrossRef(recipeIds[28], ingredientIds[55]),   // Lemon

                RecipeIngredientCrossRef(recipeIds[29], ingredientIds[18]),  // Chicken Wings
                RecipeIngredientCrossRef(recipeIds[29], ingredientIds[71]),  // Salt
                RecipeIngredientCrossRef(recipeIds[29], ingredientIds[72]),  // Pepper
                RecipeIngredientCrossRef(recipeIds[29], ingredientIds[79]),   // Sugar (BBQ base)

                RecipeIngredientCrossRef(recipeIds[30], ingredientIds[26]),  // Tomato
                RecipeIngredientCrossRef(recipeIds[30], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[30], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[30], ingredientIds[0]),   // Eggs
                RecipeIngredientCrossRef(recipeIds[30], ingredientIds[68]),   // Chili Powder

                RecipeIngredientCrossRef(recipeIds[31], ingredientIds[26]),  // Tomato
                RecipeIngredientCrossRef(recipeIds[31], ingredientIds[32]),  // Carrot
                RecipeIngredientCrossRef(recipeIds[31], ingredientIds[35]),  // Broccoli
                RecipeIngredientCrossRef(recipeIds[31], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[31], ingredientIds[29]),   // Garlic

                RecipeIngredientCrossRef(recipeIds[32], ingredientIds[52]),  // Pasta
                RecipeIngredientCrossRef(recipeIds[32], ingredientIds[57]),  // Mushrooms
                RecipeIngredientCrossRef(recipeIds[32], ingredientIds[4]),   // Cream
                RecipeIngredientCrossRef(recipeIds[32], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[32], ingredientIds[2]),    // Butter

                RecipeIngredientCrossRef(recipeIds[33], ingredientIds[49]),  // Chickpeas
                RecipeIngredientCrossRef(recipeIds[33], ingredientIds[37]),  // Lettuce
                RecipeIngredientCrossRef(recipeIds[33], ingredientIds[26]),  // Tomato
                RecipeIngredientCrossRef(recipeIds[33], ingredientIds[103]), // Tahini
                RecipeIngredientCrossRef(recipeIds[33], ingredientIds[60]),   // Tortilla / Pita

                RecipeIngredientCrossRef(recipeIds[34], ingredientIds[14]),  // Ground Beef
                RecipeIngredientCrossRef(recipeIds[34], ingredientIds[59]),  // Bread/Bun
                RecipeIngredientCrossRef(recipeIds[34], ingredientIds[7]),   // Cheddar
                RecipeIngredientCrossRef(recipeIds[34], ingredientIds[37]),  // Lettuce
                RecipeIngredientCrossRef(recipeIds[34], ingredientIds[26]),   // Tomato

                RecipeIngredientCrossRef(recipeIds[35], ingredientIds[81]),  // Lentils
                RecipeIngredientCrossRef(recipeIds[35], ingredientIds[32]),  // Carrot
                RecipeIngredientCrossRef(recipeIds[35], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[35], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[35], ingredientIds[87]),   // Cumin

                RecipeIngredientCrossRef(recipeIds[36], ingredientIds[12]),  // Chicken Breast
                RecipeIngredientCrossRef(recipeIds[36], ingredientIds[79]),  // Honey
                RecipeIngredientCrossRef(recipeIds[36], ingredientIds[74]),  // Soy Sauce
                RecipeIngredientCrossRef(recipeIds[36], ingredientIds[29]),   // Garlic

                RecipeIngredientCrossRef(recipeIds[37], ingredientIds[56]),  // Apple
                RecipeIngredientCrossRef(recipeIds[37], ingredientIds[57]),  // Banana
                RecipeIngredientCrossRef(recipeIds[37], ingredientIds[58]),  // Mango
                RecipeIngredientCrossRef(recipeIds[37], ingredientIds[55]),  // Lemon
                RecipeIngredientCrossRef(recipeIds[37], ingredientIds[79]),   // Honey

                RecipeIngredientCrossRef(recipeIds[38], ingredientIds[33]),  // Bell Pepper
                RecipeIngredientCrossRef(recipeIds[38], ingredientIds[54]),  // Rice
                RecipeIngredientCrossRef(recipeIds[38], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[38], ingredientIds[26]),   // Tomato

                RecipeIngredientCrossRef(recipeIds[39], ingredientIds[12]),  // Chicken Breast
                RecipeIngredientCrossRef(recipeIds[39], ingredientIds[32]),  // Carrot
                RecipeIngredientCrossRef(recipeIds[39], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[39], ingredientIds[114]), // Noodles (Rice Noodles)
                RecipeIngredientCrossRef(recipeIds[39], ingredientIds[110]),  // Chicken Stock

                RecipeIngredientCrossRef(recipeIds[40], ingredientIds[59]),  // Bread
                RecipeIngredientCrossRef(recipeIds[40], ingredientIds[26]),  // Tomato
                RecipeIngredientCrossRef(recipeIds[40], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[40], ingredientIds[88]),  // Basil
                RecipeIngredientCrossRef(recipeIds[40], ingredientIds[86]),   // Olive Oil

                RecipeIngredientCrossRef(recipeIds[41], ingredientIds[12]),  // Chicken Breast
                RecipeIngredientCrossRef(recipeIds[41], ingredientIds[33]),  // Bell Pepper
                RecipeIngredientCrossRef(recipeIds[41], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[41], ingredientIds[60]),  // Tortilla
                RecipeIngredientCrossRef(recipeIds[41], ingredientIds[72]),   // Pepper

                RecipeIngredientCrossRef(recipeIds[42], ingredientIds[60]),  // Tortilla
                RecipeIngredientCrossRef(recipeIds[42], ingredientIds[7]),   // Cheddar
                RecipeIngredientCrossRef(recipeIds[42], ingredientIds[33]),  // Bell Pepper
                RecipeIngredientCrossRef(recipeIds[42], ingredientIds[35]),   // Broccoli

                RecipeIngredientCrossRef(recipeIds[43], ingredientIds[36]),  // Spinach
                RecipeIngredientCrossRef(recipeIds[43], ingredientIds[11]),  // Cream Cheese
                RecipeIngredientCrossRef(recipeIds[43], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[43], ingredientIds[4]),    // Cream

                RecipeIngredientCrossRef(recipeIds[44], ingredientIds[12]),  // Chicken Breast
                RecipeIngredientCrossRef(recipeIds[44], ingredientIds[55]),  // Lemon
                RecipeIngredientCrossRef(recipeIds[44], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[44], ingredientIds[86]),  // Olive Oil
                RecipeIngredientCrossRef(recipeIds[44], ingredientIds[90]),   // Thyme

                RecipeIngredientCrossRef(recipeIds[45], ingredientIds[114]), // Noodles
                RecipeIngredientCrossRef(recipeIds[45], ingredientIds[32]),  // Carrot
                RecipeIngredientCrossRef(recipeIds[45], ingredientIds[33]),  // Bell Pepper
                RecipeIngredientCrossRef(recipeIds[45], ingredientIds[95]),  // Soy Sauce
                RecipeIngredientCrossRef(recipeIds[45], ingredientIds[29]),   // Garlic

                RecipeIngredientCrossRef(recipeIds[46], ingredientIds[15]),  // Beef Steak
                RecipeIngredientCrossRef(recipeIds[46], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[46], ingredientIds[93]),  // Parsley
                RecipeIngredientCrossRef(recipeIds[46], ingredientIds[103]), // Tahini
                RecipeIngredientCrossRef(recipeIds[46], ingredientIds[60]),   // Pita/Tortilla

                RecipeIngredientCrossRef(recipeIds[47], ingredientIds[20]),  // Salmon
                RecipeIngredientCrossRef(recipeIds[47], ingredientIds[55]),  // Lemon
                RecipeIngredientCrossRef(recipeIds[47], ingredientIds[93]),  // Parsley
                RecipeIngredientCrossRef(recipeIds[47], ingredientIds[90]),  // Thyme
                RecipeIngredientCrossRef(recipeIds[47], ingredientIds[86]),   // Olive Oil

                RecipeIngredientCrossRef(recipeIds[48], ingredientIds[30]),  // Potato
                RecipeIngredientCrossRef(recipeIds[48], ingredientIds[86]),  // Olive Oil
                RecipeIngredientCrossRef(recipeIds[48], ingredientIds[72]),   // Pepper

                RecipeIngredientCrossRef(recipeIds[49], ingredientIds[49]),  // Chickpeas
                RecipeIngredientCrossRef(recipeIds[49], ingredientIds[103]), // Tahini
                RecipeIngredientCrossRef(recipeIds[49], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[49], ingredientIds[55]),  // Lemon
                RecipeIngredientCrossRef(recipeIds[49], ingredientIds[86]),   // Olive Oil

                RecipeIngredientCrossRef(recipeIds[50], ingredientIds[35]),  // Broccoli
                RecipeIngredientCrossRef(recipeIds[50], ingredientIds[7]),   // Cheddar
                RecipeIngredientCrossRef(recipeIds[50], ingredientIds[1]),   // Milk
                RecipeIngredientCrossRef(recipeIds[50], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[50], ingredientIds[2]),    // Butter

                RecipeIngredientCrossRef(recipeIds[51], ingredientIds[23]),  // Cod
                RecipeIngredientCrossRef(recipeIds[51], ingredientIds[3]),   // Flour
                RecipeIngredientCrossRef(recipeIds[51], ingredientIds[2]),   // Butter / Oil
                RecipeIngredientCrossRef(recipeIds[51], ingredientIds[71]),  // Salt
                RecipeIngredientCrossRef(recipeIds[51], ingredientIds[72]),   // Pepper

                RecipeIngredientCrossRef(recipeIds[52], ingredientIds[52]),  // Pasta
                RecipeIngredientCrossRef(recipeIds[52], ingredientIds[33]),  // Bell Pepper
                RecipeIngredientCrossRef(recipeIds[52], ingredientIds[35]),  // Broccoli
                RecipeIngredientCrossRef(recipeIds[52], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[52], ingredientIds[86]),   // Olive Oil

                RecipeIngredientCrossRef(recipeIds[53], ingredientIds[12]),  // Chicken Breast
                RecipeIngredientCrossRef(recipeIds[53], ingredientIds[114]), // Noodles
                RecipeIngredientCrossRef(recipeIds[53], ingredientIds[33]),  // Bell Pepper
                RecipeIngredientCrossRef(recipeIds[53], ingredientIds[95]),  // Soy Sauce
                RecipeIngredientCrossRef(recipeIds[53], ingredientIds[29]),   // Garlic

                RecipeIngredientCrossRef(recipeIds[54], ingredientIds[5]),   // Yogurt
                RecipeIngredientCrossRef(recipeIds[54], ingredientIds[56]),  // Apple
                RecipeIngredientCrossRef(recipeIds[54], ingredientIds[57]),  // Banana
                RecipeIngredientCrossRef(recipeIds[54], ingredientIds[79]),  // Honey
                RecipeIngredientCrossRef(recipeIds[54], ingredientIds[107]),  // Almonds

                RecipeIngredientCrossRef(recipeIds[55], ingredientIds[15]),  // Beef Steak
                RecipeIngredientCrossRef(recipeIds[55], ingredientIds[30]),  // Potato
                RecipeIngredientCrossRef(recipeIds[55], ingredientIds[32]),  // Carrot
                RecipeIngredientCrossRef(recipeIds[55], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[55], ingredientIds[110]),  // Beef Stock

                RecipeIngredientCrossRef(recipeIds[56], ingredientIds[81]),  // Lentils
                RecipeIngredientCrossRef(recipeIds[56], ingredientIds[32]),  // Carrot
                RecipeIngredientCrossRef(recipeIds[56], ingredientIds[28]),  // Onion
                RecipeIngredientCrossRef(recipeIds[56], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[56], ingredientIds[87]),   // Cumin

                RecipeIngredientCrossRef(recipeIds[57], ingredientIds[12]),  // Chicken Breast
                RecipeIngredientCrossRef(recipeIds[57], ingredientIds[26]),  // Tomato
                RecipeIngredientCrossRef(recipeIds[57], ingredientIds[37]),  // Lettuce
                RecipeIngredientCrossRef(recipeIds[57], ingredientIds[103]), // Tahini / Garlic Sauce
                RecipeIngredientCrossRef(recipeIds[57], ingredientIds[60]),   // Pita Bread

                RecipeIngredientCrossRef(recipeIds[58], ingredientIds[58]),  // Mango
                RecipeIngredientCrossRef(recipeIds[58], ingredientIds[5]),   // Yogurt
                RecipeIngredientCrossRef(recipeIds[58], ingredientIds[1]),   // Milk
                RecipeIngredientCrossRef(recipeIds[58], ingredientIds[79]),   // Honey

                RecipeIngredientCrossRef(recipeIds[59], ingredientIds[38]),  // Green Beans
                RecipeIngredientCrossRef(recipeIds[59], ingredientIds[29]),  // Garlic
                RecipeIngredientCrossRef(recipeIds[59], ingredientIds[2]),    // Butter



            )

            dao.insertRecipeIngredients(crossRefs)

            println("✅🔥 SEEDING FINISHED SUCCESSFULLY 🔥✅")
        } catch (e: Exception) {
            println("💥 Seeder ERROR: ${e.message}")
            e.printStackTrace()
        }
    }
}
