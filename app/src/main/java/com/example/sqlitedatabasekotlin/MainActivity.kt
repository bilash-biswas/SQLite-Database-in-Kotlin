package com.example.sqlitedatabasekotlin

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sqlitedatabasekotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var dbHandler : DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHandler = DatabaseHelper(this)

        binding.addNewStudent.setOnClickListener{
            startActivity(Intent(applicationContext, NewStudentActivity::class.java))
        }

        binding.allStudent.setOnClickListener{
            startActivity(Intent(applicationContext, AllStudentActivity::class.java))
        }
        binding.totalSalary.text = "Total Salary : " + dbHandler.getTotalSalary().toString()

    }

    override fun onBackPressed() {

        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Exit!")
        dialog.setMessage("Do you want to exit??")
        dialog.setPositiveButton("Yes"){
                _, _ ->
            super.onBackPressed()
        }
        dialog.setNeutralButton("No"){
                _,_ ->
            dialog.setOnDismissListener{
                    a ->
                a.dismiss()
            }
        }
        dialog.create().show()
    }
}