dependencies {
    implementation(project(":money-core"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.5")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.0")

    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.0")
}