package com.example.goodplanet.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goodplanet.R
import com.example.goodplanet.adapter.FileAdapter
import com.example.goodplanet.base.BaseActivity
import java.util.ArrayList

class FileActivity : BaseActivity() {

    private lateinit var rv :RecyclerView
    private lateinit var cl_add : ConstraintLayout
    private lateinit var cl_commit : ConstraintLayout
    private lateinit var adapter : FileAdapter
    private lateinit var list : ArrayList<String>
    private var num = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setView(R.layout.activity_file)
        setTitle("档案管理")
        initView()
        initData()
    }

    fun initView() {
        rv = findViewById(R.id.rv_file)
        cl_add = findViewById(R.id.cl_add)
        cl_commit = findViewById(R.id.cl_commit)
    }

    fun initData() {
        list = ArrayList()
        list.add(num.toString())
        adapter = FileAdapter(this,list)
        rv.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rv.adapter = adapter
        cl_add.setOnClickListener {
            num++
            list.add(num.toString())
            adapter.notifyDataSetChanged()
        }
        cl_commit.setOnClickListener {
            Toast.makeText(this,"提交成功",Toast.LENGTH_SHORT).show()
            finish()
        }
    }


}