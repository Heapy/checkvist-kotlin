import Versions.kotlinVersion
import Versions.ktorVersion

group = "io.heapy.checkvist.common"

apply(from = "$rootDir/publish.gradle")

plugins {
    kotlin("jvm").version(Versions.kotlinVersion)
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    compile("io.ktor:ktor-client-core-jvm:$ktorVersion")
}
