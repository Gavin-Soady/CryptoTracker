package ie.wit.cryptotracker.models


import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CryptoModel(var id: Long = 0,
                       var name: String = "",
                       var amount: String = "",
                       var price: String = "",
                       var total: String = "",
                       var image: Uri = Uri.EMPTY ) : Parcelable
