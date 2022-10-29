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
        //setContentView(R.layout.activity_main)
        hello()
    }
}

fun hello(){

    println("Hello Crypto World")
}