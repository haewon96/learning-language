package org.sungshin.lnk.learningnorthkorean

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().apply {
            postDelayed({
                startActivity<MapsActivity>("message" to "지역 정보")

//                finish() // splash 없애고 map만 띄우기
            }, 2000) // 인위적으로 2초의 delay 주기
        }
    }
}
