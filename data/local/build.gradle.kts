plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-android-extensions")
    id("com.squareup.sqldelight")
}

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
    maven {
        url = uri("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}
kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "data:local"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common"))

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")

                implementation("com.squareup.sqldelight:runtime:1.4.3")
                implementation("com.squareup.sqldelight:coroutines-extensions:1.4.3")

                implementation("org.kodein.di:kodein-di:7.1.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(project(":common"))

                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("io.mockk:mockk-common:1.10.0")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.3.2")
                implementation("com.squareup.sqldelight:android-driver:1.4.3")

                implementation("org.kodein.di:kodein-di:7.1.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation("io.mockk:mockk:1.10.0")
                implementation(kotlin("test-junit"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.4.0")
                implementation("androidx.arch.core:core-testing:2.1.0")
            }
        }
        val iosMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.4.3")
            }
        }
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

android {
    compileSdkVersion(29)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    database("KMMTest") { // This will be the name of the generated database class.
        packageName = "dev.carrion.local"
    }
}