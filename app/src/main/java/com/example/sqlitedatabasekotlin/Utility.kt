package com.example.sqlitedatabasekotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

class Utility {
    companion object{
        fun Toast(context: Context, text : String){
            return Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }

        /*fun intent(context: Context, secondActivity : Class<out Activity>, isFinished : Boolean){
            val intent = Intent(context, secondActivity)
            context.startActivity(intent)
            if (isFinished && secondActivity is Activity){
                secondActivity.finish()
            }
        }*/
    }
}