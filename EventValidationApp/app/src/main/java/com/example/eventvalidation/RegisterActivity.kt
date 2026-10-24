package com.example.eventvalidation

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.eventvalidation.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegister.setOnClickListener {
            if (validateInputs()) {
                val fullName = binding.etFullName.text.toString().trim()
                val email = binding.etEmail.text.toString().trim()
                val phone = binding.etPhone.text.toString().trim()
                val studentId = binding.etStudentId.text.toString().trim()
                val className = binding.etClass.text.toString().trim()

                // Hiển thị Snackbar thành công kèm Action chuyển màn hình
                Snackbar.make(binding.rootView, "Đăng ký thành công!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Xem Chi Tiết") {
                        val intent = Intent(this, DetailActivity::class.java).apply {
                            putExtra("FULL_NAME", fullName)
                            putExtra("EMAIL", email)
                            putExtra("PHONE", phone)
                            putExtra("STUDENT_ID", studentId)
                            putExtra("CLASS_NAME", className)
                        }
                        startActivity(intent)
                    }
                    .show()
            }
        }

        // Button chuyển trực tiếp qua màn hình Chi tiết
        binding.btnNavToDetailDirect.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validateInputs(): Boolean {
        var isValid = true

        val fullName = binding.etFullName.text.toString().trim()
        val email = binding.etEmail.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()
        val studentId = binding.etStudentId.text.toString().trim()
        val className = binding.etClass.text.toString().trim()

        // Validate Họ tên
        if (fullName.isEmpty()) {
            binding.tilFullName.error = "Vui lòng nhập họ và tên"
            isValid = false
        } else {
            binding.tilFullName.error = null
        }

        // Validate Email
        if (email.isEmpty()) {
            binding.tilEmail.error = "Vui lòng nhập email"
            isValid = false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.tilEmail.error = "Email không đúng định dạng"
            isValid = false
        } else {
            binding.tilEmail.error = null
        }

        // Validate SĐT (Định dạng Việt Nam)
        if (phone.isEmpty()) {
            binding.tilPhone.error = "Vui lòng nhập số điện thoại"
            isValid = false
        } else if (!phone.matches(Regex("^(0[3|5|7|8|9])+([0-9]{8})$"))) {
            binding.tilPhone.error = "Số điện thoại không hợp lệ (10 chữ số)"
            isValid = false
        } else {
            binding.tilPhone.error = null
        }

        // Validate MSV
        if (studentId.isEmpty()) {
            binding.tilStudentId.error = "Vui lòng nhập mã sinh viên"
            isValid = false
        } else {
            binding.tilStudentId.error = null
        }

        // Validate Lớp
        if (className.isEmpty()) {
            binding.tilClass.error = "Vui lòng nhập lớp"
            isValid = false
        } else {
            binding.tilClass.error = null
        }

        return isValid
    }
}