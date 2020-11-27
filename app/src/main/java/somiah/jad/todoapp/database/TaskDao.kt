package somiah.jad.todoapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import somiah.jad.todoapp.Task
import java.util.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAllTasks(): LiveData< List<Task>>


    @Query("SELECT * FROM task WHERE taskId=(:id)")
    fun getTask(id: UUID): LiveData<Task?>

    @Insert
    fun addTask(task : Task)


}