package com.example.goodplanet.ui.main

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.goodplanet.R
import com.example.goodplanet.databinding.FragmentMapRootBinding

class RootMapFragment : Fragment() {

    private lateinit var binding: FragmentMapRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMapRootBinding.inflate(layoutInflater,container,false)
        binding.tvChatcontent.setMaxWidth(getScreenWidth(requireActivity()) - dip2px(requireContext(),
            135.0F
        ))
        binding.llMessage.setBackgroundResource(R.drawable.qipao_lb)
        binding.tvChatcontent.text = "您好，我是您的养老助手"
        binding.llMessage2.setBackgroundResource(R.drawable.qipao_lb)
        binding.tvChatcontent2.text = "请详细描述您的需求（服务类型，时间，套餐类型），以便我为您找到合适的服务类型"
        return binding.root
    }

    //获取屏幕宽度
    fun getScreenWidth(activity: Activity): Int {
        val metric = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metric)
        return metric.widthPixels // 屏幕宽度（像素）
    }

    //获取屏幕高度
    fun getScreenHight(activity: Activity): Int {
        val metric = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metric)
        return metric.heightPixels // 屏幕宽度（像素）
    }

    //获取屏幕宽高
    fun getScreenWidthAndHight(activity: Activity): IntArray? {
        val metric = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metric)
        return intArrayOf(metric.widthPixels, metric.heightPixels) // 屏幕宽度（像素）
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

}