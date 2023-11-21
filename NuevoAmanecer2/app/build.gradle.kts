plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.nuevo.nuevoamanecer"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nuevo.nuevoamanecer"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Firebase dependencies
    implementation ("com.google.firebase:firebase-analytics-ktx:20.1.2") // Example version, replace with the latest
    implementation("com.google.firebase:firebase-database-ktx:20.0.3") // Realtime Database

    // Glide for image loading
    implementation("com.github.bumptech.glide:glide:4.12.0")

    // Kotlin extensions for Realtime Database
    implementation("com.google.firebase:firebase-database-ktx")

    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    // Gson Converter for Retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.google.firebase:firebase-storage-ktx:20.0.0") // Storage
    implementation ("com.squareup.okhttp3:okhttp:4.9.0")

}

// Make sure to apply the Google services plugin at the bottom
