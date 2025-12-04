package com.example.cookup.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cookup.data.model.IngredientEntity
import com.example.cookup.data.model.RecipeEntity
import com.example.cookup.data.model.RecipeIngredientCrossRef

@Database(
    entities = [
        IngredientEntity::class,
        RecipeEntity::class,
        RecipeIngredientCrossRef::class
    ],
    version = 2, // IMPORTANT: bump version to rerun seeding
    exportSchema = false
)
abstract class CookUpDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao

    companion object {
        @Volatile
        private var INSTANCE: CookUpDatabase? = null

        fun getInstance(context: Context): CookUpDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    CookUpDatabase::class.java,
                    "cookup_db"
                )
                    .fallbackToDestructiveMigration()   // ADD THIS
                    .addCallback(object : Callback() {  // ADD THIS
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Thread {
                                DatabaseSeeder.seed(getInstance(context))
                            }.start()
                        }
                    })
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
