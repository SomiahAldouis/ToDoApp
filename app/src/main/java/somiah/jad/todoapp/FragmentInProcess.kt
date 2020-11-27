package somiah.jad.todoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentInProcess : Fragment() {

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
        return view
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            FragmentInProcess().apply {
                arguments = Bundle().apply {

                }
            }
    }
}