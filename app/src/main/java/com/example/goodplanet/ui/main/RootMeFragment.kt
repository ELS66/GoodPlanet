package com.example.goodplanet.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.goodplanet.MainActivity
import com.example.goodplanet.R
import com.example.goodplanet.base.BaseActivity
import com.example.goodplanet.databinding.FragmentMeRootBinding

class RootMeFragment : Fragment() {

    private lateinit var binding: FragmentMeRootBinding
    private var pass : String? = ""

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
        if (pass == "root") {
            binding.tvMeLevel.text = "管理员"
            binding.clMeRoot.visibility = View.VISIBLE
            Glide.with(requireActivity())
                .load(context?.resources?.getDrawable(R.drawable.ai))
                .into(binding.cirImage)
        } else {
            binding.tvMeLevel.text = "用户"
            binding.clMeRoot.visibility = View.GONE
            Glide.with(requireActivity())
                .load(context?.resources?.getDrawable(R.drawable.me_touxiang))
                .apply(RequestOptions.circleCropTransform())
                .into(binding.cirImage)
        }
        return binding.root
    }


    private fun readstate(): String? {
        val sharedPreferences = requireActivity().getSharedPreferences("login_state", BaseActivity.MODE_PRIVATE)
        pass = sharedPreferences.getString("password", "")
        return sharedPreferences.getString("username", "")
    }

    private fun readstatePass(): String? {
        val sharedPreferences = requireActivity().getSharedPreferences("login_state", BaseActivity.MODE_PRIVATE)
        return sharedPreferences.getString("pass", "")
    }


}