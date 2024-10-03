package com.example.sqlitedatabasekotlin

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlitedatabasekotlin.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var dbHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(applicationContext)
        binding.studentName.setText(intent.getStringExtra("name"))
        binding.subjectName.setText(intent.getStringExtra("subject"))
        binding.contactNumber.setText(intent.getStringExtra("contact"))
        binding.salary.setText(intent.getStringExtra("salary"))

        binding.updateStudent.setOnClickListener{
            updateStudent()
        }

        binding.deleteStudent.setOnClickListener{
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Delete Student")
            dialog.setMessage("Do you to delete??")
            dialog.setPositiveButton("Yes"){
                _, _ ->
                dbHelper.deleteStudent(intent.getStringExtra("id")!!.toInt())
                Utility.Toast(applicationContext, "Student deleted")
                startActivity(Intent(applicationContext, MainActivity::class.java))
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
            dialog.setNeutralButton("Cancel"){
                _,_ ->
                dialog.setOnDismissListener{
                    a ->
                    a.dismiss()
                }
            }
            dialog.create().show()
        }

    }

    fun updateStudent() {
        val result = dbHelper.updateStudent(
            intent.getStringExtra("id")!!.toInt(),
            binding.studentName.text.trim().toString(),
            binding.subjectName.text.trim().toString(),
            binding.contactNumber.text.trim().toString(),
            binding.salary.text.trim().toString()
        )
        if (result > 0){
            Utility.Toast(applicationContext, "Update Successfully.")
            startActivity(Intent(applicationContext, MainActivity::class.java))
            finish()
        }
    }

}