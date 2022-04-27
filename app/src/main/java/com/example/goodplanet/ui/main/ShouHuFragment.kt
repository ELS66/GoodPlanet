package com.example.goodplanet.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goodplanet.R
import com.example.goodplanet.databinding.FragmentShouHuBinding


class ShouHuFragment : Fragment() {

    private lateinit var binding: FragmentShouHuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShouHuBinding.inflate(layoutInflater,container,false)
        Glide.with(requireActivity())
            .load(context?.resources?.getDrawable(R.drawable.sh_tx1))
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivSh1)
        Glide.with(requireActivity())
            .load(context?.resources?.getDrawable(R.drawable.sh_tx2))
            .apply(RequestOptions.circleCropTransform())
            .into(binding.ivSh2)
        return binding.root
    }

}