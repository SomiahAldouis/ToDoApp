package somiah.jad.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentToDo : Fragment() {

    private val toDoViewModel:TaskListViewModel by lazy{
        ViewModelProviders.of(this).get(TaskListViewModel::class.java)
    }
    private lateinit var toDoRecyclerView: RecyclerView
    private var adapter: ToDoAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.todo_fragment, container, false)
       toDoRecyclerView =
               view.findViewById(R.id.todo_recyclerview_id) as RecyclerView
       toDoRecyclerView.layoutManager = LinearLayoutManager(context)
       // toDoRecyclerView.adapter = adapter
        updateUI()
        return view
   }

    private fun updateUI() {
        val tasks = toDoViewModel.tasks
        adapter = ToDoAdapter(tasks)
        toDoRecyclerView.adapter = adapter
    }
    private inner class ToDoHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val taskTextTitle : TextView = itemView.findViewById(R.id.text_title_id)
        val taskTextDetails: TextView = itemView.findViewById(R.id.text_details_id)
        val taskTextDate: TextView = itemView.findViewById(R.id.text_date_id)
        val inProcessButton : ImageButton = itemView.findViewById(R.id.image_button1_id)
        val doneButton: ImageButton = itemView.findViewById(R.id.image_button2_id)

    }
    private inner class ToDoAdapter(var tasks: List<Task>)
        : RecyclerView.Adapter<ToDoHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : ToDoHolder {
            val view = layoutInflater.inflate(R.layout.todo_item, parent, false)
            return ToDoHolder(view)
        }
        override fun getItemCount() : Int{
//            if(tasks.size == 0){
//                classButton.setVisibility(View.VISIBLE)
//            }else{
//                classButton.setVisibility(View.GONE)
//            }
            return tasks.size
        }
        override fun onBindViewHolder(holder: ToDoHolder, position: Int) {
            val task = tasks[position]
            holder.apply {
                taskTextTitle.text = task.taskTitle
                taskTextDetails.text = task.taskDetails
                taskTextDate.text = task.taskDate.time.toString()
            }
        }
    }
    companion object {

        fun newInstance(param1: String, param2: String) =
            FragmentToDo().apply {
                arguments = Bundle().apply {

                }
            }
    }
}