plugins {
    kotlin(Plugins.multiplatform)
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroidExt)
    id(Plugins.sqlDelight)
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
                implementation(project(Modules.common))

                implementation(CommonDependencies.Kotlin.coroutines)

                implementation(CommonDependencies.SqlDelight.runtime)
                implementation(CommonDependencies.SqlDelight.coroutinesExt)

                implementation(CommonDependencies.KodeIn.di)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(project(Modules.common))

                implementation(kotlin(CommonTestDependencies.Kotlin.testCommon))
                implementation(kotlin(CommonTestDependencies.Kotlin.testAnnotations))
                implementation(CommonTestDependencies.mockk)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(AndroidDependencies.AndroidX.coreKtx)
                implementation(AndroidDependencies.SqlDelight.androidDriver)

                implementation(CommonDependencies.KodeIn.di)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(AndroidTestDependencies.mockk)
                implementation(kotlin(AndroidTestDependencies.testJunit))
                implementation(AndroidTestDependencies.coroutinesTest)
                implementation(AndroidTestDependencies.coreTesting)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(IOSDependencies.SqlDelight.nativeDriver)
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

sqldelight {
    database(Project.SqlDelight.databaseName) { // This will be the name of the generated database class.
        packageName = Project.SqlDelight.packageName
    }
}