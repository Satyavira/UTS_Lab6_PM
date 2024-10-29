package com.satyavira.uts_lab6_pm

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.satyavira.uts_lab6_pm.databinding.ActivityThirdGreetingCardBinding

class ThirdGreetingCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdGreetingCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThirdGreetingCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.greeting_card_3)
        val matrix = Matrix()
        matrix.postRotate(180f)

        val rotatedBitmap: Bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        binding.main.background = BitmapDrawable(resources, rotatedBitmap)

        val title = intent.extras?.getString("title")
        val content = intent.extras?.getString("content")

        binding.tvTitle.text = title
        binding.tvContent.text = content

        binding.btSave.setOnClickListener {
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "greeting_card_${System.currentTimeMillis()}.jpg")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            }

            val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)

            uri?.let {
                val outputStream = contentResolver.openOutputStream(it)
                if (outputStream != null) {
                    takeScreenshot().compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                }
                outputStream?.close()
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    setDataAndType(uri, "image/*")
                    addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) // Grant temporary access
                }
                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun takeScreenshot(): Bitmap {
        val rootView = binding.root
        binding.btSave.visibility = View.GONE
        val bitmap = Bitmap.createBitmap(rootView.width, rootView.height, Bitmap.Config.ARGB_8888)

        val canvas = Canvas(bitmap)
        rootView.draw(canvas)
        binding.btSave.visibility = View.VISIBLE

        return bitmap
    }
}