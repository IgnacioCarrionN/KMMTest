plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
    id(Plugins.kotlinAndroidExt)
}
group = Project.group
version = Project.version

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
    implementation(CommonDependencies.KodeIn.di)
    implementation(AndroidDependencies.KodeIn.kodeinAndroidx)
}
android {
    compileSdkVersion(Project.Android.compileSdkVersion)
    defaultConfig {
        applicationId = Project.Android.applicationId
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}