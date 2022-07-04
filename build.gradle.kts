plugins {
    kotlin("jvm") version "1.7.0"
    id("org.jlleitschuh.gradle.ktlint") version "10.2.0"
    id("org.jetbrains.dokka") version "1.7.0"
    `maven-publish`
    signing
}

repositories {
    mavenCentral()
}

dependencies {
    subprojects.forEach { api(project(it.path)) }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jlleitschuh.gradle.ktlint")
    apply(plugin = "org.jetbrains.dokka")
    apply(plugin = "org.gradle.maven-publish")
    apply(plugin = "org.gradle.signing")

    group = "com.abroadbent"
    version = "0.3.0"

    repositories {
        mavenCentral()
    }

    dependencies {
        implementation(kotlin("stdlib", version = "1.7.0"))

        dokkaHtmlPlugin("org.jetbrains.dokka:kotlin-as-java-plugin:1.7.0")

        testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
        testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.0")

        runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.0")
    }

    java {
        withJavadocJar()
        withSourcesJar()
    }

    tasks {
        compileKotlin {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
        }

        compileTestKotlin {
            kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
        }

        test {
            useJUnitPlatform()
        }

        artifacts {
            archives(kotlinSourcesJar)
            archives(jar)
        }
    }

    publishing {
        publications {
            create<MavenPublication>("maven") {
                group = this@subprojects.group
                artifactId = this@subprojects.name
                version = this@subprojects.version as String

                from(components["java"])

                pom {
                    name.set(this@subprojects.name)
                    description.set("Money is a simple utility library for storing monetary values and handling serialization and localization")
                    packaging = "jar"
                    url.set("https://github.com/AlexBroadbent/money")
                    licenses {
                        license {
                            name.set("The Apache License, Version 2.0")
                            url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        }
                    }
                    developers {
                        developer {
                            id.set("AlexBroadbent")
                            name.set("Alexander Broadbent")
                            email.set("AlexBroadbent14@gmail.com")
                        }
                    }
                    scm {
                        connection.set("scm:git:ssh://github.com/AlexBroadbent/money.git")
                        developerConnection.set("scm:git:ssh://github.com/AlexBroadbent/money.git")
                        url.set("https://github.com/AlexBroadbent/money")
                    }
                }
            }
        }

        repositories {
            maven {
                credentials {
                    username = System.getenv("NEXUS_USERNAME") ?: findProperty("NEXUS_USERNAME") as? String
                    password = System.getenv("NEXUS_PASSWORD") ?: findProperty("NEXUS_PASSWORD") as? String
                }
                name = "money"
                setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            }
        }
    }

    signing {
        sign(publishing.publications)
    }
}
