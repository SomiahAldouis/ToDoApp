package somiah.jad.todoapp

import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FragmentToDo : Fragment() {

    private lateinit var addFab : FloatingActionButton
    private val toDoViewModel:TaskListViewModel by lazy{
        ViewModelProviders.of(this).get(TaskListViewModel::class.java)
    }
    private lateinit var toDoRecyclerView: RecyclerView
    private var adapter: ToDoAdapter?= ToDoAdapter(emptyList())


//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//        super.onCreateOptionsMenu(menu, inflater)
//        inflater.inflate(R.id.fab, menu)
//    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    private var callBacks: NewTaskPopUp.Callbacks?=null
    fun onTaskAdd(task: Task){
        toDoViewModel.addTask(task)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.fab -> {

                NewTaskPopUp().apply {
                    setTargetFragment(this@FragmentToDo, 0)
                    show(this@FragmentToDo.requireFragmentManager(),"Input")
                }

                true
            }
            else -> return super.onOptionsItemSelected(item)
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
       toDoRecyclerView.adapter = adapter

        return view
   }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toDoViewModel.allTodoTasks.observe(
            viewLifecycleOwner,
            Observer { tasks ->
                tasks?.let {
                    //Log.i(TAG, "Got crimes ${crimes.size}")
                    updateUI(tasks)
                }
            })
    }

    private fun updateUI(tasks: List<Task>) {
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
                taskTextDate.text = task.taskDate
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