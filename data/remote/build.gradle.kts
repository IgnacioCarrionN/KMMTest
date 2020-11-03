plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlin-android-extensions")
    kotlin("plugin.serialization") version "1.4.10"
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
                baseName = "data:remote"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-core:1.4.1")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")

                implementation("org.kodein.di:kodein-di:7.1.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation("io.mockk:mockk-common:1.10.0")
                //implementation("io.ktor:ktor-client-mock:1.4.1")

                implementation(project(Modules.common))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.core:core-ktx:1.3.2")

                implementation("io.ktor:ktor-client-android:1.4.1")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.0")
                implementation("io.ktor:ktor-client-serialization-jvm:1.4.1")

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

