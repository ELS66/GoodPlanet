package com.example.goodplanet.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.goodplanet.base.BaseActivity
import com.example.goodplanet.databinding.ActivityOrderBinding
import com.example.goodplanet.utils.TimePopupWindow
import com.example.goodplanet.utils.Utils
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class OrderActivity : BaseActivity() {

    private lateinit var binding: ActivityOrderBinding
    private var selectDate = Date()
    private var selectTime = ""
    private val arrayList1 : Array<String> = arrayOf("护理型","非护理型")
    private val arrayList2 = arrayOf("自理","失能","非失能")
    private val arrayList3 = arrayOf("日托","月托","半年托","年托")
    private val arrayList4 = arrayOf("药物治疗","物理治疗","作业治疗","言语治疗")
    private val arrayList5 = arrayOf("生活照料","家政服务","基本健康护理")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
    }

    fun initView() {
        binding.rlOrderTime.setOnClickListener {
            showSelectTime(binding.tvOrderTimeText,selectDate)
        }
        binding.rlOrderBed.setOnClickListener {
            showSelect(arrayList1,binding.tvOrderBedText)
        }
        binding.rlOrderType.setOnClickListener {
            showSelect(arrayList2,binding.tvOrderTypeText)
        }
        binding.rlOrderTaochan.setOnClickListener {
            showSelect(arrayList3,binding.tvOrderTaochanText)
        }
        binding.rlOrderKf.setOnClickListener {
            showSelect(arrayList4,binding.tvOrderKfText)
        }
        binding.rlOrderSm.setOnClickListener {
            showSelect(arrayList5,binding.tvOrderSmText)
        }
        binding.btOrderSave.setOnClickListener {
            val dialog = Utils.showProcessDialog(this, "正在预约,请稍后...")
            GlobalScope.launch {
                delay(1500)
                dialog.dismiss()
                runOnUiThread {
                    Toast.makeText(this@OrderActivity,"预约成功",Toast.LENGTH_SHORT).show()
                    this@OrderActivity.finish()
                }
            }
        }
        binding.ivHeaderLeft.setOnClickListener {
            finish()
        }
    }

    override fun handleHeaderEventRight1(view: View?) {
        super.handleHeaderEventRight1(view)
        finish()
    }

    private fun showSelect(arrayList: Array<String>,tv : TextView) {
        AlertDialog.Builder(this)
            .setItems(arrayList) { _, which ->
                setBackText(tv, arrayList[which])
            }.show()
    }

    /**
     * 选择约看时间
     *
     * @param tv_time_message
     * @param selectdate
     */
    private fun showSelectTime(tv_time_message: TextView, selectdate: Date) {
        val timePopupWindow = TimePopupWindow(mContext, TimePopupWindow.Type.ALL)
        timePopupWindow.showAtLocation(window.decorView, Gravity.BOTTOM, 0, 0, selectdate)
        timePopupWindow.setOnTimeSelectListener { date ->
            val currentDate: Date = getCurrentDate()
            selectDate = date
            val format = "yyyy-MM-dd HH:mm"
            selectTime = SimpleDateFormat(format, Locale.CHINA).format(date)
            setBackText(tv_time_message, selectTime)
        }
    }

    /**
     * 设置选中完数据前后的文本
     *
     * @param tv  设置的控件
     * @param str 设置的内容
     */
    private fun setBackText(tv: TextView, str: String) {
//        if (true) {
        tv.text = str
        tv.setTextColor(Color.parseColor("#0E131A"))
//        } else {
//            tv.setText(str);
//            tv.setTextColor(Color.parseColor("#C0C0C0"));
//        }
    }

    fun getCurrentDate(): Date {
        val date = Date()
        var date1: Date = date
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA)
        val s = formatter.format(date)
        try {
            date1 = formatter.parse(s)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return date1
    }

}