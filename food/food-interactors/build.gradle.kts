
plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies {
    implementation(project(":core"))
    implementation(project(":food:food-datasource"))
    implementation(project(":food:food-domain"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
}