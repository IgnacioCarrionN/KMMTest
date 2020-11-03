plugins {
    kotlin(Plugins.multiplatform)
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroidExt)
    kotlin(Plugins.kotlinSerialization) version "1.4.10"
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
                implementation(CommonDependencies.Ktor.ktorClientCore)
                implementation(CommonDependencies.Kotlin.serialization)

                implementation(CommonDependencies.KodeIn.di)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin(CommonTestDependencies.Kotlin.testCommon))
                implementation(kotlin(CommonTestDependencies.Kotlin.testAnnotations))
                implementation(CommonTestDependencies.mockk)

                implementation(project(Modules.common))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(AndroidDependencies.AndroidX.coreKtx)

                implementation(AndroidDependencies.Ktor.androidClient)
                implementation(AndroidDependencies.Kotlin.coroutinesAndroid)
                implementation(AndroidDependencies.Ktor.serializationJvm)

            }
        }
        val androidTest by getting {
            dependencies {
                implementation(AndroidTestDependencies.mockk)
                implementation(kotlin(AndroidTestDependencies.testJunit))
                implementation(AndroidTestDependencies.ktorClientMock)
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
    compileSdkVersion(Project.Android.compileSdkVersion)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(Project.Android.minSdkVersion)
        targetSdkVersion(Project.Android.targetSdkVersion)
        versionCode = Project.Android.versionCode
        versionName = Project.Android.versionName
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

