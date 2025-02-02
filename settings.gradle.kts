include(":data:repository")
include(":data:local")
include(":data:remote")
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        jcenter()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.namespace == "com.android" || requested.id.name == "kotlin-android-extensions") {
                useModule("com.android.tools.build:gradle:4.0.1")
            }
        }
    }
}
rootProject.name = "KMMTest"


include(":androidApp")
include(":common")

enableFeaturePreview("GRADLE_METADATA")