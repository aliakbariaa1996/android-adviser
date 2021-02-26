package ir.org.tavanesh.presentation.adviser.advicecenterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.org.tavanesh.core.domain.advisor.entity.AdviceCenter
import ir.org.tavanesh.core.utils.base.TaskDiffCallback
import ir.org.tavanesh.databinding.RowRvAdviceCenterBinding
import ir.org.tavanesh.ext.loadUrl
import kotlinx.android.synthetic.main.row_rv_advice_center.view.*

class AdviceCenterRvAdapter(
    private val callback: (item: AdviceCenter) -> Unit
) :
    ListAdapter<AdviceCenter, AdviceCenterRvAdapter.ViewHolder>(TaskDiffCallback<AdviceCenter>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowRvAdviceCenterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.model = item
        holder.click(item, callback)
    }

    fun getItemInPosition(position: Int): AdviceCenter {
        return this.getItem(position)
    }

    class ViewHolder(val binding: RowRvAdviceCenterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun click(item: AdviceCenter, callback: (item: AdviceCenter) -> Unit) {
            itemView.btnMoreInfo.setOnClickListener {
                callback(item)
            }
            itemView.imgIcon.loadUrl(item.image)

        }
    }

}