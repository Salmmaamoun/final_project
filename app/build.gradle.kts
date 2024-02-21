plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    buildFeatures {
        dataBinding = true
    }
    namespace = "com.example.graduation_project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.graduation_project"
        minSdk = 24
        targetSdk = 33
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

    //meow
    implementation("com.etebarian:meow-bottom-navigation:1.2.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61")
   //ssp sdp
    implementation ("com.intuit.ssp:ssp-android:1.0.6")
    implementation ("com.intuit.sdp:sdp-android:1.0.6")
    implementation(project(":domain"))
    implementation(project(":data"))
    //view model
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")

    implementation ("androidx.fragment:fragment-ktx:1.3.2")
    implementation ("androidx.activity:activity-ktx:1.3.1")

    //retrofit
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    implementation("androidx.viewpager2:viewpager2:1.0.0")
    
    implementation ("androidx.room:room-runtime:2.4.0")
    implementation ("androidx.room:room-ktx:2.4.0")
    kapt ("androidx.room:room-compiler:2.4.0")

    // Retrofit for networking

    // Gson for JSON serialization/deserialization
    implementation ("com.google.code.gson:gson:2.8.7")
    // OkHttp for network logging
    implementation ("com.squareup.okhttp3:logging-interceptor:4.9.1")



}