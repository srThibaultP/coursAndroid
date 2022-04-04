package io.pledran.todo

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter : ListAdapter<TodoEntity, TodoAdapter.TodoViewHolder>(DIFF_UTIL_ITEM_CALLBACK) {

    companion object {
        private val DIFF_UTIL_ITEM_CALLBACK =
            object : DiffUtil.ItemCallback<TodoEntity>() {
                override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity) = oldItem.title == newItem.title && oldItem.id == newItem.id
                override fun areContentsTheSame(oldItem: TodoEntity, newItem: TodoEntity) = oldItem == newItem
            }
    }

    var listener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(LayoutInflater.from(parent.context), parent)

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) =
        holder.bind(getItem(position))

    inner class TodoViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.todo_item, parent, false)) {

        init {
            val cbDone = itemView.findViewById<CheckBox>(R.id.cbDone)
            cbDone.setOnClickListener {
                val todoEntity = getItem(adapterPosition)
                todoEntity.done = cbDone.isChecked

                listener?.onTodoClicked(todoEntity)
            }
        }

        fun bind(todoEntity: TodoEntity) {
            val cbDone = itemView.findViewById<CheckBox>(R.id.cbDone)
            val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)

            cbDone.isChecked = todoEntity.done
            tvTitle.text = todoEntity.title
        }
    }

    interface OnClickListener {
        fun onTodoClicked(todoEntity: TodoEntity)
    }

}