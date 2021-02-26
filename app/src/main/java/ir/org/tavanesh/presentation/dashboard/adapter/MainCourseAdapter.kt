package ir.org.tavanesh.presentation.dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.core.utils.base.TaskDiffCallback
import ir.org.tavanesh.databinding.RowRvCourseMainBinding
import ir.org.tavanesh.ext.loadUrl
import kotlinx.android.synthetic.main.row_rv_course_main.view.*


class MainCourseAdapter(
    private val courseOnClick: (course: Course) -> Unit
) :
    ListAdapter<Course, MainCourseAdapter.ViewHolder>(TaskDiffCallback<Course>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowRvCourseMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.model = item
        holder.click(item, courseOnClick)
    }

    fun getItemInPosition(position: Int): Course {
        return this.getItem(position)
    }

    class ViewHolder(val binding: RowRvCourseMainBinding) : RecyclerView.ViewHolder(binding.root) {

        fun click(item: Course, courseOnClick: (course: Course) -> Unit) {
            itemView.card.setCardBackgroundColor(item.getBackground(itemView.context))
            itemView.setOnClickListener {
                courseOnClick(item)
            }
            itemView.imgIcon.loadUrl(item.imageUrl)
        }
    }

}