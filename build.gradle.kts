buildscript {
    repositories {
        gradlePluginPortal()
        jcenter()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
        classpath("com.android.tools.build:gradle:4.1.0")
        classpath("com.squareup.sqldelight:gradle-plugin:1.4.3")
    }
}

group = "dev.carrion.kmmtest"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
