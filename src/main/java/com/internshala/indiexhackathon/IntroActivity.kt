package com.internshala.indiexhackathon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class IntroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        // You can add additional logic or elements to the introduction page if needed

        // Use a Handler to delay the launch of the main activity after the introduction page
        val handler = android.os.Handler()
        handler.postDelayed({
            val mainIntent = Intent(this@IntroActivity, MainActivity::class.java)
            startActivity(mainIntent)
            finish() // close the introduction activity
        }, 6000) // 3 seconds delay, adjust as needed
    }
}
