package net.hackergarten.sessionapi.venue

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup
import org.springframework.web.context.WebApplicationContext


@SpringBootTest
@RunWith(SpringRunner::class)
@TestPropertySource(locations = ["classpath:test.properties"])
class VenueApiTest {

    @Autowired
    lateinit var context: WebApplicationContext

    lateinit var mockMvc: MockMvc

    @Before
    fun `set up`() {
        mockMvc = webAppContextSetup(context)
                .apply<DefaultMockMvcBuilder>(springSecurity())
                .build()
    }

    @Test
    fun `restrict venues api`() {
        mockMvc.perform(get("/api/venues"))
                .andExpect(status().isUnauthorized)
    }

    @Test
    fun `expose venues api`() {
        mockMvc.perform(get("/api/venues")
                .with(user("user")
                        .roles("LEAD")))
                .andExpect(
                        status().isOk)
    }

}