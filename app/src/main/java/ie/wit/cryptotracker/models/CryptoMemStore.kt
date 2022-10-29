package ie.wit.cryptotracker.models

import timber.log.Timber.i

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class CryptoMemStore : CryptoStore {

    val cryptos = ArrayList<CryptoModel>()

    override fun findAll(): List<CryptoModel> {
        return cryptos
    }

    override fun create(crypto: CryptoModel) {
        crypto.id = getId()
        cryptos.add(crypto)
        logAll()
    }

    override fun update(crypto: CryptoModel) {
        var foundCrypto: CryptoModel? = cryptos.find { p -> p.id == crypto.id }
        if (foundCrypto != null) {
            foundCrypto.name = crypto.name
            foundCrypto.amount = crypto.name
            foundCrypto.price = crypto.price

            logAll()
        }
    }

    private fun logAll() {
        cryptos.forEach { i("$it") }
    }
}
