package com.example.project13.Activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.project13.databinding.ActivityIntroBinding
import kotlin.jvm.java

class IntroActivity : BaseActivity() {
    private lateinit var binding : ActivityIntroBinding
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnStart.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
        }
}