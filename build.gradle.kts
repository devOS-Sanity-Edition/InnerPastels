@file:Suppress("UnstableApiUsage")

import org.jetbrains.dokka.base.DokkaBase
import org.jetbrains.dokka.base.DokkaBaseConfiguration
import org.jetbrains.dokka.gradle.DokkaTask
import java.net.URL


buildscript {
    dependencies {
        classpath("org.jetbrains.dokka:dokka-base:1.9.10")
    }
}

plugins {
    kotlin("jvm") version "2.0.0"
    `maven-publish`
    java

    alias(libs.plugins.grgit)
    alias(libs.plugins.fabric.loom)
    alias(libs.plugins.dokka)
}

val archivesBaseName = project.property("archives_base_name").toString()
version = getModVersion()
group = project.property("maven_group")!!

repositories {
    mavenCentral()
    maven { url = uri("https://maven.parchmentmc.org") }
    maven { url = uri("https://mvn.devos.one/snapshots") } }

//All dependencies and their versions are in ./gradle/libs.versions.toml
dependencies {

    minecraft(libs.minecraft)

    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-1.21:2024.06.23@zip")
    })

    //Fabric
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)
    modImplementation(libs.fabric.language.kotlin) // how did i not have this

    implementation(libs.bundles.imgui) {
        exclude(group = "org.lwjgl")
    }

    include(modImplementation("gay.asoji:fmw:1.0.0+build.8")!!)
}

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        named("main") {
            moduleName.set("Inner Pastels")
            moduleVersion.set(project.version.toString())

            includes.from("Module.md")

            sourceLink {
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl.set(URL("http://github.com/devOS-Sanity-Edition/InnerPastels/tree/kt/1.20.5/main/" + "src/main/kotlin"))
                remoteLineSuffix.set("#L")
            }
        }
    }
    pluginConfiguration<DokkaBase, DokkaBaseConfiguration> {
//		customAssets = listOf(file("my-image.png"))
//		customStyleSheets = listOf(file("my-styles.css"))
        footerMessage = "(c) 2024 devOS: Sanity Edition, Team Nautical, asoji"
        separateInheritedMembers = true

//		templatesDir = file("dokka/templates")
        mergeImplicitExpectActualDeclarations = true
    }
}

// Write the version to the fabric.mod.json
tasks.processResources {
    inputs.property("version", project.version)

    filesMatching("fabric.mod.json") {
        expand(mutableMapOf("version" to project.version))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(21)
}

java {
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.jar {
    from("LICENSE") {
        rename { "${it}_${project.base.archivesName.get()}" }
    }
}

// This will attempt to publish the mod to the devOS Maven, otherwise it will build the mod locally
// This is auto run by GitHub Actions
task("buildOrPublish") {
    group = "build"
    var mavenUser = System.getenv().get("MAVEN_USER")
    if (!mavenUser.isNullOrEmpty()) {
        dependsOn(tasks.getByName("publish"))
        println("prepared for publish")
    } else {
        dependsOn(tasks.getByName("build"))
        println("prepared for build")
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.property("maven_group").toString()
            artifactId = project.property("archives_base_name").toString()
            version = getModVersion()

            from(components.get("java"))
        }
    }

    repositories {
        maven {
            url = uri("https://mvn.devos.one/${System.getenv()["PUBLISH_SUFFIX"]}/")
            credentials {
                username = System.getenv()["MAVEN_USER"]
                password = System.getenv()["MAVEN_PASS"]
            }
        }
    }
}

loom {
    runs {
        create("datagen") {
            client()
            name("Data Generation")
            vmArgs(
                "-Dfabric-api.datagen",
                "-Dfabric-api.datagen.output-dir=${file("src/main/generated")}",
                "-Dfabric-api.datagen.modid=${project.extra["archives_base_name"] as String}"
            )
            runDir("build/datagen")
        }
    }
}

sourceSets {
    main {
        resources {
            srcDirs("src/main/generated")
            exclude("src/main/generated/.cache")
        }
    }
}

fun getModVersion(): String {
    val modVersion = project.property("mod_version")
    val buildId = System.getenv("GITHUB_RUN_NUMBER")
    val branch = grgit.branch.current.name.replace("/", ".")

    // If a git repo can't be found, grgit won't work, this non-null check exists so you don't run grgit stuff without a git repo
    if (grgit != null) {
        val head = grgit.head()
        var id = head.abbreviatedId

        // Flag the build if the build tree is not clean
        // (aka you have uncommitted changes)
        if (!grgit.status().isClean()) {
            id += "-dirty"
            if (buildId != null) {
                id = id.replace("-dirty", "")
            } else {
                id += ""
            }
        }

        // ex: 1.0.0+rev.91949fa or 1.0.0+rev.91949fa-dirty
        return "${modVersion}+rev.${id}+branch.${branch}"
    }

    // No tracking information could be found about the build
    return "${modVersion}+unknown"

}