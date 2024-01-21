package com.internshala.indiexhackathon

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIMEOUT: Long = 3000 // 2 seconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Load the GIF into the ImageView using Glide
        val imageViewLogo: ImageView = findViewById(R.id.imageViewLogo)
        Glide.with(this)
            .asGif()
            .load(R.drawable.background_splash_screen)
            .into(imageViewLogo)

        // Use a Handler to delay the launch of the main activity
        Handler().postDelayed({
            val mainIntent = Intent(this@SplashActivity, IntroActivity::class.java)
            startActivity(mainIntent)
            finish() // close the splash activity
        }, SPLASH_TIMEOUT)
    }
}
