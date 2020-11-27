package somiah.jad.todoapp

import java.util.*

data class Task(

    val taskId: UUID = UUID.randomUUID(),
    var taskTitle: String = "",
    var taskDetails: String = "",
    var taskDate: Date = Date(),
    var taskCategory: String = "")
