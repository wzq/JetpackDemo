plugins {
   kotlin("jvm")
}

sourceSets.main {
    kotlin.srcDir("src/main/kotlin")
    resources.srcDir("src/main/resources")
}

tasks.withType<Copy> {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}


dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.8.20-1.0.11")
}