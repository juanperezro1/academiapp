package com.academiapp.activities

import android.content.Context
import com.academiapp.activities.PersonDModel.Persons
import androidx.recyclerview.widget.RecyclerView
import com.academiapp.activities.EditPersonAddAdapter.Viewholder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.academiapp.R
import android.widget.TextView

class EditPersonAddAdapter(context: Context?, private val testmodelList: List<Persons>) :
    RecyclerView.Adapter<Viewholder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.editpersonlistlayout, parent, false)
        return Viewholder(view)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val txt2 = testmodelList[position].first_name + " " +
                testmodelList[position].last_name
        holder.setData(txt2)
    }

    override fun getItemCount(): Int {
        return testmodelList.size
    }

    inner class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView1: TextView
        private val textView2: TextView? = null
        fun setData(tx2: String?) {
            textView1.text = tx2
        }

        init {
            textView1 = itemView.findViewById(R.id.txt_Nombre_detail)
        }
    }
}