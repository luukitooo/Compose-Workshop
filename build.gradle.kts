buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Safe Args
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.7.7")

        // Hilt
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.51.1")
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.android.library) apply false
}