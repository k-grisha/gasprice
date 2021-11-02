package gr.ether.gasprice.rest.dto

import java.time.ZonedDateTime

data class GasPriceDto(val datetime: ZonedDateTime,
                       val gasPrice: Int )