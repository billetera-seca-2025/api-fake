package billeteraseca.apifake.dto

data class InstantDebitRequest(
    val receiverEmail: String,
    val bankName: String,
    val cbu: String,
    val amount: Double
)
// This class represents a request for a DEBIN transaction.