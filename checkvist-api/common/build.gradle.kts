import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion

group = "io.heapy.checkvist.common"

apply(from = "$rootDir/publish.gradle")

plugins {
    kotlin("jvm").version("1.3.10")
}

val ktorVersion: String by project

dependencies {
    compileOnly(kotlin("stdlib-jdk8", getKotlinPluginVersion()))

    compile("io.ktor:ktor-client-core-jvm:$ktorVersion")
}
