import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

group = "io.heapy.checkvist.jvm"

apply(from = "$rootDir/publish.gradle")

plugins {
    kotlin("jvm").version("1.3.10")
}

val ktorVersion: String by project
val junitVersion: String by project

dependencies {
    compileOnly(kotlin("stdlib-jdk8", getKotlinPluginVersion()))

    compile(project(":checkvist-api:common"))

    compile("io.ktor:ktor-client-apache:$ktorVersion")
    compile("io.ktor:ktor-client-jackson:$ktorVersion") {
        exclude(group = "org.jetbrains.kotlinx")

    }
    testCompile("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}
