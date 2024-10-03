package com.example.sqlitedatabasekotlin

import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AllStudentActivity : AppCompatActivity() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var adapter : CustomAdapter
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var studentModel: StudentModel
    private lateinit var studentList : ArrayList<StudentModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_student)

        dbHelper = DatabaseHelper(this)
        studentList = ArrayList()
        adapter = CustomAdapter(applicationContext, studentList)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        retrieveStudent()

        recyclerView.adapter = adapter


    }
    fun retrieveStudent(){

        val cursor = dbHelper.getAllStudent()

        if (cursor.moveToNext()){
            do {
                studentModel = StudentModel(
                    cursor.getString(cursor.getColumnIndexOrThrow("id")),
                    cursor.getString(cursor.getColumnIndexOrThrow("name")),
                    cursor.getString(cursor.getColumnIndexOrThrow("subject")),
                    cursor.getString(cursor.getColumnIndexOrThrow("contact_number")),
                    cursor.getString(cursor.getColumnIndexOrThrow("salary")),
                )
                studentList.add(studentModel)
            }while (cursor.moveToNext())

        }
        cursor.close()
        adapter.notifyDataSetChanged()
    }

}