package com.example.sqlitedatabasekotlin

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_NAME ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_NAME TEXT,"
                + "$COLUMN_SUBJECT TEXT,"
                + "$COLUMN_CONTACT_NUMBER TEXT,"
                + "$COLUMN_SALARY TEXT)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insetStudent(name: String, subject: String, contact: String, salary: String): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_SUBJECT, subject)
            put(COLUMN_CONTACT_NUMBER, contact)
            put(COLUMN_SALARY, salary)
        }
        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun getAllStudent(): Cursor {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }

    fun updateStudent(
        id: Int,
        name: String,
        subject: String,
        contact: String,
        salary: String
    ): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_SUBJECT, subject)
            put(COLUMN_CONTACT_NUMBER, contact)
            put(COLUMN_SALARY, salary)
        }
        return db.update(TABLE_NAME, contentValues, "$COLUMN_ID=?", arrayOf(id.toString()))
    }

    fun deleteStudent(id: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id.toString()))
    }

    fun getTotalSalary(): Int {
        val db = this.readableDatabase

        val cursor: Cursor = db.rawQuery("SELECT SUM($COLUMN_SALARY) FROM $TABLE_NAME", null)
        var totalSalary = 0
        if (cursor.moveToFirst()) {
            totalSalary = cursor.getInt(0)
        }
        cursor.close()

        return totalSalary
    }

    companion object {
        private const val DATABASE_NAME = "StudentDatabase.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "student"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_SUBJECT = "subject"
        private const val COLUMN_CONTACT_NUMBER = "contact_number"
        private const val COLUMN_SALARY = "salary"
    }
}