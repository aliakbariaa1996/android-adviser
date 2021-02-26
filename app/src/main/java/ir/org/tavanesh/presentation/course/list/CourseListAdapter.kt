package ir.org.tavanesh.presentation.course.list

import ir.org.tavanesh.databinding.RowRvCourseBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ir.org.tavanesh.core.domain.course.entity.Course
import ir.org.tavanesh.core.utils.base.TaskDiffCallback
import ir.org.tavanesh.ext.loadUrl
import kotlinx.android.synthetic.main.row_rv_course.view.*


class CourseListAdapter(
    private val courseOnClick: (course: Course) -> Unit
):
    ListAdapter<Course, CourseListAdapter.ViewHolder>(TaskDiffCallback<Course>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RowRvCourseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.binding.model = item
        holder.click(item, courseOnClick)
    }

    class ViewHolder(val binding: RowRvCourseBinding) : RecyclerView.ViewHolder(binding.root) {

        fun click(item: Course, courseOnClick: (course: Course) -> Unit) {
            itemView.imgIcon.loadUrl(item.imageUrl)
            itemView.card.setCardBackgroundColor(item.getBackground(itemView.context))
            itemView.setOnClickListener {
                courseOnClick(item)
            }
        }
    }

}