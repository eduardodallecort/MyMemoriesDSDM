package br.edu.unisep.mymemories.ui.newmemory

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.graphics.drawable.toBitmap
import br.edu.unisep.mymemories.MainActivity
import br.edu.unisep.mymemories.R
import br.edu.unisep.mymemories.data.Image
import br.edu.unisep.mymemories.databinding.ActivityNewMemoryBinding
import br.edu.unisep.mymemories.ui.newlocalization.NewLocalizationActivity
import br.edu.unisep.mymemories.utils.bitmapToBase64
import br.edu.unisep.mymemories.utils.getUriForFile
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class NewMemoryActivity : AppCompatActivity() {

    private val viewModel: NewMemoryViewModel by viewModel()

    private lateinit var pictureUri: Uri

    lateinit var onSave: (() -> Unit)


    private val binding: ActivityNewMemoryBinding by lazy {
        ActivityNewMemoryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupEvents()
    }

    private fun setupEvents() {
        binding.btnCam.setOnClickListener { takePicture() }
        binding.btnGallery.setOnClickListener { openImageGallery() }

        binding.btnTakeLocalization.setOnClickListener { }

    }

    private fun takePicture(): String {
        val base64: String = createPictureFile()
        takePictureLauncher.launch(pictureUri)

        return base64
    }

    private fun createPictureFile(): String {
        val pictureDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val pictureFile = File(pictureDir, "_image.jpg")

        this.pictureUri = getUriForFile(this, pictureFile)

        binding.ivPicture.setImageURI(this.pictureUri)

        val image = Image()
        image.fileName = pictureFile.name
        image.mimeType = "imagem/jpg"
        image.base64 = bitmapToBase64(binding.ivPicture.drawable.toBitmap())

        return image.base64
    }

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { pictureTaken ->
        if (pictureTaken) {
            viewModel.getImages(this)
        }
    }

    private fun saveMemory() {

        val imageBase64: String = takePicture()

        viewModel.save(
            imageBase64,
            binding.etCity.text.toString(),
            binding.etDescription.text.toString(),
            "-55.0",
        "-56.0"
        )

        startActivity(MainActivity.newIntent(this))
    }

    private fun openImageGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Selecionar Imagem"), 4587)
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

    private fun onSaveSuccess() {
        onSave()
    }

    private fun onSaveError() {
        Snackbar.make(binding.root, "Erro ao incluir memory", Snackbar.LENGTH_LONG).show()
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, NewMemoryActivity::class.java)
    }
}