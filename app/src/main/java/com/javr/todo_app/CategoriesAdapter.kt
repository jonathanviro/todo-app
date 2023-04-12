package com.javr.todo_app

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemSelectedListener
import androidx.recyclerview.widget.RecyclerView

class CategoriesAdapter(private val lstCategories: List<TaskCategory>, private val onItemSelected: (Int) -> Unit) : RecyclerView.Adapter<CategoriesViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_category, parent, false)
        return CategoriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(lstCategories[position], onItemSelected)
    }

    override fun getItemCount(): Int = lstCategories.size
}
