plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "1.8.22"

}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-client-core:2.3.4") // Core do Ktor Client
    implementation("io.ktor:ktor-client-cio:2.3.4")  // Motor de rede CIO (assíncrono)
    implementation("io.ktor:ktor-client-content-negotiation:2.3.4") // Negociação de conteúdo
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4") // Serialização JSON
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}