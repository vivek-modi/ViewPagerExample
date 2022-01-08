package com.example.viewpagerexample

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.viewpagerexample.databinding.TopLayoutBinding

class ButtonActivity : AppCompatActivity() {

    private lateinit var binding: TopLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TopLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}