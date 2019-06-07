package net.hackergarten.sessionapi.session

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner::class)
class SessionApiTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `expose session api`() {
        mockMvc.perform(get("/api/sessions")).andExpect(status().isOk)
    }
}