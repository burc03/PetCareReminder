plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.petcarereminder"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.petcarereminder"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    // 🔹 ANDROIDX
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)

    // 🔹 ROOM (LOCAL DATABASE)  🔥🔥🔥
    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)

    // 🔹 TEST
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
