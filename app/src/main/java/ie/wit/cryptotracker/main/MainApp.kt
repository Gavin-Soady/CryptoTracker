package ie.wit.cryptotracker.main

import android.app.Application
import android.os.Bundle
import ie.wit.cryptotracker.R
import ie.wit.cryptotracker.models.CryptoJSONStore
import ie.wit.cryptotracker.models.CryptoMemStore
import ie.wit.cryptotracker.models.CryptoStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    lateinit var cryptos: CryptoStore

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        cryptos = CryptoJSONStore(applicationContext)
        i("CryptoTracker started")
    }
}

