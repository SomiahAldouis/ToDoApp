package somiah.jad.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragmentInProcess : Fragment() {

    private val inProcessViewModel:TaskListViewModel by lazy{
        ViewModelProviders.of(this).get(TaskListViewModel::class.java)
    }
    private lateinit var inProcessRecyclerView: RecyclerView
    private var adapter: FragmentInProcess.InProcessAdapter?= InProcessAdapter(emptyList())

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
        val view = inflater.inflate(R.layout.inprocess_fragment, container, false)
        inProcessRecyclerView =
            view.findViewById(R.id.inprocess_recyclerview_id) as RecyclerView
        inProcessRecyclerView.layoutManager = LinearLayoutManager(context)
        inProcessRecyclerView.adapter = adapter

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inProcessViewModel.allInProcessTasks.observe(
            viewLifecycleOwner,
            Observer { tasks ->
                tasks?.let {
                    //Log.i(TAG, "Got crimes ${crimes.size}")
                    updateUI(tasks)
                }
            })
    }

    private fun updateUI(tasks: List<Task>) {

        adapter = InProcessAdapter(tasks)
        inProcessRecyclerView.adapter = adapter
    }
    private inner class InProcessHolder(view: View)
        : RecyclerView.ViewHolder(view) {

        val taskTextTitle : TextView = itemView.findViewById(R.id.text_title_id)
        val taskTextDetails: TextView = itemView.findViewById(R.id.text_details_id)
        val taskTextDate: TextView = itemView.findViewById(R.id.text_date_id)
        val inProcessButton : ImageButton = itemView.findViewById(R.id.image_button1_id)
        val doneButton: ImageButton = itemView.findViewById(R.id.image_button2_id)

    }
    private inner class InProcessAdapter(var tasks: List<Task>)
        : RecyclerView.Adapter<InProcessHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
                : InProcessHolder {
            val view = layoutInflater.inflate(R.layout.inprocess_item, parent, false)
            return InProcessHolder(view)
        }
        override fun getItemCount() : Int{
//            if(tasks.size == 0){
//                classButton.setVisibility(View.VISIBLE)
//            }else{
//                classButton.setVisibility(View.GONE)
//            }
            return tasks.size
        }
        override fun onBindViewHolder(holder: InProcessHolder, position: Int) {
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
            FragmentInProcess().apply {
                arguments = Bundle().apply {

                }
            }
    }
}