dependencies {
    implementation(project(":money-core"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.3") {
        implementation("org.jetbrains.kotlin:kotlin-reflect:1.7.0")
    }
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3")
}
