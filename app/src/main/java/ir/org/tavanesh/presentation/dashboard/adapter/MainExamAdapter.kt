package ir.org.tavanesh.presentation.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.exam.entity.Exam
import ir.org.tavanesh.core.utils.base.TaskDiffCallback
import ir.org.tavanesh.databinding.RowRvMainExamBinding
import ir.org.tavanesh.ext.loadUrl
import kotlinx.android.synthetic.main.row_rv_main_exam.view.*


class MainExamAdapter(
    private val examOnClick: (exam:Exam, position:Int) -> Unit
) :
    ListAdapter<Exam, MainExamAdapter.ViewHolder>(TaskDiffCallback<Exam>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowRvMainExamBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.model = item
        holder.click(item,examOnClick, position)
    }

    class ViewHolder(val binding: RowRvMainExamBinding) : RecyclerView.ViewHolder(binding.root) {

        fun click(item: Exam,examOnClick: (exam: Exam, position:Int) -> Unit, position:Int) {
            if(item.id=="-1"){
                itemView.ivImage.setImageResource(R.drawable.ic_more_color)
            }else{
                itemView.ivImage.loadUrl(item.image)
            }
            itemView.setOnClickListener {
                examOnClick(item, position)
            }
        }
    }

}