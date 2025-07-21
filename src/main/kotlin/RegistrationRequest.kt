// Questa è una "data class" che mappa esattamente i campi
// che la nostra app Android invierà nel corpo della richiesta JSON.
data class RegistrationRequest(
    val phoneNumber: String,
    val identityPublicKey: String,
    val registrationId: Int,
    val signedPreKey: SignedPreKeyDto,
    val preKeys: List<PreKeyDto>
)

data class SignedPreKeyDto(val id: Int, val key: String)

data class PreKeyDto(val id: Int, val key: String)
