import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
  kotlin("multiplatform")
  id("com.android.library")
  id("com.squareup.sqldelight")
}

group = "com.trendingkh"
version = "1.0"

kotlin {
  android()
  ios {
    binaries {
      framework {
        baseName = "shared"
      }
    }
  }

  val sqlDelightVersion: String by project
  val coroutinesVersion = "1.4.3"
  val ktorVersion = "1.5.3"

  sourceSets {
    val commonMain by getting {
      dependencies {
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
        implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
        implementation("com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion")
        implementation("io.ktor:ktor-client-core:$ktorVersion")
        implementation("io.ktor:ktor-client-logging:$ktorVersion")
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
      }
    }
    val androidMain by getting {
      dependencies {
        implementation("com.google.android.material:material:1.3.0")
        implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")
        api("io.ktor:ktor-client-android:$ktorVersion")
      }
    }
    val androidTest by getting {
      dependencies {
        implementation(kotlin("test-junit"))
        implementation("junit:junit:4.13")
      }
    }
    val iosMain by getting {
      dependencies {
        implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
        implementation("io.ktor:ktor-client-ios:$ktorVersion")
      }
    }
    val iosTest by getting
  }
}

android {
  compileSdkVersion(29)
  sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
  defaultConfig {
    minSdkVersion(24)
    targetSdkVersion(29)
  }
}

val packForXcode by tasks.creating(Sync::class) {
  group = "build"
  val mode = System.getenv("CONFIGURATION") ?: "DEBUG"
  val sdkName = System.getenv("SDK_NAME") ?: "iphonesimulator"
  val targetName = "ios" + if (sdkName.startsWith("iphoneos")) "Arm64" else "X64"
  val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)
  inputs.property("mode", mode)
  dependsOn(framework.linkTask)
  val targetDir = File(buildDir, "xcode-frameworks")
  from({ framework.outputDirectory })
  into(targetDir)
}

tasks.getByName("build").dependsOn(packForXcode)

sqldelight {
  database("AppDatabase") {
    packageName = "com.trendingkh.shared.db"
  }
}