plugins {
    `maven-publish`
}

group = "io.github.vichid"
version = "0.0.1"

buildscript {
    dependencies {
        classpath(libs.gradlePlugins.anvil)
        classpath(libs.gradlePlugins.detekt)
        classpath(libs.gradlePlugins.kotlin)
    }

    repositories {
        google()
        maven("https://plugins.gradle.org/m2/")
        maven("https://maven.pkg.github.com/vichid/playground") {
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN_PUBLISH")
            }
        }
    }
}
apply(plugin = "org.jetbrains.kotlin.jvm")

val implementation by configurations

dependencies {
    implementation(libs.detekt.api)
}

configure<PublishingExtension> {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/vichid/playground")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN_PUBLISH")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])
        }
    }
}
