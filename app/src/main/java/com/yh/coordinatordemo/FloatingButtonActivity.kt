package com.yh.coordinatordemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View

class FloatingButtonActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating_button)
    }

    fun onclick(view: View) {
        var snackBar=Snackbar.make(view,"hello word",Snackbar.LENGTH_SHORT)
        snackBar.show()
    }
}
