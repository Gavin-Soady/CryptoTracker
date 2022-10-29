package ie.wit.cryptotracker.models

data class CryptoModel(var id: Long = 0,
                       var name: String = "",
                       var amount: String = "",
                       var price: String = "",
)
