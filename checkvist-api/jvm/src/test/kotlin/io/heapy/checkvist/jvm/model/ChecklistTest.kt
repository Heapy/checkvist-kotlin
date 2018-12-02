package io.heapy.checkvist.jvm.model

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.heapy.checkvist.api.model.Checklist
import io.heapy.checkvist.jvm.configureCheckvist
import io.ktor.client.call.typeInfo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ChecklistTest {
    @Test
    fun testDeserialization() {
        val om = jacksonObjectMapper().configureCheckvist()
        val content = "checklists.json".readResource()
        val type = om.typeFactory.constructType(typeInfo<List<Checklist>>().reifiedType)
        val checklists = om.readValue<List<Checklist>>(content, type)

        assertEquals(2, checklists.size)
        assertEquals("test,test2", checklists[1].tagsAsText)
    }
}
