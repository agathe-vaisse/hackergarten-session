package net.hackergarten.sessionapi.session

import net.hackergarten.sessionapi.venue.Venue
import org.springframework.data.rest.core.config.Projection
import java.util.*

@Projection(name = "sessionWithVenue", types = [Venue::class])
interface SessionWithVenue {

    fun getTitle(): String

    fun getDate(): Date

    fun getVenue(): Venue
}