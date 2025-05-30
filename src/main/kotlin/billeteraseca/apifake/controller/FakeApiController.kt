package billeteraseca.apifake.controller

import billeteraseca.apifake.dto.InstantDebitRequest
import billeteraseca.apifake.service.FakeApiService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/mock")
class FakeApiController(private val fakeApiService: FakeApiService) {

    @PostMapping("/instant-debit")
    fun simulateInstantDebit(@RequestBody request: InstantDebitRequest): ResponseEntity<Any> {
        return fakeApiService.processInstantDebitRequest(request)
            .fold(
                onSuccess = { ResponseEntity.ok(true) },
                onFailure = { ResponseEntity.status(HttpStatus.BAD_REQUEST).body(it.message) }
            )
    }

    @GetMapping("/get-cbu-balance/{cbu}")
    fun getCbuBalance(@PathVariable cbu: String): ResponseEntity<Any> {
        val balance = fakeApiService.getBalance(cbu)
        return if (balance != null) {
            ResponseEntity.ok(mapOf(
                "cbu" to cbu,
                "balance" to balance
            ))
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "CBU not found"))
        }
    }
}