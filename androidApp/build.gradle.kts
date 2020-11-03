plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android-extensions")
}
group = "dev.carrion.kmmtest"
version = "1.0-SNAPSHOT"

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
    maven(url = "https://dl.bintray.com/kodein-framework/kodein-dev")
}
dependencies {
    implementation(project(Modules.common))
    implementation(project(Modules.dataRepository))
    implementation(project(Modules.dataLocal))
    implementation(project(Modules.dataRemote))

    implementation(AndroidDependencies.Kotlin.coroutinesAndroid)

    implementation(AndroidDependencies.material)

    implementation(AndroidDependencies.AndroidX.appCompat)
    implementation(AndroidDependencies.AndroidX.constraintLayout)
    implementation(AndroidDependencies.AndroidX.fragmentKtx)
    implementation(AndroidDependencies.AndroidX.viewModelKtx)
    implementation(AndroidDependencies.AndroidX.liveDataKtx)
    implementation("org.kodein.di:kodein-di:7.1.0")
    implementation("org.kodein.di:kodein-di-framework-android-x:7.1.0")
}
android {
    compileSdkVersion(29)
    defaultConfig {
        applicationId = "dev.carrion.kmmtest.androidApp"
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

    kotlinOptions {
        jvmTarget = "1.8"
    }
}