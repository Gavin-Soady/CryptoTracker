package ie.wit.cryptotracker.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ie.wit.cryptotracker.R
import ie.wit.cryptotracker.adapters.CryptoAdapter
import ie.wit.cryptotracker.adapters.CryptoListener
import ie.wit.cryptotracker.databinding.CryptoListBinding
import ie.wit.cryptotracker.main.MainApp
import ie.wit.cryptotracker.models.CryptoModel


class CryptoListActivity : AppCompatActivity(), CryptoListener {

    lateinit var app: MainApp
    private lateinit var binding: CryptoListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CryptoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title = title
        setSupportActionBar(binding.toolbar)

        app = application as MainApp

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = CryptoAdapter(app.cryptos.findAll(),this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> {
                val launcherIntent = Intent(this, CryptoActivity::class.java)
               startActivityForResult(launcherIntent,0)
            }
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onCryptoClick(crypto: CryptoModel) {
        val launcherIntent = Intent(this, CryptoActivity::class.java)
        launcherIntent.putExtra("crypto_edit", crypto)
        startActivityForResult(launcherIntent,0)
    }


}