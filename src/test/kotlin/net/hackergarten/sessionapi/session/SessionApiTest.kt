package net.hackergarten.sessionapi.session

import net.hackergarten.sessionapi.venue.venue
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.isEmptyString
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.context.WebApplicationContext
import java.util.*
import javax.persistence.EntityManager

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner::class)
@TestPropertySource(locations = ["classpath:test.properties"])
@Transactional
class SessionApiTest {

    @Autowired
    lateinit var context: WebApplicationContext

    @Autowired
    lateinit var entityManager: EntityManager

    lateinit var mockMvc: MockMvc

    @Before
    fun `set up`() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .apply<DefaultMockMvcBuilder>(SecurityMockMvcConfigurers.springSecurity())
                .build()
    }

    @Test
    fun `restrict session api`() {
        mockMvc.perform(get("/api/sessions"))
                .andExpect(status().isUnauthorized)
    }

    @Test
    fun `expose session api`() {
        val now = Date()
        val venue = venue("Some name", "Some address")
        entityManager.persist(venue)
        entityManager.persist(session("hello", now, venue))

        mockMvc.perform(get("/api/sessions")
                .with(user("user")
                        .roles("LEAD")))
                .andExpect(status().isOk)
                .andExpect(jsonPath("$._embedded.sessions[0].title", equalTo("hello")))
                .andExpect(jsonPath("$._embedded.sessions[0].date", not(isEmptyString())))
                .andExpect(jsonPath("$._embedded.sessions[0].venue.name", equalTo("Some name")))
                .andExpect(jsonPath("$._embedded.sessions[0].venue.address", equalTo("Some address")))
    }
}