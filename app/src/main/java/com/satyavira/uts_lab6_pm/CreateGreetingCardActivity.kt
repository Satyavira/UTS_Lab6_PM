package com.satyavira.uts_lab6_pm

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.satyavira.uts_lab6_pm.databinding.ActivityCreateGreetingCardBinding

class CreateGreetingCardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateGreetingCardBinding
    private var greetingCard: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateGreetingCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        greetingCard = intent.extras?.getInt("Greeting Card")!!

        savedInstanceState?.let {
            val savedTitle = it.getString("title")
            val savedContent = it.getString("content")
            binding.tilTitle.editText?.setText(savedTitle)
            binding.tilContent.editText?.setText(savedContent)
        }

        binding.btCreate.setOnClickListener {
            val title = binding.tilTitle.editText?.text.toString()
            val content = binding.tilContent.editText?.text.toString()
            if (title.isEmpty() || content.isEmpty()) {
                Snackbar.make(binding.root, "The title or content is not filled!", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (title.length > 20 || content.length > 370) {
                Snackbar.make(binding.root, "The title or content is too filled!", Snackbar.LENGTH_LONG).show()
                return@setOnClickListener
            }
            when (greetingCard) {
                1 -> {
                    val intent = Intent(this@CreateGreetingCardActivity, FirstGreetingCardActivity::class.java)
                    intent.putExtra("title", title)
                    intent.putExtra("content", content)
                    startActivity(intent)
                }
                2 -> {
                val intent = Intent(this@CreateGreetingCardActivity, SecondGreetingCardActivity::class.java)
                intent.putExtra("title", title)
                intent.putExtra("content", content)
                startActivity(intent)
                }
                3 -> {
                val intent = Intent(this@CreateGreetingCardActivity, ThirdGreetingCardActivity::class.java)
                intent.putExtra("title", title)
                intent.putExtra("content", content)
                startActivity(intent)
                }
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val title = binding.tilTitle.editText?.text.toString()
        val content = binding.tilContent.editText?.text.toString()
        outState.putString("title", title)
        outState.putString("content", content)
    }
}