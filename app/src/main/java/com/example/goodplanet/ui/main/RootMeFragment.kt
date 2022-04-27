package com.example.goodplanet.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.goodplanet.MainActivity
import com.example.goodplanet.R
import com.example.goodplanet.base.BaseActivity
import com.example.goodplanet.databinding.FragmentMeRootBinding

class RootMeFragment : Fragment() {

    private lateinit var binding: FragmentMeRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMeRootBinding.inflate(layoutInflater,container,false)
        binding.tvMeName.text = readstate()
        return binding.root
    }


    private fun readstate(): String? {
        val sharedPreferences = requireActivity().getSharedPreferences("login_state", BaseActivity.MODE_PRIVATE)
        return sharedPreferences.getString("username", "")
    }


}