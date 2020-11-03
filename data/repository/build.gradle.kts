plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-android-extensions")
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
                baseName = "data:repository"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":common"))
                implementation(project(":data:remote"))
                implementation(project(":data:local"))

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")
                implementation("org.kodein.di:kodein-di:7.1.0")
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.3.2")
                implementation("org.kodein.di:kodein-di:7.1.0")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(AndroidTestDependencies.mockk)
                implementation(kotlin("test-junit"))
                implementation("io.ktor:ktor-client-mock-jvm:1.4.1")
            }
        }
        val iosMain by getting
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
}