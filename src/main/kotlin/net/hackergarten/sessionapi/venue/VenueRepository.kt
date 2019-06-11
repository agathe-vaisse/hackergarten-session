package net.hackergarten.sessionapi.venue

import org.springframework.data.jpa.repository.JpaRepository

interface VenueRepository : JpaRepository<Venue, Long>