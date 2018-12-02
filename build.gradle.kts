plugins {
    id("com.jfrog.bintray").version("1.8.4").apply(false)
    id("org.jetbrains.dokka").version("0.9.17").apply(false)
}

subprojects {
    repositories {
        jcenter()
    }
}
