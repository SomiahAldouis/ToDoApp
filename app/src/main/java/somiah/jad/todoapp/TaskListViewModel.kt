package somiah.jad.todoapp

import androidx.lifecycle.ViewModel

class TaskListViewModel : ViewModel() {

    private val taskRepository = TaskRepository.get()
    val allTasks = taskRepository.getAllTasks()

}