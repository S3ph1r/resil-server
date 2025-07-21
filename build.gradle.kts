val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.22"
    id("io.ktor.plugin") version "2.3.9"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {
    // Blocco le versioni di Ktor a 2.3.9
    implementation("io.ktor:ktor-server-core-jvm:2.3.9")
    implementation("io.ktor:ktor-server-netty-jvm:2.3.9")
    implementation("io.ktor:ktor-server-content-negotiation-jvm:2.3.9")
    implementation("io.ktor:ktor-serialization-gson-jvm:2.3.9")
    implementation("io.ktor:ktor-server-call-logging-jvm:2.3.9")
    implementation("ch.qos.logback:logback-classic:1.4.14")
}
