package net.hackergarten.sessionapi.venue


fun venue(name: String, address: String): Venue {
    val venue = Venue()
    venue.name = name
    venue.address = address
    return venue
}