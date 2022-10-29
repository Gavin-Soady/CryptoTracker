package ie.wit.cryptotracker.models


interface CryptoStore {
    fun findAll(): List<CryptoModel>
    fun create(placemark: CryptoModel)
    fun update(placemark: CryptoModel)
}