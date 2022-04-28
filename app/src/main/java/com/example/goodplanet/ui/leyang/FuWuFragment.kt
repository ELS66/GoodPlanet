package com.example.goodplanet.ui.leyang

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.goodplanet.R
import com.example.goodplanet.databinding.FragmentFuWuBinding


class FuWuFragment : Fragment() {

    private lateinit var binding: FragmentFuWuBinding
    private var ivlist1 : ArrayList<Int> = ArrayList()
    private var tvlist1 : ArrayList<String> = ArrayList()
    private var ivlist2 : ArrayList<Int> = ArrayList()
    private var tvlist2 : ArrayList<String> = ArrayList()
    private var ivlist3 : ArrayList<Int> = ArrayList()
    private var tvlist3 : ArrayList<String> = ArrayList()
    private var ivlist4 : ArrayList<Int> = ArrayList()
    private var tvlist4 : ArrayList<String> = ArrayList()
    private var ivlist5 : ArrayList<Int> = ArrayList()
    private var tvlist5 : ArrayList<String> = ArrayList()
    private var tvAllList : ArrayList<TextView> = ArrayList()
    private var ivAllList : ArrayList<ImageView> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFuWuBinding.inflate(layoutInflater,container,false)
        initData()
        binding.tvFw1.setOnClickListener {
            clear()
            binding.tvFw1.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.tvFw1.setTextColor(Color.parseColor("#F04B71"))
            for (i in ivlist1.indices) {
                Glide.with(requireActivity())
                    .load(context?.resources?.getDrawable(ivlist1[i]))
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(5)))
                    .into(ivAllList[i])
                tvAllList[i].text = tvlist1[i]
            }
        }
        binding.tvFw2.setOnClickListener {
            clear()
            binding.tvFw2.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.tvFw2.setTextColor(Color.parseColor("#F04B71"))
            binding.llFw4.visibility = View.VISIBLE
            for (i in ivlist2.indices) {
                Glide.with(requireActivity())
                    .load(context?.resources?.getDrawable(ivlist2[i]))
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(5)))
                    .into(ivAllList[i])
                tvAllList[i].text = tvlist2[i]
            }
        }
        binding.tvFw3.setOnClickListener {
            clear()
            binding.tvFw3.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.tvFw3.setTextColor(Color.parseColor("#F04B71"))
            for (i in ivlist3.indices) {
                Glide.with(requireActivity())
                    .load(context?.resources?.getDrawable(ivlist3[i]))
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(5)))
                    .into(ivAllList[i])
                tvAllList[i].text = tvlist3[i]
            }
        }
        binding.tvFw4.setOnClickListener {
            clear()
            binding.tvFw4.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.tvFw4.setTextColor(Color.parseColor("#F04B71"))
            for (i in ivlist4.indices) {
                Glide.with(requireActivity())
                    .load(context?.resources?.getDrawable(ivlist4[i]))
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(5)))
                    .into(ivAllList[i])
                tvAllList[i].text = tvlist4[i]
            }
        }
        binding.tvFw5.setOnClickListener {
            clear()
            binding.tvFw5.setBackgroundColor(Color.parseColor("#FFFFFF"))
            binding.tvFw5.setTextColor(Color.parseColor("#F04B71"))
            for (i in ivlist5.indices) {
                Glide.with(requireActivity())
                    .load(context?.resources?.getDrawable(ivlist5[i]))
                    .apply(RequestOptions.bitmapTransform(RoundedCorners(5)))
                    .into(ivAllList[i])
                tvAllList[i].text = tvlist5[i]
            }
        }

        return binding.root
    }

    private fun initData() {
        tvAllList.add(binding.tvFw11)
        ivAllList.add(binding.ivFw1)
        tvAllList.add(binding.tvFw12)
        ivAllList.add(binding.ivFw2)
        tvAllList.add(binding.tvFw13)
        ivAllList.add(binding.ivFw3)
        tvAllList.add(binding.tvFw14)
        ivAllList.add(binding.ivFw4)
        ivlist1.add(R.drawable.fw11)
        ivlist1.add(R.drawable.fw12)
        ivlist1.add(R.drawable.fw13)
        tvlist1.add("健康评估")
        tvlist1.add("体格检查")
        tvlist1.add("诊疗操作")
        ivlist2.add(R.drawable.fw21)
        ivlist2.add(R.drawable.fw22)
        ivlist2.add(R.drawable.fw23)
        ivlist2.add(R.drawable.fw24)
        tvlist2.add("基础护理")
        tvlist2.add("康复护理")
        tvlist2.add("心理护理")
        tvlist2.add("专项护理")
        ivlist3.add(R.drawable.fw31)
        ivlist3.add(R.drawable.fw32)
        ivlist3.add(R.drawable.fw33)
        tvlist3.add("康复评定")
        tvlist3.add("康复指导")
        tvlist3.add("康复治疗")
        ivlist4.add(R.drawable.fw41)
        ivlist4.add(R.drawable.fw42)
        ivlist4.add(R.drawable.fw43)
        tvlist4.add("舒适照护")
        tvlist4.add("心理评估")
        tvlist4.add("症状控制")
        ivlist5.add(R.drawable.fw51)
        ivlist5.add(R.drawable.fw52)
        ivlist5.add(R.drawable.fw53)
        tvlist5.add("健康指导")
        tvlist5.add("中医辨证论治")
        tvlist5.add("中医技术")
    }

    private fun clear() {
        binding.tvFw1.setBackgroundColor(Color.parseColor("#F5F6F7"))
        binding.tvFw2.setBackgroundColor(Color.parseColor("#F5F6F7"))
        binding.tvFw3.setBackgroundColor(Color.parseColor("#F5F6F7"))
        binding.tvFw4.setBackgroundColor(Color.parseColor("#F5F6F7"))
        binding.tvFw5.setBackgroundColor(Color.parseColor("#F5F6F7"))
        binding.tvFw1.setTextColor(Color.parseColor("#000000"))
        binding.tvFw2.setTextColor(Color.parseColor("#000000"))
        binding.tvFw3.setTextColor(Color.parseColor("#000000"))
        binding.tvFw4.setTextColor(Color.parseColor("#000000"))
        binding.tvFw5.setTextColor(Color.parseColor("#000000"))
        binding.llFw4.visibility = View.INVISIBLE
    }

}