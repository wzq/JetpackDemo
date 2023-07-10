plugins {
    id("org.jetbrains.kotlin.jvm")
}

sourceSets.main {
    kotlin.srcDir("src/main/kotlin")
    java.srcDir("src/main/kotlin")
    resources.srcDir("src/main/resources")
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.8.20-1.0.11")
}