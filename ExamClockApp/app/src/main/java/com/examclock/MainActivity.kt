package com.examclock

import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*
import android.widget.TextView
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    private lateinit var timeText: TextView
    private val handler = Handler(Looper.getMainLooper())
    private val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())

    private val updateTimeTask = object : Runnable {
        override fun run() {
            val currentTime = timeFormat.format(Date())
            timeText.text = currentTime
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        timeText = findViewById(R.id.timeText)
        handler.post(updateTimeTask)
    }

    override fun onDestroy() {
        handler.removeCallbacks(updateTimeTask)
        super.onDestroy()
    }
}