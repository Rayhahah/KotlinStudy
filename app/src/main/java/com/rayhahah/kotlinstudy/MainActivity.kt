package com.rayhahah.kotlinstudy

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rayhahah.kotlinstudy.learn.basicType.BasicTypeActivity
import com.rayhahah.kotlinstudy.test.list.ListActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_to_list.setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }

        btn_to_request.setOnClickListener {
            startActivity(Intent(this, RequestActivity::class.java))
        }

        btn_to_basic_type.setOnClickListener {
            startActivity(Intent(this, BasicTypeActivity::class.java))
        }
    }
}
