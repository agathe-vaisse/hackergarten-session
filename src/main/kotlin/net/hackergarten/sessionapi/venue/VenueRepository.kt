package net.hackergarten.sessionapi.venue

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.access.prepost.PreAuthorize

@PreAuthorize("hasRole('ROLE_LEAD')")
interface VenueRepository : JpaRepository<Venue, Long>