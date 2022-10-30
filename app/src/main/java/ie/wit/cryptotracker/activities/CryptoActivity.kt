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
//import com.squareup.picasso.Picasso

class CryptoActivity : AppCompatActivity() {

    private lateinit var binding: CryptoTokenBinding
    var crypto = CryptoModel()
    lateinit var app: MainApp

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            binding = CryptoTokenBinding.inflate(layoutInflater)
            setContentView(binding.root)
            binding.toolbarAdd.title = title
            setSupportActionBar(binding.toolbarAdd)
            app = application as MainApp

        i("Crypto Activity started...")

            var edit = false
        if (intent.hasExtra("crypto_edit")) {
            edit = true
            crypto = intent.extras?.getParcelable("crypto_edit")!!
            binding.btnAdd.setText(R.string.save_crypto)
            binding.name.setText(crypto.name)
            binding.amount.setText(crypto.amount)

        }

        binding.btnAdd.setOnClickListener() {
            crypto.name = binding.name.text.toString()
            crypto.amount = binding.amount.text.toString()
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
        }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_crypto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}