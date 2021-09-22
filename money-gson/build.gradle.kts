dependencies {
    implementation(project(":money-core"))

    implementation("com.google.code.gson:gson:2.8.8")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.0")

    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.0")
}