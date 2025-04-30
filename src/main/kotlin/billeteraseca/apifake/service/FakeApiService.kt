package billeteraseca.apifake.service

import billeteraseca.apifake.dto.DebinRequest
import org.springframework.stereotype.Service

@Service
class FakeApiService {
    fun processDebinRequest(request: DebinRequest): Boolean {
        println("Processing DEBIN: $request")

        // Simple validation of the request
        if (request.amount <= 0) {
            println("Invalid amount")
            return false
        }

        if (request.amount > 10000.0) {
            println("DEBIN rejected: Amount exceeds limit")
            return false
        }

        // Simulate a random response for the DEBIN request
        val result = listOf(true, false).random()
        println("DEBIN: ${if (result) "APPROVED" else "REJECTED"}")
        return result
    }
}
