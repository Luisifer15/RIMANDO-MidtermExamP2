package ph.edu.auf.recyclerviewslesson.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ph.edu.auf.rimando.luisalfonso.midtermexamp2.databinding.FragmentSimpleRecyclerViewBinding
import ph.edu.auf.rimando.luisalfonso.midtermexamp2.models.TaskModel

class SimpleRecyclerViewFragment : Fragment() {
    private lateinit var binding: FragmentSimpleRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentSimpleRecyclerViewBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val taskList = arrayListOf(
            TaskModel(1, "Description 1"),
            TaskModel(2, "Description 2"),
            TaskModel(3, "Description 3"),
            TaskModel(4, "Description 4"),

        )
    }
}