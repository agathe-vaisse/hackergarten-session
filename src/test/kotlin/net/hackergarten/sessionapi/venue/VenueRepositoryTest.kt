package net.hackergarten.sessionapi.venue

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.TestPropertySource
import org.springframework.test.context.junit4.SpringRunner
import javax.persistence.EntityManager

@DataJpaTest(showSql = false)
@RunWith(SpringRunner::class)
@TestPropertySource(locations = ["classpath:test.properties"])
class VenueRepositoryTest {

    @Autowired
    lateinit var venueRepository: VenueRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun `find empty venue list`() {
        val sessions = venueRepository.findAll()

        assertThat(sessions).isEmpty()
    }

    @Test
    fun `find preregistered venue`() {
        val savedVenue = venue(name = "Pivotal France", address = "33 rue Lafayette 75009 Paris")
        entityManager.persist(savedVenue)

        val maybeSession = venueRepository.findById(savedVenue.id!!)

        assertThat(maybeSession).isPresent
        assertThat(maybeSession.get()).isEqualToComparingFieldByField(savedVenue)
    }

}