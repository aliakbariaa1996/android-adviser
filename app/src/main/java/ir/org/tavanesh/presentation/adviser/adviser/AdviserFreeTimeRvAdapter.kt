package ir.org.tavanesh.presentation.adviser.adviser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.org.tavanesh.core.domain.advisor.entity.AdviserTime
import ir.org.tavanesh.core.utils.base.TaskDiffCallback
import ir.org.tavanesh.databinding.RowRvAdviserFreeTimeBinding

class AdviserFreeTimeRvAdapter(
    private val callback: (item: AdviserTime, position:Int) -> Unit
) :
    ListAdapter<AdviserTime, AdviserFreeTimeRvAdapter.ViewHolder>(TaskDiffCallback<AdviserTime>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowRvAdviserFreeTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.model = item
        holder.click(item, callback, position)
    }

    fun getItemInPosition(position: Int): AdviserTime {
        return this.getItem(position)
    }

    class ViewHolder(val binding: RowRvAdviserFreeTimeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun click(item: AdviserTime, callback: (item: AdviserTime, position:Int) -> Unit, position:Int) {
            itemView.setOnClickListener {
                callback(item, position)
            }

        }
    }

}
