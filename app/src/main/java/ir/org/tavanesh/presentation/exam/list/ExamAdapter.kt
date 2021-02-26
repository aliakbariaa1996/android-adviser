package ir.org.tavanesh.presentation.exam.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.utils.base.TaskDiffCallback
import ir.org.tavanesh.databinding.RowRvExamBinding

class ExamAdapter(
    private val callback: (exam: Exam) -> Unit
) :
    ListAdapter<Exam, ExamAdapter.ViewHolder>(TaskDiffCallback<Exam>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowRvExamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.model = item
        holder.click(item, callback)
    }

    fun getItemInPosition(position: Int): Exam {
        return this.getItem(position)
    }

    class ViewHolder(val binding: RowRvExamBinding) : RecyclerView.ViewHolder(binding.root) {

        fun click(item: Exam, callback: (item: Exam) -> Unit) {
            itemView.setOnClickListener {
                callback(item)
            }

        }
    }

}