plugins {
    kotlin("jvm") version "1.5.30"
}

repositories {
    mavenCentral()
}

dependencies {
    subprojects.forEach { implementation(project(it.path)) }
}

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "uk.co.alexbroadbent"
    version = "0.1.0"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib", version = "1.5.30"))
    }

    tasks.compileKotlin {
        sourceCompatibility = JavaVersion.VERSION_11.toString()
        targetCompatibility = JavaVersion.VERSION_11.toString()
        kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
    }

    tasks.test {
        useJUnitPlatform()
    }
}