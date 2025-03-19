@file:Suppress("UnstableApiUsage")

import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
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
    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.0"
    `maven-publish`
    java

    alias(libs.plugins.grgit)
    alias(libs.plugins.fabric.loom)
    alias(libs.plugins.dokka)
    alias(libs.plugins.shadow) apply false
}

val shade: Configuration by configurations.creating { }
val archivesBaseName = project.property("archives_base_name").toString()
version = getModVersion()
group = project.property("maven_group")!!

/*
*
* Taken from Deftu's Gradle-Toolkit with permission, and have explicit permission from Deftu himself to be excluded from Deftu's Gradle-Toolkit's LGPLv3 license
*
* src: https://github.com/Deftu/Gradle-Toolkit/blob/main/src/main/kotlin/dev/deftu/gradle/tools/shadow.gradle.kts
*
* lines affected: 27, 42-96
*
*/

val fatJar = tasks.register<ShadowJar>("fatJar") {
    group = "innerpastels"
    description = "Builds a fat JAR with all dependencies shaded in"
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    configurations = listOf(shade)
    archiveVersion.set(project.version.toString())
    archiveClassifier.set("all")

    val javaPlugin = project.extensions.getByType(JavaPluginExtension::class.java)
    val jarTask = project.tasks.getByName("jar") as Jar

    manifest.inheritFrom(jarTask.manifest)
    val libsProvider = project.provider { listOf(jarTask.manifest.attributes["Class-Path"]) }
    val files = project.objects.fileCollection().from(shade)
    doFirst {
        if (!files.isEmpty) {
            val libs = libsProvider.get().toMutableList()
            libs.addAll(files.map { it.name })
            manifest.attributes(mapOf("Class-Path" to libs.filterNotNull().joinToString(" ")))
        }
    }

    from(javaPlugin.sourceSets.getByName("main").output)
    exclude("META-INF/INDEX.LIST", "META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA", "module-info.class")
}

project.artifacts.add("shade", fatJar)

pluginManager.withPlugin("java") {
    tasks["assemble"].dependsOn(fatJar)
}

tasks {
    val shadowJar = findByName("shadowJar")
    if (shadowJar != null) {
        named("shadowJar") {
            doFirst {
                throw GradleException("Incorrect task! You're looking for fatJar.")
            }
        }
    }
}

loom {
    tasks {
        fatJar {
            archiveClassifier.set("dev")
        }

        remapJar {
            inputFile.set(fatJar.get().archiveFile)
            archiveClassifier.set("")
        }
    }
}

repositories {
    mavenCentral()
    maven("https://maven.parchmentmc.org")
    maven("https://mvn.devos.one/snapshots")
    maven { url = uri("https://api.modrinth.com/maven") }

}

//All dependencies and their versions are in ./gradle/libs.versions.toml
dependencies {

    minecraft(libs.minecraft)

    mappings(loom.layered {
        officialMojangMappings()
        parchment("org.parchmentmc.data:parchment-1.21:2024.07.28@zip")
    })

    //Fabric
    modImplementation(libs.fabric.loader)
    modImplementation(libs.fabric.api)
    modImplementation(libs.fabric.language.kotlin) // how did i not have this
    modImplementation(libs.sodium)
    include(implementation("com.moulberry:mixinconstraints:1.0.7")!!)

    listOf(
        "binding" to true,
        "lwjgl3" to true,
        "natives-windows" to false,
        "natives-linux" to false,
        "natives-macos" to false
    ).forEach { (module, bundled) ->
        val version = "1.87.1"
        api("io.github.spair:imgui-java-$module:$version") {
            exclude(group = "org.lwjgl")
        }
    }

    include(modApi("gay.asoji:fmw:1.0.0+build.8")!!)
    
    include(implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")!!)
}

tasks.withType<DokkaTask>().configureEach {
    dokkaSourceSets {
        named("main") {
            moduleName.set("Inner Pastels")
            moduleVersion.set(project.version.toString())

            includes.from("Module.md")

            sourceLink {
                localDirectory.set(file("src/main/kotlin"))
                remoteUrl.set(URL("http://github.com/devOS-Sanity-Edition/InnerPastels/tree/kt/1.21/main/" + "src/main/kotlin"))
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
        create("testModClient") {
            client()
            name("Test Mod Client")
            source(sourceSets.getByName("test"))
            runDir("run/test")
        }
        create("testModServer") {
            server()
            name("Test Mod Server")
            source(sourceSets.getByName("test"))
            runDir("run/test_server")
        }
        create("Gametest") {
            server()
            name("Test")
            source(sourceSets.getByName("test"))
            vmArgs("-Dfabric-api.gametest")
            vmArgs("-Dfabric-api.gametest.report-file=${project.buildDir}/junit.xml")
            runDir("run/gametest_server")
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
        return "${modVersion}+rev.${id}-branch.${branch}"
    }

    // No tracking information could be found about the build
    return "${modVersion}+unknown"
}

