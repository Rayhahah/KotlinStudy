package com.rayhahah.kotlinstudy.test.list

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.rayhahah.kotlinstudy.R
import com.rayhahah.kotlinstudy.test.adapter.ListAdapter
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    lateinit var listAdapter: ListAdapter
    var dataStr = "hello,kotlin"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        initRv()
    }

    private fun initRv() {
        var data = ArrayList<String>()
//        dataStr.indices.mapTo(data) { "index$it," + dataStr[it] }
        //效果和上面是一样的
        for (index in dataStr.indices) {
            data.add("index$index,${dataStr[index]}")
        }

        listAdapter = ListAdapter(R.layout.item_list)
        listAdapter.setNewData(data)
        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.adapter = listAdapter
    }
}

