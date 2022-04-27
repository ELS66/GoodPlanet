package com.example.goodplanet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goodplanet.databinding.ActivityJianKangBinding

class JianKangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJianKangBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJianKangBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}