package com.example.sqlitedatabasekotlin

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter(val context: Context, val mList : ArrayList<StudentModel>) :
    RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        val student = mList.get(position)
        holder.name.setText(student.name)
        holder.subject.setText(student.subject)
        holder.contact.setText(student.contact)
        holder.salary.setText(student.salary)

        holder.itemView.setOnClickListener{
            val intent = Intent(context, UpdateActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            intent.putExtra("id", student.id)
            intent.putExtra("name", student.name)
            intent.putExtra("subject", student.subject)
            intent.putExtra("contact", student.contact)
            intent.putExtra("salary", student.salary)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val name = itemView.findViewById<TextView>(R.id.studentName)
        val subject = itemView.findViewById<TextView>(R.id.subjectName)
        val contact = itemView.findViewById<TextView>(R.id.contactNumber)
        val salary = itemView.findViewById<TextView>(R.id.salary)

    }
}