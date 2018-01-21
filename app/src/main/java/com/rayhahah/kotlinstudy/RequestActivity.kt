package com.rayhahah.kotlinstudy

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_request.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import java.net.URL

class RequestActivity : AppCompatActivity() {

    private val url = "http://www.wanandroid.com/"

    companion object {
        private val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private val URL = "http://api.openweathermap.org/data/2.5/" +
                "forecast/daily?mode=json&units=metric&cnt=7"
        private val COMPLETE_URL = "$URL&APPID=$APP_ID&q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)

        btn_thread_request.setOnClickListener {

            //            Thread({
//                val text = URL("http://samples.openweathermap.org/data/2.5/forecast?id=524901&appid=385ed8fb76d74873f3b2904d1914bd60").readText()
//                l(text)
//            }).start()
            thread({
                while (true) {
                    l("hh")
                    Thread.sleep(3000)
                }
            })
        }

        btn_async_request.setOnClickListener {
            doAsync {
                val readText = URL(COMPLETE_URL + "").readText()
                uiThread {
                    toast(readText)
                }
            }
        }
    }
}
