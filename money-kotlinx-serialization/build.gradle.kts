dependencies {
    implementation(project(":money-core"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.2.2")

    testImplementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.0")

    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.0")
}