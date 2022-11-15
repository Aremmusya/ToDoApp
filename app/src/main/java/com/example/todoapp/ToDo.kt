package com.example.todoapp
//создаем дата класс, через который будут предоставляться данные в ресайкл, с 2мя параметрами
data class ToDo (
    val title: String,
    var isChecked: Boolean = false
)