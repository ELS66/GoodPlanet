package com.example.goodplanet.ui

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.Spannable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Base64
import android.view.*
import android.widget.*
import android.widget.AdapterView.OnItemClickListener
import com.example.goodplanet.MainActivity
import com.example.goodplanet.R
import com.example.goodplanet.base.BaseActivity
import com.example.goodplanet.utils.AesUtil
import com.example.goodplanet.utils.StringUtils
import com.example.goodplanet.utils.Utils
import java.io.*

class LoginActivity : BaseActivity() {


    /**
     * 已登录账号的用户名密码
     */
    private var userMap: HashMap<String, String> = HashMap()
    private val userlist: ArrayList<String> = arrayListOf()
    private val passwordlist: ArrayList<String> = arrayListOf()
    private lateinit var  btn_login: Button
    private lateinit var ll_login_username : LinearLayout
    private lateinit var tv_login_forget_password : TextView
    private lateinit var et_login_password : EditText
    private lateinit var et_login_username : EditText
    private lateinit var iv_login_username : ImageView
    private lateinit var iv_login_password : ImageView
    private lateinit var rl_login_username_num : RelativeLayout
    private lateinit var rl_login_password_hide : RelativeLayout
    private lateinit var rlClearUsername : RelativeLayout

    private val popupAdapter = PopupAdapter()


    private lateinit var lv_login_popupwindow: ListView

    private var popWindow:PopupWindow? = null
    private var isFlag = false
    private var AesKey = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        window.addFlags(WindowManager.LayoutParams.FLAG_SECURE)
        setContentView(R.layout.activity_login)
        initView()
        initData()
        registerListener()
    }

    fun initView() {
        btn_login = findViewById(R.id.btn_login) as Button
        btn_login.isEnabled = false
        tv_login_forget_password = findViewById(R.id.tv_login_forget_password)
        ll_login_username = findViewById(R.id.ll_login_username)
        et_login_password = findViewById(R.id.et_login_password)
        et_login_username = findViewById(R.id.et_login_username)
        iv_login_username = findViewById(R.id.iv_login_username)
        iv_login_password = findViewById(R.id.iv_login_password)
        rl_login_username_num = findViewById(R.id.rl_login_username_num)
        rl_login_password_hide = findViewById(R.id.rl_login_password_hide)
        rlClearUsername = findViewById(R.id.rl_login_clear_username)
    }


    fun initData() {
        AesKey = AesUtil.generateKey()
        val usernamestate = readstate() ?: ""
        //读取保存的多账号
        readLoginUser()
        val set = userMap.entries
        for ((key, value) in set) {
            userlist.add(key)
            try {
                passwordlist.add(AesUtil.decrypt(AesKey,value))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        if (userlist != null && userlist.size > 0) {
            rl_login_username_num.visibility = View.VISIBLE
            for (i in userlist.indices) {
                if (usernamestate == userlist[i]) {
                    rlClearUsername.visibility = View.VISIBLE
                    et_login_username.setText(userlist[i])
                    et_login_username.setSelection(et_login_username.text.length)
                    et_login_password.setText(passwordlist[i])
                }
            }
        } else {
            rl_login_username_num.visibility = View.GONE
        }
    }
    fun registerListener() {
        btn_login.setOnClickListener {
            handleLoginSuccess()
        }
        //忘记密码
        tv_login_forget_password.setOnClickListener {

        }
        rl_login_username_num.setOnClickListener{
            Utils.hideSoftKeyBoard(this)
            isFlag = popWindow != null && popWindow!!.isShowing
            if (!isFlag) {
                setPopupWindow()
            } else {
                popWindow?.dismiss()
            }
        }
        rl_login_password_hide.setOnClickListener {
            Utils.hideSoftKeyBoard(this)
            ConfirmPasswordShowOnClick()
        }
        rlClearUsername.setOnClickListener{
            et_login_username.setText("")
        }
        et_login_username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (StringUtils.isNullOrEmpty(s.toString())) {
                    rlClearUsername.visibility = View.GONE
                    et_login_password.setText("")
                    setLoginButtonBg(false)
                } else {
                    rlClearUsername.visibility = View.VISIBLE
                    if (StringUtils.isNullOrEmpty(et_login_password.text.toString())) {
                        setLoginButtonBg(false)
                    } else {
                        setLoginButtonBg(true)
                    }
                }
            }
        })

        et_login_password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (StringUtils.isNullOrEmpty(s.toString())) {
                    setLoginButtonBg(false)
                } else {
                    if (StringUtils.isNullOrEmpty(et_login_username.text.toString())) {
                        setLoginButtonBg(false)
                    } else {
                        setLoginButtonBg(true)
                    }
                }
            }
        })
    }


    private fun readstate(): String? {
        val sharedPreferences = getSharedPreferences("login_state", MODE_PRIVATE)
        return sharedPreferences.getString("username", "")
    }

    //读取多账号选择
    private fun readLoginUser() {
        val sharedPreferences = getSharedPreferences("login_userinfo", MODE_PRIVATE)
        try {
            sharedPreferences.getString("login_userinfo", "")?.let { stringToMap(it) }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
    }

    private fun stringToMap(mapString: String): Map<String, String> {
        val mobileBytes = Base64.decode(mapString.toByteArray(), Base64.DEFAULT)
        val byteArrayInputStream = ByteArrayInputStream(mobileBytes)
        val objectInputStream = ObjectInputStream(byteArrayInputStream)
        userMap = objectInputStream.readObject() as HashMap<String, String>
        objectInputStream.close()
        return userMap
    }

    /**
     * 设置登录按钮颜色
     */
    private fun setLoginButtonBg(couldLogin: Boolean) {
        if (couldLogin) {
            btn_login.background = resources.getDrawable(R.drawable.shape_arc_background_2dp_297eff)
            //            btn_login.setClickable(true);
            btn_login.isEnabled = true
        } else {
            btn_login.background = resources.getDrawable(R.drawable.shape_arc_background_2dp_a9cbff)
            //            btn_login.setClickable(false);
            btn_login.isEnabled = false
        }
    }

    /**
     * 判断  确认密码  处眼睛是否是可见状态  true可见
     */
    private var isConfirmPassWordShow = false

    private fun ConfirmPasswordShowOnClick() {
        if (!isConfirmPassWordShow) {
            //设置EditText文本为可见的
            et_login_password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            iv_login_password.setImageResource(R.drawable.login_password_up)
        } else {
            //设置EditText文本为隐藏的
            et_login_password.transformationMethod = PasswordTransformationMethod.getInstance()
            iv_login_password.setImageResource(R.drawable.login_password_down)
        }
        isConfirmPassWordShow = !isConfirmPassWordShow
        et_login_password.postInvalidate()
        //切换后将EditText光标置于末尾
        val charSequence: CharSequence = et_login_password.text
        if (charSequence is Spannable) {
            Selection.setSelection(charSequence, charSequence.length)
        }
    }

    private fun setPopupWindow() {
        val view: View = LayoutInflater.from(this).inflate(R.layout.login_popupwindow, null)
        lv_login_popupwindow = view.findViewById<View>(R.id.lv_login_popupwindow) as ListView
        lv_login_popupwindow.adapter = popupAdapter
        popWindow = PopupWindow(view, et_login_username.width, ViewGroup.LayoutParams.WRAP_CONTENT)
        popWindow!!.isTouchable = true
        popWindow!!.isFocusable = true
        popWindow!!.isOutsideTouchable = false
        val dw = ColorDrawable(-0x1)
        popWindow!!.setBackgroundDrawable(dw)
        //        if (Build.VERSION.SDK_INT != 24) {
//            // android 7.0 不使用此方法
//            popWindow.update();
//        }
        popWindow!!.showAsDropDown(et_login_username)
        lv_login_popupwindow.setOnItemClickListener(OnItemClickListener { parent, view, position, id ->
            et_login_username.setText(userlist[position])
            et_login_username.setSelection(userlist[position].length)
            et_login_password.setText(passwordlist[position])
            popWindow!!.dismiss()
        })
    }

    //删除多账号选择里面的账号
    private fun deletLoginUser(map: MutableMap<String, String>, string: String) {
        val sharedPreferences = getSharedPreferences("login_userinfo", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        map.remove(string)
        var userString: String? = ""
        try {
            userString = mapToString()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        editor.putString("login_userinfo", userString)
        editor.commit()
    }

    /**
     * 处理登录成功后的跳转
     * @param result
     */
    fun handleLoginSuccess() {
        seaveLoginUser(et_login_username.text.toString(), et_login_password.text.toString())
        savestate(et_login_username.text.toString())
        if("root" == et_login_password.text.toString().trim()) {
            val intent = Intent(this,RootMainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    //保存登陆页面多账号选择
    private fun seaveLoginUser(userName: String, passWord: String) {
        val sharedPreferences = getSharedPreferences("login_userinfo", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        var desPwd = ""
        var userString: String? = ""
        try {
            desPwd = AesUtil.encrypt(AesKey,et_login_password.text.toString().trim())
            userMap[userName] = desPwd
            userString = mapToString()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        editor.putString("login_userinfo", userString)
        editor.commit()
    }

    /**
     * 保存当前登录用户
     */
    private fun savestate(username: String) {
        val sharedPreferences = getSharedPreferences("login_state", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("password", "") //清除，过段时间可以去掉
        editor.commit()
    }

    //把map格式转换成String类型，保存String
    private fun mapToString(): String? {
        // 实例化一个ByteArrayOutputStream对象，用来装载压缩后的字节文件。
        val byteArrayOutputStream = ByteArrayOutputStream()
        // 然后将得到的字符数据装载到ObjectOutputStream
        val objectOutputStream = ObjectOutputStream(byteArrayOutputStream)
        // writeObject 方法负责写入特定类的对象的状态，以便相应的 readObject 方法可以还原它
        objectOutputStream.writeObject(userMap)
        // 最后，用Base64.encode将字节文件转换成Base64编码保存在String中
        val mapString = String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT))
        // 关闭objectOutputStream
        objectOutputStream.close()
        return mapString
    }

    inner class PopupAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return userlist.size
        }

        override fun getItem(position: Int): Any {
            return userlist.get(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView : View?, parent: ViewGroup): View {
            var convertView = convertView
            var holder: ViewHolder? = null
            if (convertView == null) {
                holder = ViewHolder()
                convertView = LayoutInflater.from(this@LoginActivity)
                    .inflate(R.layout.login_popupwindow_item, null)
                holder.tv_login_popupwindow =
                    convertView.findViewById<View>(R.id.tv_login_popupwindow) as TextView
                holder.iv_login_popupwindow =
                    convertView.findViewById<View>(R.id.iv_login_popupwindow) as ImageView
                convertView.tag = holder
            } else {
                holder = convertView.tag as ViewHolder
            }
            holder.tv_login_popupwindow!!.text = userlist[position]
            holder.tv_login_popupwindow!!.setOnClickListener {
                et_login_username.setText(userlist[position])
                et_login_username.setSelection(et_login_username.text.length)
                et_login_password.setText(passwordlist[position])
                et_login_password.setSelection(et_login_password.text.length)
                popWindow?.dismiss()
            }
            holder.iv_login_popupwindow!!.setOnClickListener {
                deletLoginUser(userMap as MutableMap<String, String>, userlist[position])
                userlist.removeAt(position)
                passwordlist.removeAt(position)
                popupAdapter.notifyDataSetChanged()
            }
            return convertView!!
        }

    }

    inner class ViewHolder {
        var tv_login_popupwindow: TextView? = null
        var iv_login_popupwindow: ImageView? = null
    }

}