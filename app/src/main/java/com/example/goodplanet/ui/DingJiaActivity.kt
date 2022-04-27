package com.example.goodplanet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goodplanet.base.BaseActivity
import com.example.goodplanet.databinding.ActivityDingJiaBinding

class DingJiaActivity : BaseActivity() {

    private lateinit var binding: ActivityDingJiaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDingJiaBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}