package com.example.cookup

import android.app.Application
import com.example.cookup.data.CookUpRepository
import com.example.cookup.data.local.CookUpDatabase
import com.example.cookup.data.local.DatabaseSeeder

class CookUpApp : Application() {

    private val database by lazy {
        CookUpDatabase.getInstance(this)
    }

    val repository by lazy {
        CookUpRepository(database.recipeDao())
    }

    override fun onCreate() {
        super.onCreate()

        println("🔥 APP STARTED — calling DatabaseSeeder")
        DatabaseSeeder.seed(database)
        println("🔥 Seeder call finished")
    }
}
