package com.example.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView

import kotlinx.android.synthetic.main.item_todo.view.*
//класс, где в параметре переменная - список из класса To/Do, пренадлежит типу Ресайклвью с методом алдаптер, этого же класса и метода подкласса
class ToDoAdapter (private val todos: MutableList<ToDo>): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>(){
    class ToDoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    //подкласс с параметром итемвью вью пренадлежащему типу ресайклвью методом раздувания вьюхолдер с параметром его же итемвью
    //стандартная запись адаптера, преобразуем из него 3 функции:крейт байнд и гетитем

    //фун-я добавления тодо
    fun addToDo(todo: ToDo){
        todos.add(todo)
        notifyItemInserted(todos.size -1) //уведомление адаптора об изменении позиции списка
    }
    //фун-я удаления ТОЛЬКО отмеченных галкой текст
    fun deleteDoneTodos(){
        todos.removeAll { TODO ->
            TODO.isChecked
        }
        notifyDataSetChanged()
    }

    //дополнение к байндвью, которая проверяет галочку и зачеркивает текст
    private fun toggeleStrikeThrouth(tvTodoTitle: TextView,isChecked: Boolean){
        if (isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    //тут возвращаем и преобразовываем класс (из xml item_todo делаем картинку, которая работает с котлин кодом)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    //тут задаем данные для списка тодо, используя доп фун-ю toggeleStrikeThrouth
    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val curToDo = todos[position]
        holder.itemView.apply {
            tvToDoTitle.text = curToDo.title
            cbDone.isChecked = curToDo.isChecked
            toggeleStrikeThrouth(tvToDoTitle, curToDo.isChecked)
            cbDone.setOnCheckedChangeListener { _, isChecked ->
                toggeleStrikeThrouth(tvToDoTitle, isChecked)
                curToDo.isChecked = !curToDo.isChecked
            }
        }

    }
    //тут указываем количество - размер списка тодос
    override fun getItemCount(): Int {
        return todos.size

    }
}