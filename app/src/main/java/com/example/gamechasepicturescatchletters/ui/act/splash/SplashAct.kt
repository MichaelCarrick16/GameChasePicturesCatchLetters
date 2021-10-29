package com.example.gamechasepicturescatchletters.ui.act.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.gamechasepicturescatchletters.R
import com.example.gamechasepicturescatchletters.ui.act.main.MainAct

class SplashAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_splash)
        initViews()
    }

    private fun initViews() {
        Handler(Looper.getMainLooper()).postDelayed({
            /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(this, MainAct::class.java)
            startActivity(mainIntent)
            finish()
        }, 3000)

//        Handler().postDelayed(object : Runnable{
//            override fun run() {
//                // handle logic
//            }
//        },3000)
    }
}