package com.example.cookup.data.local

data class Recipe(
    val id: Int,
    val title: String,
    val description: String,
    val cookingTimeMinutes: Int,
    val servings: Int,
    val ingredients: List<String>,
    val steps: List<String>
)

val recipes = listOf(

    Recipe(
        1,
        "Grilled Chicken with Rice",
        "Healthy grilled chicken served with rice.",
        35,
        2,
        listOf("Chicken", "Rice", "Garlic", "Olive oil"),
        listOf(
            "Season the chicken with garlic and salt.",
            "Heat olive oil in a pan.",
            "Grill chicken for 6 minutes per side.",
            "Cook rice in boiling water.",
            "Serve chicken over rice."
        )
    ),

    Recipe(
        2,
        "Cheese Omelette",
        "Quick breakfast omelette with cheese.",
        10,
        1,
        listOf("Egg", "Cheese", "Butter"),
        listOf(
            "Beat eggs with salt.",
            "Heat butter in pan.",
            "Pour eggs into pan.",
            "Add cheese and fold."
        )
    ),

    Recipe(
        3,
        "Tomato Rice",
        "Light vegetarian rice dish.",
        25,
        2,
        listOf("Rice", "Tomato", "Onion", "Olive oil"),
        listOf(
            "Chop onion and tomato.",
            "Heat olive oil.",
            "Cook onion until soft.",
            "Add tomato and rice.",
            "Add water and cook rice."
        )
    ),

    Recipe(
        4,
        "Chicken Pasta",
        "Creamy chicken pasta.",
        30,
        2,
        listOf("Chicken", "Pasta", "Garlic", "Cheese"),
        listOf(
            "Cook pasta in salted water.",
            "Cook chicken in pan.",
            "Add garlic.",
            "Mix pasta and chicken.",
            "Add cheese and serve."
        )
    ),

    Recipe(
        5,
        "Vegetable Stir Fry",
        "Quick mixed vegetable dish.",
        20,
        2,
        listOf("Carrot", "Onion", "Garlic", "Olive oil"),
        listOf(
            "Chop vegetables.",
            "Heat olive oil.",
            "Stir fry vegetables.",
            "Cook until tender."
        )
    ),

    Recipe(
        6,
        "Fried Eggs",
        "Simple fried eggs.",
        5,
        1,
        listOf("Egg", "Butter"),
        listOf(
            "Heat butter in pan.",
            "Crack eggs into pan.",
            "Cook until set."
        )
    ),

    Recipe(
        7,
        "Chicken Sandwich",
        "Quick chicken sandwich.",
        15,
        1,
        listOf("Chicken", "Bread", "Cheese"),
        listOf(
            "Cook chicken.",
            "Toast bread.",
            "Assemble sandwich with cheese."
        )
    ),

    Recipe(
        8,
        "Rice with Vegetables",
        "Rice cooked with vegetables.",
        30,
        3,
        listOf("Rice", "Carrot", "Onion"),
        listOf(
            "Chop vegetables.",
            "Cook vegetables.",
            "Add rice and water.",
            "Cook until rice is done."
        )
    ),

    Recipe(
        9,
        "Garlic Pasta",
        "Simple garlic flavored pasta.",
        20,
        2,
        listOf("Pasta", "Garlic", "Olive oil"),
        listOf(
            "Cook pasta.",
            "Heat olive oil.",
            "Add garlic.",
            "Mix pasta with garlic oil."
        )
    ),

    Recipe(
        10,
        "Egg Fried Rice",
        "Classic egg fried rice.",
        25,
        2,
        listOf("Rice", "Egg", "Onion"),
        listOf(
            "Cook rice.",
            "Scramble eggs.",
            "Add onion.",
            "Mix rice with eggs."
        )
    ),

    Recipe(
        11,
        "Chicken Salad",
        "Fresh chicken salad.",
        20,
        2,
        listOf("Chicken", "Tomato", "Onion"),
        listOf(
            "Cook chicken.",
            "Chop vegetables.",
            "Mix everything together."
        )
    ),

    Recipe(
        12,
        "Cheese Toast",
        "Toasted bread with cheese.",
        10,
        1,
        listOf("Bread", "Cheese"),
        listOf(
            "Place cheese on bread.",
            "Toast until cheese melts."
        )
    ),

    Recipe(
        13,
        "Rice Omelette",
        "Egg omelette with rice.",
        15,
        1,
        listOf("Egg", "Rice"),
        listOf(
            "Beat eggs.",
            "Add cooked rice.",
            "Cook omelette."
        )
    ),

    Recipe(
        14,
        "Chicken Rice Bowl",
        "Balanced chicken and rice bowl.",
        30,
        2,
        listOf("Chicken", "Rice"),
        listOf(
            "Cook rice.",
            "Grill chicken.",
            "Serve chicken over rice."
        )
    ),

    Recipe(
        15,
        "Vegetable Omelette",
        "Egg omelette with vegetables.",
        15,
        1,
        listOf("Egg", "Onion", "Tomato"),
        listOf(
            "Beat eggs.",
            "Add vegetables.",
            "Cook omelette."
        )
    ),

    Recipe(
        16,
        "Pasta with Tomato",
        "Classic tomato pasta.",
        25,
        2,
        listOf("Pasta", "Tomato", "Garlic"),
        listOf(
            "Cook pasta.",
            "Cook tomato and garlic.",
            "Mix pasta with sauce."
        )
    ),

    Recipe(
        17,
        "Chicken Wrap",
        "Easy chicken wrap.",
        20,
        1,
        listOf("Chicken", "Bread"),
        listOf(
            "Cook chicken.",
            "Wrap chicken in bread."
        )
    ),

    Recipe(
        18,
        "Rice Soup",
        "Light rice soup.",
        40,
        3,
        listOf("Rice", "Onion"),
        listOf(
            "Boil water.",
            "Add onion.",
            "Add rice and cook."
        )
    ),

    Recipe(
        19,
        "Egg Sandwich",
        "Simple egg sandwich.",
        10,
        1,
        listOf("Egg", "Bread"),
        listOf(
            "Cook egg.",
            "Place egg in bread."
        )
    ),

    Recipe(
        20,
        "Chicken Pasta Salad",
        "Cold pasta salad with chicken.",
        30,
        3,
        listOf("Chicken", "Pasta", "Tomato"),
        listOf(
            "Cook pasta.",
            "Cook chicken.",
            "Mix with tomato."
        )
    ),

    Recipe(
        21,
        "Vegetable Rice",
        "Rice cooked with vegetables.",
        30,
        3,
        listOf("Rice", "Carrot", "Onion"),
        listOf(
            "Cook vegetables.",
            "Add rice and water.",
            "Cook until done."
        )
    ),

    Recipe(
        22,
        "Garlic Eggs",
        "Eggs cooked with garlic.",
        10,
        1,
        listOf("Egg", "Garlic"),
        listOf(
            "Heat oil.",
            "Add garlic.",
            "Cook eggs."
        )
    ),

    Recipe(
        23,
        "Chicken Tomato Stir Fry",
        "Chicken cooked with tomato.",
        25,
        2,
        listOf("Chicken", "Tomato"),
        listOf(
            "Cook chicken.",
            "Add tomato.",
            "Cook until done."
        )
    ),

    Recipe(
        24,
        "Rice and Cheese",
        "Cheesy rice dish.",
        20,
        2,
        listOf("Rice", "Cheese"),
        listOf(
            "Cook rice.",
            "Add cheese.",
            "Mix well."
        )
    ),

    Recipe(
        25,
        "Egg Tomato Scramble",
        "Scrambled eggs with tomato.",
        10,
        1,
        listOf("Egg", "Tomato"),
        listOf(
            "Beat eggs.",
            "Add tomato.",
            "Cook scramble."
        )
    ),

    Recipe(
        26,
        "Chicken Rice Soup",
        "Warm chicken and rice soup.",
        45,
        4,
        listOf("Chicken", "Rice", "Onion"),
        listOf(
            "Boil water.",
            "Add chicken.",
            "Add onion and rice.",
            "Cook until tender."
        )
    ),

    Recipe(
        27,
        "Vegetable Pasta",
        "Pasta with vegetables.",
        30,
        2,
        listOf("Pasta", "Carrot", "Onion"),
        listOf(
            "Cook pasta.",
            "Cook vegetables.",
            "Mix together."
        )
    ),

    Recipe(
        28,
        "Cheese Eggs",
        "Eggs cooked with cheese.",
        10,
        1,
        listOf("Egg", "Cheese"),
        listOf(
            "Beat eggs.",
            "Add cheese.",
            "Cook eggs."
        )
    ),

    Recipe(
        29,
        "Chicken Garlic Rice",
        "Chicken flavored rice.",
        35,
        3,
        listOf("Chicken", "Rice", "Garlic"),
        listOf(
            "Cook chicken.",
            "Add garlic.",
            "Add rice and water.",
            "Cook until done."
        )
    ),

    Recipe(
        30,
        "Vegetable Soup",
        "Light vegetable soup.",
        40,
        4,
        listOf("Carrot", "Onion", "Tomato"),
        listOf(
            "Boil water.",
            "Add vegetables.",
            "Cook until soft."
        )
    )
)
