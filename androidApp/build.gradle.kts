plugins {
  id("com.android.application")
  kotlin("android")
  id("kotlin-kapt")
  id("dagger.hilt.android.plugin")
}

group = "com.trendingkh"
version = "1.0"

dependencies {
  implementation(project(":shared"))
  implementation("androidx.appcompat:appcompat:1.2.0")
  implementation("androidx.core:core-ktx:1.3.2")
  implementation("com.google.android.material:material:1.3.0")
  implementation("androidx.constraintlayout:constraintlayout:2.0.4")
  implementation("androidx.legacy:legacy-support-v4:1.0.0")

  val fragmentVersion = "1.2.5"
  implementation("androidx.fragment:fragment-ktx:$fragmentVersion")
  val activityVersion = "1.2.2"
  implementation("androidx.activity:activity-ktx:$activityVersion")
  val lifecycleVersion = "2.3.1"
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
  implementation("androidx.lifecycle:lifecycle-common-java8:$lifecycleVersion")
  implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

  val hiltVersion = "2.33-beta"
  implementation("com.google.dagger:hilt-android:$hiltVersion")
  kapt("com.google.dagger:hilt-compiler:$hiltVersion")
}

android {
  compileSdkVersion(29)
  defaultConfig {
    applicationId = "com.trendingkh.khdict.kmm.android"
    minSdkVersion(24)
    targetSdkVersion(29)
    versionCode = 1
    versionName = "1.0"
  }
  buildTypes {
    getByName("release") {
      isMinifyEnabled = false
    }
  }
  buildFeatures {
    viewBinding = true
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
}

kapt {
  correctErrorTypes = true
}