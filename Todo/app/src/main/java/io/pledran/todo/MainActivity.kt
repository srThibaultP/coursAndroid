package io.pledran.todo

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class MainActivity : AppCompatActivity() {

    val db by lazy {
        Room.databaseBuilder(applicationContext, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .allowMainThreadQueries()
            .build()
    }

    lateinit var adapter: TodoAdapter

    val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerView) }
    val etTodo by lazy { findViewById<EditText>(R.id.etTodo) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etTodo.setOnEditorActionListener { v, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    db.todoDao().insert(
                        TodoEntity(
                            title = etTodo.text.toString(),
                            done = false
                        )
                    )
                    etTodo.text.clear()
                    loadItems()
                    true
                }
                else -> false
            }
        }

        adapter = TodoAdapter().apply {
            listener = object: TodoAdapter.OnClickListener {
                override fun onTodoClicked(todoEntity: TodoEntity) {
                    db.todoDao().update(todoEntity)
                }
            }
        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        loadItems()
    }

    private fun loadItems() {
        adapter.submitList(db.todoDao().getItems())
    }
}