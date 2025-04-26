package billeteraseca.apifake

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ApiFakeApplication

fun main(args: Array<String>) {
    runApplication<ApiFakeApplication>(*args)
}
