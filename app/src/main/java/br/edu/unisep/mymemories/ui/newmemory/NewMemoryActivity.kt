package br.edu.unisep.mymemories.ui.newmemory

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import br.edu.unisep.mymemories.MainActivity
import br.edu.unisep.mymemories.R
import br.edu.unisep.mymemories.databinding.ActivityNewMemoryBinding

class NewMemoryActivity : AppCompatActivity() {

    private val viewModel by viewModels<NewMemoryViewModel>()

    private val binding: ActivityNewMemoryBinding by lazy {
        ActivityNewMemoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    private fun saveMemory() {
        startActivity(MainActivity.newIntent(this))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_new_memory, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.mnSaveNewMemory) {
            saveMemory()
            return true
        }

        return false
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, NewMemoryActivity::class.java)
    }
}