package com.javr.todo_app

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.javr.todo_app.TaskCategory.*

class MainActivity : AppCompatActivity() {
    private val lstCategories = listOf(
        Business,
        Personal,
        Other
    )

    private val lstTask = mutableListOf(
        Task("Prueba Business", Business),
        Task("Prueba Persona", Personal),
        Task("Prueba Other", Other),
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter

    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponents()
        initListener()
        initUI()
    }

    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }


    private fun initListener() {
        fabAddTask.setOnClickListener { showDialod() }
    }

    private fun showDialod() {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)

        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()) {
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton: RadioButton = rgCategories.findViewById(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.todo_dialog_category_business) -> Business
                    getString(R.string.todo_dialog_category_personal) -> Personal
                    else -> Other
                }

                lstTask.add(Task(currentTask, currentCategory))
                updateTask()
                dialog.hide()
            }
        }

        dialog.show()
    }

    private fun initUI() {
        categoriesAdapter = CategoriesAdapter(lstCategories){ position -> updateCategorie(position) }
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        tasksAdapter = TasksAdapter(lstTask) { position -> onItemSelected(position) }
        rvTasks.layoutManager = LinearLayoutManager(this)
        rvTasks.adapter = tasksAdapter
    }

    private fun onItemSelected(position: Int) {
        lstTask[position].isSelected = !lstTask[position].isSelected
        updateTask()
    }

    private fun updateCategorie(position: Int) {
        lstCategories[position].isSelected = !lstCategories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTask()
    }
    private fun updateTask() {
        val selectedCategories: List<TaskCategory> = lstCategories.filter { it.isSelected }
        val newTasks = lstTask.filter { selectedCategories.contains(it.category) }
        tasksAdapter.lstTasks = newTasks
        tasksAdapter.notifyDataSetChanged()
    }
}