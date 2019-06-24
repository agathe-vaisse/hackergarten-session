package net.hackergarten.sessionapi.session

import net.hackergarten.sessionapi.venue.Venue
import java.util.*


fun session(title: String, date: Date, venue: Venue): Session {
    return Session(title, date, venue)
}