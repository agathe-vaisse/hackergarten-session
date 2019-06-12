package net.hackergarten.sessionapi.venue

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Venue(@Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            var id: Long? = null,
            var name: String,
            var address: String)