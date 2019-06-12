package net.hackergarten.sessionapi.venue


fun venue(name: String, address: String): Venue {
    return Venue(name = name, address = address)
}