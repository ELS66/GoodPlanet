package com.example.goodplanet.ui.main

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.goodplanet.MainActivity
import com.example.goodplanet.R
import com.example.goodplanet.databinding.FragmentHomeRootBinding
import com.example.goodplanet.ui.*
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder
import java.util.ArrayList


class RootHomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeRootBinding
    private var imglist = ArrayList<Drawable>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeRootBinding.inflate(layoutInflater,container,false)
        initView()
        return binding.root
    }

    private fun initView() {
        imglist.add(resources.getDrawable(R.drawable.banner1))
        imglist.add(resources.getDrawable(R.drawable.banner2))
        imglist.add(resources.getDrawable(R.drawable.banner3))
        binding.ivAi.setOnClickListener {
            val intent = Intent(binding.root.context,OrderActivity::class.java)
            startActivity(intent)
        }
        binding.banner.adapter = object : BannerImageAdapter<Drawable>(imglist){

            override fun onBindView(
                holder: BannerImageHolder?,
                data: Drawable?,
                position: Int,
                size: Int
            ) {
                holder?.let {
                    Glide.with(holder.imageView)
                        .load(imglist[position])
                        .into(holder.imageView)
                }
            }

        }
        binding.llDangan.setOnClickListener {
            startActivity(Intent(context,FileActivity::class.java))
        }
        binding.llYuyue.setOnClickListener {
            startActivity(Intent(context,OrderActivity::class.java))
        }
        binding.llYuancheng.setOnClickListener {
            val intent = Intent(context,YuanchengActivity::class.java)
//            intent.putExtra("name",MainActivity.name)
            startActivity(intent)
        }
        binding.llDingjia.setOnClickListener {
            startActivity(Intent(context,DingJiaActivity::class.java))
        }
        binding.llXuzhi.setOnClickListener {
            startActivity(Intent(context,LeYangActivity::class.java))
        }
        binding.llWenda.setOnClickListener {

        }
        binding.llShenghuo.setOnClickListener {
            startActivity(Intent(context,ShengHuoActivity::class.java))
        }
        binding.llYule.setOnClickListener {
            startActivity(Intent(context,YouxiActivity::class.java))
        }
    }


}