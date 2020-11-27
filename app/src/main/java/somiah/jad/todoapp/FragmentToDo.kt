package somiah.jad.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class FragmentToDo : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    private lateinit var recyclerView: RecyclerView
    private var adapter: Adapter?=null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        
        val view = inflater.inflate(R.layout.todo_fragment, container, false)
        return view
   }

    companion object {

        fun newInstance(param1: String, param2: String) =
            FragmentToDo().apply {
                arguments = Bundle().apply {

                }
            }
    }
}