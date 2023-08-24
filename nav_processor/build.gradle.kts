@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

//sourceSets.main {
//    kotlin.srcDir("src/main/kotlin")
//    resources.srcDir("src/main/resources")
//}

//tasks.withType<Copy> {
//    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
//}

dependencies {
//    implementation(kotlin("stdlib"))
//    implementation("com.squareup:javapoet:1.12.1")
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.0-1.0.13")
}