package net.hackergarten.sessionapi.session

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(excerptProjection = SessionWithVenue::class)
interface SessionRepository : JpaRepository<Session, Long>