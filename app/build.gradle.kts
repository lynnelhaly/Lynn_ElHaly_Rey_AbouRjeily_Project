kotlin
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.kapt)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.cookup"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.cookup"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    // This is incorrect for modern AGP and Kotlin versions.
    // The Compose compiler is now part of the Kotlin plugin.
    // Remove this block.
    /*
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.get()
    }
    */

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // -------------------------
    // CORE
    // -------------------------
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)


    // -------------------------
    // COMPOSE
    // -------------------------
    implementation(platform(libs.androidx.compose.bom))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // -------------------------
    // NAVIGATION
    // -------------------------
    // Use the alias from your toml file
    implementation(libs.androidx.navigation.compose)

    // -------------------------
    // VIEWMODEL
    // -------------------------
    // Use the alias from your toml file
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // -------------------------
    // COROUTINES
    // -------------------------
    // Use the alias from your toml file
    implementation(libs.kotlinx.coroutines.android)

    // -------------------------
    // ROOM (LOCAL DATABASE)
    // -------------------------
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.room.compiler)

    // -------------------------
    // FIREBASE (AUTH + FIRESTORE ONLY)
    // -------------------------
    // You only need to declare the BOM platform once.
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))
    implementation("com.google.firebase:firebase-auth-ktx")
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.google.android.gms:play-services-auth:21.0.0")
    implementation("androidx.datastore:datastore-preferences:1.1.1")



    // -------------------------
    // TESTING
    // -------------------------
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)

    // REMOVE all these duplicate/conflicting declarations
    // implementation(platform("com.google.firebase:firebase-bom:34.7.0"))
    // implementation("androidx.core:core-ktx:1.9.0")
    // implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    // implementation("androidx.activity:activity-compose:1.7.0")
    // implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    // androidTestImplementation("androidx.test.ext:junit:1.1.5")
    // androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.material:material-icons-extended")
    implementation("io.coil-kt:coil-compose:2.6.0")

}
