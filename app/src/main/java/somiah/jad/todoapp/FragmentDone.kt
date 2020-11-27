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

class FragmentDone : Fragment() {

    private val doneViewModel:TaskListViewModel by lazy{
        ViewModelProviders.of(this).get(TaskListViewModel::class.java)
    }
    private lateinit var doneRecyclerView: RecyclerView
    private var adapter: FragmentDone.DoneAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.done_fragment, container, false)
        doneRecyclerView =
            view.findViewById(R.id.done_recyclerview_id) as RecyclerView
        doneRecyclerView.layoutManager = LinearLayoutManager(context)
        // toDoRecyclerView.adapter = adapter
        updateUI()
        return view
    }
    private fun updateUI() {
        val tasks = doneViewModel.tasks
        adapter = DoneAdapter(tasks)
        doneRecyclerView.adapter = adapter
    }

    private inner class DoneHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val taskTextTitle : TextView = itemView.findViewById(R.id.text_title_id)
        val taskTextDetails: TextView = itemView.findViewById(R.id.text_details_id)
        val taskTextDate: TextView = itemView.findViewById(R.id.text_date_id)
        val inProcessButton : ImageButton = itemView.findViewById(R.id.image_button1_id)
        val doneButton: ImageButton = itemView.findViewById(R.id.image_button2_id)

    }
    private inner class DoneAdapter(var tasks: List<Task>)
        : RecyclerView.Adapter<DoneHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : DoneHolder {
            val view = layoutInflater.inflate(R.layout.done_item, parent, false)
            return DoneHolder(view)
        }
        override fun getItemCount() : Int{
//            if(tasks.size == 0){
//                classButton.setVisibility(View.VISIBLE)
//            }else{
//                classButton.setVisibility(View.GONE)
//            }
            return tasks.size
        }
        override fun onBindViewHolder(holder: DoneHolder, position: Int) {
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
            FragmentDone().apply {
                arguments = Bundle().apply {

                }
            }
    }
}