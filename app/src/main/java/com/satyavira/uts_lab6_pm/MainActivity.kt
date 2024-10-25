package com.satyavira.uts_lab6_pm

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.satyavira.uts_lab6_pm.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btCard1.setOnClickListener {
            goToCreateGreetingCardActivity(1)
        }
        binding.btCard2.setOnClickListener {
            goToCreateGreetingCardActivity(2)
        }
        binding.btCard3.setOnClickListener {
            goToCreateGreetingCardActivity(3)
        }
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    private fun goToCreateGreetingCardActivity(greetingCard: Int) {
        val intent = Intent(this@MainActivity, CreateGreetingCardActivity::class.java)
        intent.putExtra("Greeting Card", greetingCard)
        startActivity(intent)
    }
}