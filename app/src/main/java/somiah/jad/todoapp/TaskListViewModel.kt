package somiah.jad.todoapp

import androidx.lifecycle.ViewModel
import java.util.*

class TaskListViewModel : ViewModel() {

    val tasks = mutableListOf<Task>()
    init {
        for (i in 0 until 10) {
            val task = Task()
            task.taskTitle = "title $i"
            task.taskDetails = "details lablablablablablab  $i"
            task.taskDate = Date()
            tasks += task
        }
    }

//    private val taskRepository = TaskRepository.get()
//    val allTasks = taskRepository.getAllTasks()

}