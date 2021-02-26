package ir.org.tavanesh.presentation.dialog

import android.content.Context
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.advisor.entity.AdviserTime
import ir.org.tavanesh.presentation.adviser.adviser.AdviserFreeTimeRvAdapter
import ir.org.tavanesh.presentation.base.dialogs.BaseBottomSheetDialog
import kotlinx.android.synthetic.main.dialog_pick_adviser_time.*

class DialogPickAdviserTime(
    mContext: Context,
    private var adviserTimes: List<AdviserTime>,
    private val onTimeAdded: (adviserTimes:AdviserTime) -> Unit,
) : BaseBottomSheetDialog(mContext) {

    private val adviserFreeTimeRvAdapter = AdviserFreeTimeRvAdapter{ time, position ->
        selectedTime = time
        txtSelectedTime.text = time.time
        onSelect(time)
    }

    override fun getDialogLayout() = R.layout.dialog_pick_adviser_time

    private var selectedTime:AdviserTime? = null

    private fun onSelect(time:AdviserTime){
        adviserTimes.forEach { item ->
            item.isSelected = time.id == item.id
        }
        adviserFreeTimeRvAdapter.notifyDataSetChanged()
    }

    override fun setUpDialogView() {
        rvFreeTimes.adapter = adviserFreeTimeRvAdapter
        adviserFreeTimeRvAdapter.submitList(adviserTimes)
        adviserFreeTimeRvAdapter.notifyDataSetChanged()


        btnSubmit.setOnClickListener {
            selectedTime?.let{
                onTimeAdded(it)
                dismiss()
            }
        }
    }


}