package ie.wit.cryptotracker.models


interface CryptoStore {
    fun findAll(): List<CryptoModel>
    fun create(crypto: CryptoModel)
    fun update(crypto: CryptoModel)
    fun delete(crypto: CryptoModel)
}