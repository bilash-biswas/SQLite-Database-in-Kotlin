package com.example.sqlitedatabasekotlin

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlitedatabasekotlin.databinding.ActivityNewStudentBinding

class NewStudentActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNewStudentBinding
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewStudentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addNewStudent.setOnClickListener{
            addNewStudent()
        }

    }
    fun addNewStudent(){
        if (binding.studentName.text.trim().toString().isEmpty() || binding.subjectName.text.trim().toString().isEmpty() ||
            binding.contactNumber.text.trim().toString().isEmpty() || binding.salary.text.trim().toString().isEmpty()){
            Utility.Toast(applicationContext, "All are required")
            return
        }
        dbHelper = DatabaseHelper(applicationContext)
        dbHelper.insetStudent(
            binding.studentName.text.trim().toString(),
            binding.subjectName.text.trim().toString(),
            binding.contactNumber.text.trim().toString(),
            binding.salary.text.trim().toString(),
        )
        Utility.Toast(applicationContext, "Student added Successfully")
        startActivity(Intent(applicationContext, MainActivity::class.java))
        finish()
    }
}