package com.example.goodplanet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goodplanet.databinding.ActivityYouxiBinding

class YouxiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityYouxiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYouxiBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}