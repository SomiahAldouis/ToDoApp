package somiah.jad.todoapp

import android.app.Application
import somiah.jad.todoapp.TaskRepository.Companion.initialize

class TaskIntentApp: Application() {

    override fun onCreate() {
        super.onCreate()
        TaskRepository.initialize(this)
    }

}