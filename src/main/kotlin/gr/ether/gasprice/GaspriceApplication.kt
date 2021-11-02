package gr.ether.gasprice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class GaspriceApplication

fun main(args: Array<String>) {
    runApplication<GaspriceApplication>(*args)
}
