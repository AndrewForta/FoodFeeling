
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
    implementation(project(":feeling:feeling-datasource"))
    implementation(project(":feeling:feeling-domain"))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2")
}