plugins {
    id("maven-publish")
    kotlin("jvm") version "1.7.10"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/vichid/detekt-rules")
            credentials {
                username = System.getenv("GITHUB_ACTOR")
                password = System.getenv("GITHUB_TOKEN_PUBLISH")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            groupId = "io.github.vichid"
            artifactId = "detekt.rules"
            version = System.getenv("GITHUB_REF_NAME")

            from(components["java"])
        }
    }
}

dependencies {
    implementation(libs.detekt.api)
}
