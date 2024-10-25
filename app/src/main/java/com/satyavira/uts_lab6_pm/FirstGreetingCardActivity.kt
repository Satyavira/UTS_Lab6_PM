package com.satyavira.uts_lab6_pm

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.satyavira.uts_lab6_pm.databinding.ActivityFirstGreetingCardBinding

class FirstGreetingCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstGreetingCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityFirstGreetingCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

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