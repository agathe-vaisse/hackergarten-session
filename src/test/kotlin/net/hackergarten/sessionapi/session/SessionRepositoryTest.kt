package net.hackergarten.sessionapi.session

import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner
import java.util.*
import javax.persistence.EntityManager

@DataJpaTest
@RunWith(SpringRunner::class)
class SessionRepositoryTest {

    @Autowired
    lateinit var sessionRepository: SessionRepository

    @Autowired
    lateinit var entityManager: EntityManager

    @Test
    fun `find empty sessions list`(){
        val sessions = sessionRepository.findAll()
        Assertions.assertThat(sessions).isEmpty()
    }

    @Test
    fun `find preregistered session` () {
        val savedSession = Session(title = "Session 1", date = Date(), venue = "Pivotal France", address = "33 rue Lafayette 75009 Paris")
        entityManager.persist(savedSession)
        val maybeSession = sessionRepository.findById(savedSession.id!!)
        Assertions.assertThat(maybeSession).isPresent
        val session = maybeSession.get()
        Assertions.assertThat(session).isEqualToComparingFieldByField(savedSession)
    }

}