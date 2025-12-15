plugins {
    kotlin("jvm") version "2.2.21"
    kotlin("kapt") version "2.2.21"
}

group = "me.mikinol.hostnamegate"
version = "0.6"

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.10-R0.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(21)
}

tasks.withType<ProcessResources> {
    inputs.property("version", project.version)

    filesMatching("paper-plugin.yml") {
        expand("version" to project.version)
    }
}
