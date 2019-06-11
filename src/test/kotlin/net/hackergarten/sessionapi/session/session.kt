package net.hackergarten.sessionapi.session

import net.hackergarten.sessionapi.venue.Venue
import java.util.*


fun session(title: String, date: Date, venue: Venue): Session {
    val session = Session()
    session.title = title
    session.date = date
    session.venue = venue
    return session
}