package gr.ether.gasprice.rest.controller

import gr.ether.gasprice.mapper.GasPriceMapper
import gr.ether.gasprice.rest.dto.GasPriceDto
import gr.ether.gasprice.services.GasPriceService
import org.hibernate.mapping.Map
import org.mapstruct.factory.Mappers
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1")
class GasPriceController(
    private val gasPriceService: GasPriceService,
    private val gasPriceMapper: GasPriceMapper
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/gas-price")
    fun getGasPrice(): List<Int> {
        return gasPriceService.getGasPrice()
    }

    @GetMapping("/gas-price/avg")
    fun getGasPriceAvg(
        @RequestParam(defaultValue = "hour") period: String,
        @RequestParam(defaultValue = "1000") limit: Int,
        @RequestParam(defaultValue = "1000") maxPrice: Int
    ): List<GasPriceDto> {
        return gasPriceMapper.map(gasPriceService.getGasPriceAvg(limit, period, maxPrice))
    }


}