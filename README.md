CookUp
Android Course Project — Ingredient-Based Recipe Finder App
Team Members: Lynn El Haly and Rey Abou Rjeily

App Description:
CookUp is an Android application designed to help users decide what to cook based on the ingredients they already have available.
Instead of searching for recipes manually, users select ingredients and receive a list of suitable meal ideas that match their selection.
The application allows users to create an account, log in securely, browse recipes, view full cooking instructions, and save favorite meals for later use.
All recipe data and user favorites are stored in Firebase Firestore, while user authentication is handled using Firebase Authentication.
The application is built following the MVVM architecture, which ensures a clean separation between the user interface, application logic, and data sources.
The project is structured to allow future enhancements such as advanced filtering, offline access, and personalized recipe suggestions.

Main Features:
-User registration and login using Firebase Authentication.
-Ingredient selection with search and category filtering.
-Automatic recipe matching based on selected ingredients.
-Detailed recipe pages including description, ingredients list, preparation steps, cooking time, and servings.
-Favorite recipes management linked to the user account.
-Navigation between screens using Jetpack Navigation Compose.
-Fully developed user interface using Jetpack Compose.

The project follows the MVVM (Model–View–ViewModel) pattern.

Data Flow:
UI Screens → ViewModels → Repositories → Firebase Firestore / Firebase Authentication
Data changes are exposed through StateFlow, allowing the interface to update automatically when the underlying data changes.

Setup Instructions:

Prerequisites:
-Android Studio Narwhal (2025.x)
-JDK 17
-Gradle Plugin 8.5.2 or newer
-Kotlin 1.9 or newer
-A Firebase project with Authentication and Firestore enabled

How to Run:
1-Clone the repository: https://github.com/lynnelhaly/Lynn_ElHaly_Rey_AbouRjeily_Project.git
2-Open the project in Android Studio.
3-Add the google-services.json file to the app module.
4-Wait for Gradle synchronization to finish.
5-Click Run to launch the application on an emulator or physical device.
6-Create an account or log in to access the application.
7-Select ingredients, browse matching recipes, and save favorites.