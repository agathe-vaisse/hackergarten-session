package net.hackergarten.sessionapi.venue

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers


@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner::class)
@TestPropertySource(locations = ["classpath:test.properties"])
class VenueApiTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `expose venues api`() {
        mockMvc.perform(get("/api/venues")).andExpect(MockMvcResultMatchers.status().isOk)
    }
}