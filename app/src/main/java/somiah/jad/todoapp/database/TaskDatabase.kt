package somiah.jad.todoapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import somiah.jad.todoapp.Task

@Database (entities = [Task::class] , version = 1)
@TypeConverters(TaskTypeConverters :: class)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun taskDao() : TaskDao

}