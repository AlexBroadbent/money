import org.gradle.api.tasks.testing.logging.TestLogEvent

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.0")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.0")
}

tasks.compileTestKotlin {
    sourceCompatibility = JavaVersion.VERSION_11.toString()
    targetCompatibility = JavaVersion.VERSION_11.toString()
    kotlinOptions.jvmTarget = JavaVersion.VERSION_11.toString()
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events(TestLogEvent.STARTED, TestLogEvent.PASSED, TestLogEvent.FAILED)
    }
}