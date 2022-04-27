package com.example.goodplanet.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.goodplanet.R
import com.example.goodplanet.databinding.ActivityLeYangBinding
import com.example.goodplanet.ui.leyang.FuWuFragment
import com.example.goodplanet.ui.leyang.TaochanFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LeYangActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeYangBinding
    private var fragmentList : ArrayList<Fragment> = ArrayList()
    private val titles = arrayOf("乐养套餐","乐养服务")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeYangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fragmentList.add(TaochanFragment())
        fragmentList.add(FuWuFragment())
        binding.vpTb.adapter = object :FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return fragmentList.size
            }
            override fun createFragment(position: Int): Fragment {
                return fragmentList[position]
            }
        }
        binding.vpTb.offscreenPageLimit = 2
        TabLayoutMediator(binding.tbVp,binding.vpTb
        ) { tab, position ->
            val view = LayoutInflater.from(this).inflate(R.layout.item_tab,tab.parent,false)
            val text = view.findViewById<TextView>(R.id.tv_tab)
            text.text = titles[position]
            if (position == 0) {
                text.setTextColor(resources.getColor(R.color.myblue))
                text.setBackgroundColor(Color.WHITE)
            } else {
                text.setTextColor(resources.getColor(R.color.white))
                text.setBackgroundColor(resources.getColor(R.color.myblue))
            }
            tab.customView = view
        }.attach()
        binding.tbVp.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val tv = tab?.customView?.findViewById<TextView>(R.id.tv_tab)
                tv?.setTextColor(resources.getColor(R.color.myblue))
                tv?.setBackgroundColor(Color.WHITE)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val tv = tab?.customView?.findViewById<TextView>(R.id.tv_tab)
                tv?.setTextColor(resources.getColor(R.color.white))
                tv?.setBackgroundColor(resources.getColor(R.color.myblue))

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

    }
}