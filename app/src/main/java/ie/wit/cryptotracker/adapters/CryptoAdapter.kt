package ie.wit.cryptotracker.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import ie.wit.cryptotracker.databinding.CardCryptoBinding
import ie.wit.cryptotracker.models.CryptoModel

interface CryptoListener {
    fun onCryptoClick(crypto: CryptoModel)
}

class CryptoAdapter constructor(
    private var cryptos: List<CryptoModel>,
    private val listener: CryptoListener


) :
    RecyclerView.Adapter<CryptoAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardCryptoBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val crypto = cryptos[holder.adapterPosition]
        holder.bind(crypto, listener)
    }

    override fun getItemCount(): Int = cryptos.size

    class MainHolder(private val binding: CardCryptoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(crypto: CryptoModel, listener: CryptoListener) {
            binding.name.text = crypto.name
            binding.amount.text = "Amount: "+ crypto.amount
            binding.price.text = "Price: €" + crypto.price
            binding.total.text = "Total: €" + crypto.total

            Picasso.get().load(crypto.image).resize(600,600).into(binding.imageIcon)
            binding.root.setOnClickListener { listener.onCryptoClick(crypto) }
        }
    }
}
