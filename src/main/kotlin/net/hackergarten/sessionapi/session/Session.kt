package net.hackergarten.sessionapi.session

import java.util.*
import javax.persistence.*

@Entity
class Session (@Id @GeneratedValue(strategy = GenerationType.TABLE) var id: Long? = null, var title: String, @Temporal(TemporalType.TIMESTAMP) var date: Date, var venue: String, var address: String) {

}
