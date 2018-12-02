package io.heapy.checkvist.jvm

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategy

/**
 * Configuration of [ObjectMapper].
 *
 * @author Ruslan Ibragimov
 * @since 1.0.0
 */
fun ObjectMapper.configureCheckvist(): ObjectMapper {
    configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    propertyNamingStrategy = PropertyNamingStrategy.SNAKE_CASE
    return this
}
