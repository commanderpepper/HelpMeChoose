plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.kapt")
}

android {
    namespace = "commanderpepper.helpmechoose.database"
    compileSdk = 33

    defaultConfig {
        minSdk = 21
        targetSdk = 33

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles (getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

    // Kotlin Extensions and Coroutines support for Room
    implementation ("androidx.room:room-ktx:2.4.3")
    implementation ("androidx.room:room-runtime:2.4.3")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    annotationProcessor ("androidx.room:room-compiler:2.4.3")
    kapt ("androidx.room:room-compiler:2.4.3")

    // Test libraries
    testImplementation ("junit:junit:4.13.2")
    testImplementation ("androidx.room:room-testing:2.4.3")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    // Android Test libraries
    androidTestImplementation ("androidx.test.ext:junit:1.1.4")
    androidTestImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
    androidTestImplementation ("androidx.test:runner:1.5.1")
    androidTestImplementation ("androidx.room:room-testing:2.4.3")

    // Turbine for Flow testing
    testImplementation ("app.cash.turbine:turbine:0.9.0")
    androidTestImplementation ("app.cash.turbine:turbine:0.9.0")
}