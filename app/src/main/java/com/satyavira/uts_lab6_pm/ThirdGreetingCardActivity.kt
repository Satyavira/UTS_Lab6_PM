package com.satyavira.uts_lab6_pm

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
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

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}