package com.example.goodplanet.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.goodplanet.R
import com.example.goodplanet.ui.FileActivity
import com.example.goodplanet.ui.OrderActivity


class RootHomeFragment : Fragment() {

    private lateinit var iv_ai : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_root, container, false)
        initView(view)
        return view
    }

    fun initView(view : View) {
        iv_ai = view.findViewById(R.id.iv_ai)
        iv_ai.setOnClickListener {
            val intent = Intent(view.context,OrderActivity::class.java)
            startActivity(intent)
        }
    }


}