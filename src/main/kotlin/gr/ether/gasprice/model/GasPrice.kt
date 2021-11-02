package gr.ether.gasprice.model

import java.time.ZonedDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class GasPrice (
    @Id
    @GeneratedValue
    var id: Long?,
    var datetime: ZonedDateTime,
    var gasPrice: Int)