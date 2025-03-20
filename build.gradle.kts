// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false

//    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false

//    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false

}

    buildscript {

        repositories {
            google()
            mavenCentral()
        }
        dependencies {
            classpath ("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.1")
        }
    }

