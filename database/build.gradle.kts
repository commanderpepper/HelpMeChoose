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
    // Modules
    implementation(":uimodel")
//    implementation(project(mapOf("path" to ":uimodel")))

    // Room
    implementation(libs.room.ktx)
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)

    // Coroutines
    implementation(libs.coroutines.core)

    // Koin
    implementation(libs.koin.core)
    implementation(libs.koin.android)

    // Test libraries
    testImplementation(libs.junit.junit)
    testImplementation(libs.room.testing)
    testImplementation(libs.coroutines.test)

    // Android Test libraries
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.androidx.test.runner)
    androidTestImplementation(libs.room.testing)
    androidTestImplementation(libs.room.testing)
    androidTestImplementation(libs.coroutines.test)

    // Turbine for Flow testing
    testImplementation (libs.turbine.turbine)
    androidTestImplementation (libs.turbine.turbine)
}