// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApp) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.ksp) apply false

    id("androidx.navigation.safeargs.kotlin") version "2.7.6" apply false
//    id ("org.jetbrains.kotlin.plugin.serialization") version "1.8.0" apply false
}
true


