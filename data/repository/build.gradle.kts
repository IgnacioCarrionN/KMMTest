plugins {
    kotlin(Plugins.multiplatform)
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroidExt)
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
                implementation(project(Modules.common))
                implementation(project(Modules.dataRemote))
                implementation(project(Modules.dataLocal))

                implementation(CommonDependencies.Kotlin.coroutines)
                implementation(CommonDependencies.KodeIn.di)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(AndroidDependencies.AndroidX.coreKtx)
                implementation(CommonDependencies.KodeIn.di)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(AndroidTestDependencies.mockk)
                implementation(kotlin(AndroidTestDependencies.testJunit))
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