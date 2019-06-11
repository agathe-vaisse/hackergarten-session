package net.hackergarten.sessionapi.session

import net.hackergarten.sessionapi.venue.Venue
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Temporal
import javax.persistence.TemporalType

@Entity
class Session() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var title: String = ""
    @Temporal(TemporalType.TIMESTAMP)
    var date: Date = Date(0)
    @ManyToOne
    var venue: Venue = Venue()

}
