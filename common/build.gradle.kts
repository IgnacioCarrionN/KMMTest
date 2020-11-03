import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    kotlin(Plugins.multiplatform)
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroidExt)
}
group = Project.group
version = Project.version

repositories {
    gradlePluginPortal()
    google()
    jcenter()
    mavenCentral()
}
kotlin {
    android()
    ios {
        binaries {
            framework {
                baseName = "common"
            }
        }
    }
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(CommonDependencies.Kotlin.coroutines)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin(CommonTestDependencies.Kotlin.testCommon))
                implementation(kotlin(CommonTestDependencies.Kotlin.testAnnotations))
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(IOSDependencies.Kotlin.coroutinesNative)
                implementation(IOSDependencies.Kotlin.coroutinesCore)
            }
        }
        val iosTest by getting {
            dependencies {
                implementation(kotlin(CommonTestDependencies.Kotlin.testCommon))
                implementation(kotlin(CommonTestDependencies.Kotlin.testAnnotations))
            }
        }
    }
}
android {
    compileSdkVersion(Project.Android.compileSdkVersion)
    sourceSets["main"].manifest.srcFile("src\\androidMain\\AndroidManifest.xml")
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