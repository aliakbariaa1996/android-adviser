package ir.org.tavanesh.presentation.adviser.adviser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.org.tavanesh.core.domain.advisor.entity.Adviser
import ir.org.tavanesh.core.utils.base.TaskDiffCallback
import ir.org.tavanesh.databinding.RowRvAdviserBinding
import ir.org.tavanesh.ext.loadUrl
import kotlinx.android.synthetic.main.row_rv_adviser.view.*

class AdviserRvAdapter(
    private val callback: (item: Adviser) -> Unit
) :
    ListAdapter<Adviser, AdviserRvAdapter.ViewHolder>(TaskDiffCallback<Adviser>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowRvAdviserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.model = item
        holder.click(item, callback)
    }

    fun getItemInPosition(position: Int): Adviser {
        return this.getItem(position)
    }

    class ViewHolder(val binding: RowRvAdviserBinding) : RecyclerView.ViewHolder(binding.root) {

        fun click(item: Adviser, callback: (item: Adviser) -> Unit) {
            itemView.btnMoreInfo.setOnClickListener {
                callback(item)
            }
            itemView.imgIcon.loadUrl(item.avatar)
        }
    }

}
