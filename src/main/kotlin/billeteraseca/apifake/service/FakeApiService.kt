package billeteraseca.apifake.service

import billeteraseca.apifake.dto.InstantDebitRequest
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class FakeApiService {

    // List of valid banks for the simulation
    val validBanks = listOf("Galicia", "Santander", "BBVA", "Nación", "Provincia")

    // Map to store CBU balances - using ConcurrentHashMap for thread safety
    private val cbuBalances = ConcurrentHashMap<String, Double>().apply {
        // Initialize with 10 hardcoded CBUs and their balances
        put("1234567890123456789012", 50000.0)  // CBU 1
        put("2345678901234567890123", 75000.0)  // CBU 2
        put("3456789012345678901234", 100000.0) // CBU 3
        put("4567890123456789012345", 25000.0)  // CBU 4
        put("5678901234567890123456", 150000.0) // CBU 5
        put("6789012345678901234567", 30000.0)  // CBU 6
        put("7890123456789012345678", 45000.0)  // CBU 7
        put("8901234567890123456789", 60000.0)  // CBU 8
        put("9012345678901234567890", 80000.0)  // CBU 9
        put("0123456789012345678901", 120000.0) // CBU 10
    }

    fun processInstantDebitRequest(request: InstantDebitRequest): Result<Boolean> {
        println("Processing Instant Debit Request: $request")

        // Validate bank name
        if (!validBanks.contains(request.bankName)) {
            return Result.failure(Exception("Bank ${request.bankName} is not available or does not exist"))
        }

        // Validate amount
        if (request.amount <= 0) {
            return Result.failure(Exception("Invalid amount, must be greater than 0"))
        }

        // Validate CBU exists
        if (!cbuBalances.containsKey(request.cbu)) {
            return Result.failure(Exception("CBU ${request.cbu} does not exist"))
        }

        // Check if there's sufficient balance
        val currentBalance = cbuBalances[request.cbu] ?: 0.0
        if (currentBalance < request.amount) {
            return Result.failure(Exception("Insufficient balance. Current balance: $currentBalance, Required: ${request.amount}"))
        }

        // Process the transaction by updating the balance
        cbuBalances[request.cbu] = currentBalance - request.amount
        println("Transaction successful. New balance for CBU ${request.cbu}: ${cbuBalances[request.cbu]}")
        
        return Result.success(true)
    }

    // Helper method to get current balance (for testing purposes)
    fun getBalance(cbu: String): Double? = cbuBalances[cbu]
}
