package ie.wit.cryptotracker.main

import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ie.wit.cryptotracker.models.CryptoJSONStore
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

