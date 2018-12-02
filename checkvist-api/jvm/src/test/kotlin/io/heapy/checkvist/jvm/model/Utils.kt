package io.heapy.checkvist.jvm.model

fun String.readResource(): String {
    return ClassLoader.getSystemResourceAsStream(this).reader().readText()
}
