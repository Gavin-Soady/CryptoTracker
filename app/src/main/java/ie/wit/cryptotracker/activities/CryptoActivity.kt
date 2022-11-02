package ie.wit.cryptotracker.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

import com.google.android.material.snackbar.Snackbar
import ie.wit.cryptotracker.R
import ie.wit.cryptotracker.databinding.CryptoTokenBinding
import ie.wit.cryptotracker.main.MainApp
import ie.wit.cryptotracker.models.CryptoModel
import timber.log.Timber
import timber.log.Timber.i
import com.squareup.picasso.Picasso
import ie.wit.cryptotracker.showImagePicker

class CryptoActivity : AppCompatActivity() {

    private lateinit var binding: CryptoTokenBinding
    var crypto = CryptoModel()
    lateinit var app: MainApp
    private lateinit var imageIntentLauncher : ActivityResultLauncher<Intent>
    var edit = false

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = CryptoTokenBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.toolbarAdd.title = title
            setSupportActionBar(binding.toolbarAdd)
            app = application as MainApp

        i("Crypto Activity started...")

        if (intent.hasExtra("crypto_edit")) {
            edit = true
            crypto = intent.extras?.getParcelable("crypto_edit")!!
            binding.btnAdd.setText(R.string.save_crypto)
            binding.name.setText(crypto.name)
            binding.amount.setText(crypto.amount)
            binding.price.setText(crypto.price)
            //binding.total.setText(crypto.total)
            Picasso.get()
                .load(crypto.image)
                .into(binding.cryptoImage)
            if (crypto.image != Uri.EMPTY) {
                binding.chooseImage.setText(R.string.change_crypto_image)
            }

        }

        binding.btnAdd.setOnClickListener() {
            crypto.name = binding.name.text.toString()
            crypto.amount = binding.amount.text.toString()
            crypto.price = binding.price.text.toString()
            val amount = (crypto.amount).toFloat()
            val price = (crypto.price).toFloat()
            val total = amount * price
            crypto.total = total.toString()
            //crypto.amount = crypto.amount.toString()
           // crypto.price = crypto.price.toString()

            if (crypto.name.isEmpty()) {
                Snackbar.make(it, R.string.enter_crypto, Snackbar.LENGTH_LONG)
                    .show()
            }else {
                if (edit) {
                    app.cryptos.update(crypto.copy())
                } else {
                    app.cryptos.create(crypto.copy())
                }
            }
            i("add Button Pressed: ${crypto}")
            setResult(RESULT_OK)
            finish()
            }

            binding.chooseImage.setOnClickListener {
                showImagePicker(imageIntentLauncher)
            }
            registerImagePickerCallback()
        }




    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_crypto, menu)
        if (edit) menu.getItem(0).isVisible = true
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_delete -> {
                app.cryptos.delete(crypto)
                finish()
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }


     fun registerImagePickerCallback() {
        imageIntentLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult())
            { result ->
                when(result.resultCode){
                    RESULT_OK -> {
                        if (result.data != null) {
                            i("Got Result ${result.data!!.data}")
                            crypto.image = result.data!!.data!!
                            Picasso.get()
                                .load(crypto.image)
                                .into(binding.cryptoImage)
                            binding.chooseImage.setText(R.string.change_crypto_image)
                        } // end of if
                    }
                    RESULT_CANCELED -> { } else -> { }
                }
            }
    }
}