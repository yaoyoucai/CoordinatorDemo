package com.yh.coordinatordemo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun likeZhihu(view: View) {
        var intent=Intent(this,LikeZhihuBottomActivity::class.java)
        startActivity(intent)
    }

    fun floatingButton(view: View) {
        var intent=Intent(this,FloatingButtonActivity::class.java)
        startActivity(intent)
    }
}
