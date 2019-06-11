package net.hackergarten.sessionapi.session

import net.hackergarten.sessionapi.venue.venue
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import java.util.Date
import javax.persistence.EntityManager

@DataJpaTest(showSql = false)
@RunWith(SpringRunner::class)
@TestPropertySource(locations = ["classpath:test.properties"])
class SessionRepositoryTest {

    @Autowired
    lateinit var sessionRepository: SessionRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun `find empty sessions list`() {
        val sessions = sessionRepository.findAll()

        assertThat(sessions).isEmpty()
    }

    @Test
    fun `find preregistered session`() {
        val savedVenue = venue(name = "Pivotal France", address = "33 rue Lafayette 75009 Paris")
        val savedSession = session(title = "Session 1", date = Date(), venue = savedVenue)
        entityManager.persist(savedSession)

        val maybeSession = sessionRepository.findById(savedSession.id!!)

        assertThat(maybeSession).isPresent
        assertThat(maybeSession.get()).isEqualToComparingFieldByField(savedSession)
    }

}