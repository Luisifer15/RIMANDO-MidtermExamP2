package ph.edu.auf.rimando.luisalfonso.midtermexamp2

import android.R
import android.content.DialogInterface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import ph.edu.auf.rimando.luisalfonso.midtermexamp2.adapters.MainRVAdapter
import ph.edu.auf.rimando.luisalfonso.midtermexamp2.databinding.ActivityMainBinding
import ph.edu.auf.rimando.luisalfonso.midtermexamp2.models.TaskModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskList: ArrayList<TaskModel>
    private lateinit var adapter: MainRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskList = arrayListOf()

        binding.btnAddTask.setOnClickListener {
            val taskDesc = binding.edtAddTask.text.toString().trim()
            if (taskDesc.isEmpty()) {
                val d: Drawable = resources.getDrawable(android.R.drawable.ic_dialog_alert, null)
                d.setBounds(0, 0, d.intrinsicWidth / 2, d.intrinsicHeight / 2)
                binding.edtAddTask.requestFocus()
                binding.edtAddTask.setError("This field is required", d)
            } else {
                AlertDialog.Builder(this)
                    .setTitle("Confirmation")
                    .setMessage("Are you sure you want to add ${taskDesc} task?")
                    .setPositiveButton("Yes") { dialog, which ->
                        taskList.add(TaskModel(taskList.size + 1, taskDesc))
                        adapter.notifyItemInserted(taskList.size - 1)
                        binding.edtAddTask.text.clear()
                        Toast.makeText(this, "Successfully added the task", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }
        }


        val layoutManager = LinearLayoutManager(this)
        adapter = MainRVAdapter(taskList)
        binding.rvTasks.adapter = adapter
        binding.rvTasks.layoutManager = layoutManager

        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val deletedName = taskList[position]

                taskList.removeAt(position)
                adapter.notifyItemRemoved(position)
//                Toast.makeText(this@SimpleRecyclerViewActivity,"Removed ${deletedName.username}",Toast.LENGTH_SHORT).show()

                Snackbar.make(binding.rvTasks,"Removed Task #${deletedName.taskId}", Snackbar.LENGTH_LONG)
                    .setAction("Undo") {
                        taskList.add(position, deletedName)
                        adapter.notifyItemInserted(position)
                    }.show()
            }

        }).attachToRecyclerView(binding.rvTasks)

    }


}