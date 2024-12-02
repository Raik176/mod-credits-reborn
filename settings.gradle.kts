pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev")
        maven("https://maven.minecraftforge.net")
        maven("https://maven.neoforged.net/releases/")
        maven("https://maven.kikugie.dev/snapshots")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.5-beta.5"
}

stonecutter {
    centralScript = "build.gradle.kts"
    kotlinController = true
    create(rootProject) { // only have 1.20.6 cause of neoforge
        versions("1.15", "1.17.1", "1.18", "1.19", "1.20.6")
        vcsVersion = "1.20.6"

        branch("fabric")
        branch("forge")
        branch("neoforge") { versions("1.20.6") }
    }
}

rootProject.name = "Modcredits Reborn"