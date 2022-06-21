import com.squareup.sqldelight.gradle.SqlDelightDatabase

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    kotlin("plugin.serialization") version "1.6.10"
    id("com.squareup.sqldelight")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(project(":history:history-domain"))
    implementation("io.ktor:ktor-client-android:1.5.0")
    implementation("io.ktor:ktor-client-serialization:1.5.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1")
    implementation("io.ktor:ktor-client-logging-jvm:1.5.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")

    implementation("com.squareup.sqldelight:runtime:1.5.1")
}

sqldelight {
    database("HistoryDatabase") {
        packageName = "com.soringpenguin.history_datasource.cache"
        sourceFolders = listOf("sqldelight")
    }
}