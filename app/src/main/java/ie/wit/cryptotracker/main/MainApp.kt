package ie.wit.cryptotracker.main

import android.app.Application
import android.os.Bundle
import ie.wit.cryptotracker.R
import ie.wit.cryptotracker.models.CryptoMemStore
import timber.log.Timber
import timber.log.Timber.i

class MainApp : Application() {

    val cryptos = CryptoMemStore()
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        i("CryptoTracker started")
    }
}

