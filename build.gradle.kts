import kotlin.collections.listOf

plugins {
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.6.10"
    id("org.openjfx.javafxplugin") version "0.0.9"
    application
}

javafx {
    version = "16"
    modules = listOf("javafx.controls", "javafx.graphics", "javafx.fxml")
}

repositories {
    mavenCentral()
}

dependencies {
    // Use Kotlin standard library
    implementation(kotlin("stdlib-jdk8"))

    // Use Kotlinx serialization library
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")

    // User Kotlinx date and time library
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.3.2")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:31.0.1-jre")
    implementation("no.tornado:tornadofx:1.7.20")

    // Use the Kotlin test library.
    testImplementation(kotlin("test"))

    // Use the Kotlin JUnit integration.
    testImplementation(kotlin("test-junit"))
}

application {
    // Define the main class for the application.
    mainClass.set("moe.kadosawa.task7.AppKt")
}
