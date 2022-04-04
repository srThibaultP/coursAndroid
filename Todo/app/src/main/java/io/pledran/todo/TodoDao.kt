package io.pledran.todo

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoDao {
    @Insert
    fun insert(item: TodoEntity)

    @Update
    fun update(item: TodoEntity)

    @Query("SELECT * FROM " + TodoEntity.TABLE_NAME)
    fun getItems(): List<TodoEntity>
}