package net.hackergarten.sessionapi.session

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(collectionResourceRel = "sessions", path = "sessions")
interface SessionRepository : JpaRepository<Session, Long>{


}
