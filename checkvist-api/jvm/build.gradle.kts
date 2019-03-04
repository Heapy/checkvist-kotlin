import Versions.junitVersion
import Versions.kotlinVersion
import Versions.ktorVersion

group = "io.heapy.checkvist.jvm"

apply(from = "$rootDir/publish.gradle")

plugins {
    kotlin("jvm").version(Versions.kotlinVersion)
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlinVersion")

    compile(project(":checkvist-api:common"))

    compile("io.ktor:ktor-client-apache:$ktorVersion")
    compile("io.ktor:ktor-client-jackson:$ktorVersion") {
        exclude(group = "org.jetbrains.kotlinx")
    }

    testCompile("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}
