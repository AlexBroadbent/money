dependencies {
    implementation(project(":money-core"))

    implementation("com.fasterxml.jackson.core:jackson-databind:2.12.5") {
        implementation("org.jetbrains.kotlin:kotlin-reflect:1.5.30")
    }
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.5")
}
