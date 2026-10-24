package com.example.eventvalidation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eventvalidation.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fullName = intent.getStringExtra("FULL_NAME")
        val email = intent.getStringExtra("EMAIL")
        val phone = intent.getStringExtra("PHONE")
        val studentId = intent.getStringExtra("STUDENT_ID")
        val className = intent.getStringExtra("CLASS_NAME")

        if (fullName != null) {
            val content = """
                Họ và tên: $fullName
                Email: $email
                Số điện thoại: $phone
                Mã sinh viên: $studentId
                Lớp: $className
            """.trimIndent()
            binding.tvDetailContent.text = content
        } else {
            binding.tvDetailContent.text = "Chưa có dữ liệu đăng ký."
        }

        binding.btnBackToRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
    }
}