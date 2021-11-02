package gr.ether.gasprice.mapper

import gr.ether.gasprice.model.GasPrice
import gr.ether.gasprice.rest.dto.GasPriceDto
import org.mapstruct.Mapper

@Mapper(componentModel="spring")
interface GasPriceMapper {
    fun map(entity: GasPrice): GasPriceDto
    fun map(entity: List<GasPrice>): List<GasPriceDto>
}