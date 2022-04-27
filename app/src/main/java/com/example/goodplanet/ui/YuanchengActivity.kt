package com.example.goodplanet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.goodplanet.R
import com.example.goodplanet.base.BaseActivity
import com.example.goodplanet.databinding.ActivityYuanchengBinding

class YuanchengActivity : BaseActivity() {

    private lateinit var binding: ActivityYuanchengBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYuanchengBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

    }

    private fun initView() {
        binding.tvName.text = readstate()
    }

    private fun readstate(): String? {
        val sharedPreferences = getSharedPreferences("login_state", BaseActivity.MODE_PRIVATE)
        return sharedPreferences.getString("username", "")
    }


}