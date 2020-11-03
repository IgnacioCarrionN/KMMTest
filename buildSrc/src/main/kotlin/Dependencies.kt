object Project {
    const val group = "dev.carrion.kmmtest"
    const val version = "1.0-SNAPSHOT"

    object Android {
        const val applicationId = "dev.carrion.kmmtest.androidApp"
        const val compileSdkVersion = 29
        const val minSdkVersion = 24
        const val targetSdkVersion = 29
        const val versionCode = 1
        const val versionName = "1.0"
    }

    object SqlDelight {
        const val databaseName = "KMMTest"
        const val packageName = "dev.carrion.local"
    }
}

object Modules {
    const val common = ":common"
    const val dataLocal = ":data:local"
    const val dataRemote = ":data:remote"
    const val dataRepository = ":data:repository"
}

object Plugins {
    const val multiplatform = "multiplatform"
    const val androidApplication = "com.android.application"
    const val android = "android"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroidExt = "kotlin-android-extensions"
    const val sqlDelight = "com.squareup.sqldelight"
    const val kotlinSerialization = "plugin.serialization"
}

object CommonDependencies {
    object Kotlin {
        const val coroutines =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"

        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.Kotlin.serialization}"

    }

    object SqlDelight {
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqlDelight}"
        const val coroutinesExt = "com.squareup.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
    }

    object Ktor {
        const val ktorClientCore = "io.ktor:ktor-client-core:${Versions.ktor}"
    }

    object KodeIn {
        const val di = "org.kodein.di:kodein-di:${Versions.kodein}"
    }
}

object CommonTestDependencies {
    object Kotlin {
        const val testCommon = "test-common"
        const val testAnnotations = "test-annotations-common"
    }

    const val mockk = "io.mockk:mockk-common:${Versions.mockk}"
}

object IOSDependencies {
    object Kotlin {
        const val coroutinesNative = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:${Versions.Kotlin.coroutinesNative}"
        const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.coroutines}"
    }

    object SqlDelight {
        const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqlDelight}"
    }
}

object IOSTestDependencies {
    object Kotlin {
        const val testCommon = "test-common"
        const val testAnnotations = "test-annotations-common"
    }
}

object AndroidDependencies {
    const val material = "com.google.android.material:material:${Versions.materialDesign}"

    object Kotlin {
        const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Kotlin.coroutines}"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:${Versions.AndroidX.coreKtx}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintLayout}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.AndroidX.fragmentKtx}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.AndroidX.lifecycle}"
        const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.AndroidX.lifecycle}"
    }

    object SqlDelight {
        const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqlDelight}"
    }

    object Ktor {
        const val androidClient = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val serializationJvm = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
    }

    object KodeIn {
        const val kodeinAndroidx = "org.kodein.di:kodein-di-framework-android-x:${Versions.kodein}"
    }
}

object AndroidTestDependencies {
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
    const val testJunit = "test-junit"
    const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutines}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.AndroidX.coreTesting}"

    const val ktorClientMock = "io.ktor:ktor-client-mock-jvm:${Versions.ktor}"
}