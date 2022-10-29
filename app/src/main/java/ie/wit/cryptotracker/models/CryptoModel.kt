package ie.wit.cryptotracker.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CryptoModel(var id: Long = 0,
                       var name: String = "",
                       var amount: String = "",
                       var price: String = "", ) : Parcelable
