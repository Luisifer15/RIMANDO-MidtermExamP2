package ph.edu.auf.rimando.luisalfonso.midtermexamp2.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ph.edu.auf.rimando.luisalfonso.midtermexamp2.databinding.ContentTaskRvBinding
import ph.edu.auf.rimando.luisalfonso.midtermexamp2.models.TaskModel
import java.time.format.DateTimeFormatter

class MainRVAdapter(private var taskList: ArrayList<TaskModel>) : RecyclerView.Adapter<MainRVAdapter.MainRVViewHolder>() {
    private val TAG = MainRVAdapter::class.java.simpleName

    inner class MainRVViewHolder(val binding: ContentTaskRvBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(binding: ContentTaskRvBinding){
            binding.llCardView.setOnClickListener{
                val task = taskList[adapterPosition]
                Log.d(TAG,task.taskDesc)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRVViewHolder {
        val binding = ContentTaskRvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MainRVViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    override fun onBindViewHolder(holder: MainRVViewHolder, position: Int) {
        with(holder){
            holder.bind(holder.binding)
            with(taskList[position]){
                binding.txtTask.text = this.taskDesc
                binding.txtTaskId.text = "Task #${this.taskId.toString()}"
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd EEEE HH:mm:ss")
                binding.txtTaskDate.text = this.taskDate.format(formatter)
            }
        }
    }
}