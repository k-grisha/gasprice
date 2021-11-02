package gr.ether.gasprice.services

import gr.ether.gasprice.model.GasPrice
import gr.ether.gasprice.repository.GasPriceRepository
import java.time.ZonedDateTime
import java.util.Collections.synchronizedList
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class GasPriceService(
    private val gasPriceRepository: GasPriceRepository
) {

    private val restTemplate = RestTemplate()
    private val gasPrices = synchronizedList(mutableListOf<Int>())

    fun getGasPrice(): List<Int> {
        return gasPrices.toList()
    }

    fun getGasPriceAvg(limit: Int, period: String, maxPrice: Int): List<GasPrice> {
        return gasPriceRepository.fetchAll(limit, period, maxPrice).toList()
    }


    @Scheduled(fixedDelay = 10000)
    fun fetchGasPrice() {
        restTemplate.getForEntity(
            "https://api.etherscan.io/api?module=gastracker&action=gasoracle&apikey=YourApiKeyToken",
            EtherscanResponce::class.java
        ).body?.result?.SafeGasPrice?.let {
            gasPrices.add(it)
        }
    }

    @Scheduled(fixedRate = 60000, initialDelay = 60000)
    fun persistAvgGasPrice() {
        gasPriceRepository.save(GasPrice(null, ZonedDateTime.now(), gasPrices.average().toInt()))
        gasPrices.clear()
    }


    data class EtherscanResponce(val status: String, val result: EtherscanResult)
    data class EtherscanResult(val LastBlock: Long, val SafeGasPrice: Int)

}