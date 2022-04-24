package com.example.goodplanet.base;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.goodplanet.R;
import com.example.goodplanet.utils.PermissionUtils;

import androidx.annotation.NonNull;

public abstract class BaseActivity extends Activity implements OnClickListener {

    public boolean isLeftNavigationEnable = false;

    public BaseActivity() {
        mContext = this;
    }

    protected Context mContext;

    protected BaseLayout baseLayout;


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (getParent() != null) {
                getParent().onKeyDown(keyCode, event);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 设置布局
     *
     * @param layoutResId
     *            布局id
     */
    protected void setView(int layoutResId) {
        baseLayout = new BaseLayout(this, layoutResId);
        baseLayout.setFitsSystemWindows(true);
        baseLayout.setClipToPadding(true);
        setContentView(baseLayout);
        baseLayout.ll_header_left.setOnClickListener(this);
        baseLayout.ll_header_right.setOnClickListener(this);
        baseLayout.ll_header_right_middle.setOnClickListener(this);
    }



    /**
     * 设置统一error布局
     *
     * @param layoutResId
     * @param isCommonErrorLayout
     *            布局id
     */
    protected void setView(int layoutResId , boolean isCommonErrorLayout) {
        if (!isCommonErrorLayout) {
            setView(layoutResId);
        } else {
            baseLayout = new BaseLayout(this, layoutResId);
            baseLayout.setFitsSystemWindows(true);
            baseLayout.setClipToPadding(true);
            setContentView(baseLayout);
            baseLayout.ll_header_left.setOnClickListener(this);
            baseLayout.ll_header_right.setOnClickListener(this);
            baseLayout.ll_header_right_middle.setOnClickListener(this);
        }
    }

    protected void setTitle(String title) {
        baseLayout.setTitle(title);
    }
    protected void setTitle(String title,int titleColor) {
        baseLayout.setTitle(title,titleColor);
    }

    protected void setRight1(String title) {
        baseLayout.setRight1(title);
    }
    protected void setRight1Drawable(int id){
        baseLayout.setRight1Drawable(id);
    }
    protected void setRight1DrawableNew(int id){
        baseLayout.setRight1DrawableNew(id);
    }
    protected void setRightMiddleDrawable(int id){
        baseLayout.setRightMiddleDrawable(id);
    }
    protected void setRightMiddleDrawableNew(int id){
        baseLayout.setRightMiddleDrawableNew(id);
    }

    protected void setShowMiddleDrawable(int id){
        baseLayout.setShowMiddleDrawable(id);
    }

    protected void setLeft(String left){
        baseLayout.setLeft(left);
    }
    protected void setLeft(String left,int resId){
        baseLayout.setLeft(left,resId);
    }
    protected void setRight1TextColor(int color){
        baseLayout.setRight1TextColor(color);
    }
    protected void hideHeaerline(){
        baseLayout.hideHeaderLine();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (mContext == this) {
            super.onCreate(savedInstanceState);
        }
        startBroadcastReceiver();
        Log.i("nameOfActivity", getClass().getSimpleName());
    }

    private void startBroadcastReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("finish");
        registerReceiver(mFinishReceiver, filter);
    }

    private BroadcastReceiver mFinishReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("finish".equals(intent.getAction())) {
                finish();
            }
        }

    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mFinishReceiver);
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_header_left:
                if(isLeftNavigationEnable){
                    handleHeaderEventlift();
                } else{
                    if (isClickbleLeft()) {
                        handleHeaderEventlift();
                    } else {
                        finish();
                    }
                }
                break;
            case R.id.ll_header_right:
                handleHeaderEventRight1(baseLayout.ll_header_right);
                break;
            case R.id.ll_header_right_middle:
                handleHeaderEventRight1(baseLayout.ll_header_right_middle);
                break;
            default:
                break;
        }
    }

    private boolean isClickbleLeft() {
//        return this instanceof BangBrowserActivity;
        return false;
    }

    protected void handleHeaderEventRight1(View view) {
    }

    protected void handleHeaderEventlift() {

    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionUtils.onRequestPermissionsResult(this,requestCode,permissions,grantResults);
    }

}
