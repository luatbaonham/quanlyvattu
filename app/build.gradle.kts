plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.fe_quanlyvattu"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.fe_quanlyvattu"
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation ("com.google.android.material:material:1.11.0")
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation ("com.amazonaws:aws-android-sdk-s3:2.72.0")
    implementation ("com.amazonaws:aws-android-sdk-core:2.72.0")
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.16.0")
    implementation ("com.amazonaws:aws-android-sdk-s3:2.16.+")
    implementation("com.google.android.material:material:1.11.0") // hoặc phiên bản mới nhất
    implementation("com.android.volley:volley:1.2.1")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.security:security-crypto:1.1.0-alpha03")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}