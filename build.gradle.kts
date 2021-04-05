buildscript {
  repositories {
    gradlePluginPortal()
    jcenter()
    google()
    mavenCentral()
  }

  val hiltVersion = "2.33-beta"
  val kotlinVersion = "1.4.32"
  val sqlDelightVersion: String by project


  dependencies {
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    classpath("com.android.tools.build:gradle:4.0.1")
    classpath("com.squareup.sqldelight:gradle-plugin:$sqlDelightVersion")
    classpath("com.google.dagger:hilt-android-gradle-plugin:$hiltVersion")
  }
}

group = "com.trendingkh"
version = "1.0"

allprojects {
  repositories {
    google()
    jcenter()
    mavenCentral()
  }
}