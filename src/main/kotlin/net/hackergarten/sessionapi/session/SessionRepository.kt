package net.hackergarten.sessionapi.session

import org.springframework.data.jpa.repository.JpaRepository

interface SessionRepository : JpaRepository<Session, Long>