package io.pledran.todo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.pledran.todo.TodoEntity.Companion.TABLE_NAME
import java.util.*

@Entity(tableName = TABLE_NAME)
data class TodoEntity(
    @PrimaryKey @ColumnInfo(name = "id") var id: String = UUID.randomUUID().toString(),
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "done") var done: Boolean
) {
    companion object {
        const val TABLE_NAME = "Todo"
    }
}