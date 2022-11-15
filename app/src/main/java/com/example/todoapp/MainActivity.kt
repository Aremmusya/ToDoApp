package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: ToDoAdapter //заранее подготавливаем переменную , типа нашего адаптера

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todoAdapter = ToDoAdapter(mutableListOf())//запускаем ее с параметром списка
        rvToDoItems.adapter = todoAdapter//настраиваем на ресайкл наш адаптер
        rvToDoItems.layoutManager = LinearLayoutManager(this)
        btnAddToDo.setOnClickListener{//настраиваем кнопки
            val todoTitle = etToDoTitle.text.toString()
            if (todoTitle.isNotEmpty()){
                val todo = ToDo(todoTitle)
                todoAdapter.addToDo(todo)
                etToDoTitle.text.clear()
            }
        }
        btnDeleteToDo.setOnClickListener {
            todoAdapter.deleteDoneTodos()
        }




    }
}