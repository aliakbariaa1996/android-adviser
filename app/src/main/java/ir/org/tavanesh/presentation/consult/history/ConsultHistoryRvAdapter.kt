package ir.org.tavanesh.presentation.consult.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.org.tavanesh.core.domain.consult.entity.Consult
import ir.org.tavanesh.core.utils.base.TaskDiffCallback
import ir.org.tavanesh.databinding.RowRvConsultHistoryBinding


class ConsultHistoryRvAdapter(
    private val onItemClick: (consult: Consult) -> Unit
):
    ListAdapter<Consult, ConsultHistoryRvAdapter.ViewHolder>(TaskDiffCallback<Consult>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowRvConsultHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.model = item
        holder.click(item, onItemClick)
    }

    class ViewHolder(val binding: RowRvConsultHistoryBinding) : RecyclerView.ViewHolder(binding.root) {

        fun click(item: Consult, onItemClick: (consult: Consult) -> Unit) {
            itemView.setOnClickListener {
                onItemClick(item)
            }
        }
    }

}