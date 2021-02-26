package ir.org.tavanesh.presentation.adviser.adviser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import calendar.DateConverter
import com.dariushm2.PersianCaldroid.caldroiddialog.PersianCaldroidDialog
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.advisor.entity.Adviser
import ir.org.tavanesh.core.utils.EsToolbar
import ir.org.tavanesh.core.utils.ITEM_ID
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.core.utils.ToolbarButton
import ir.org.tavanesh.data.platform.model.AdviserViews
import ir.org.tavanesh.databinding.FragmentAdviserBinding
import ir.org.tavanesh.presentation.dialog.DialogPickAdviserTime
import kotlinx.android.synthetic.main.fragment_adviser.*

@AndroidEntryPoint
class AdviserFragment : Fragment() {

    private val viewModel: AdviserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAdviserBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adviser:Adviser = arguments?.getSerializable(OBJECT) as Adviser
        val centerId:String = arguments?.getString(ITEM_ID) as String
        viewModel.setAdviceCenterId(centerId)
        viewModel.getAdviserInfo(adviser)

        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_adviser_info,
                leftLead = ToolbarButton(
                    R.drawable.ic_arrow_left_dark,
                    callback = { viewModel.back() }
                )
            )
        )

        btnGetDate.setOnClickListener{
            openDatePicker()
        }

        btnGetTime.setOnClickListener {
            openTimePicker()
        }

        viewModel.setViewState(AdviserViews.PROFILE)
    }

    private fun openDatePicker() {

        val persianCalendarDialog = PersianCaldroidDialog()
        persianCalendarDialog.setOnDateSetListener { dialog, persianDate ->
            val civilDate = DateConverter.persianToCivil(persianDate)
            viewModel.setDate("$persianDate", "$civilDate")
            dialog.dismiss()
        }

        persianCalendarDialog.typeface = context?.let {
            ResourcesCompat.getFont(
                it,
                R.font.regular
            )
        }

        activity?.supportFragmentManager?.let {
            persianCalendarDialog.show(it, PersianCaldroidDialog::class.java.name)
        }
    }

    private fun openTimePicker() {
        if(viewModel.isDatePicked){
            if(viewModel.freeTimeList.isNotEmpty()){
                context?.let{
                    DialogPickAdviserTime(it, viewModel.freeTimeList) { time ->
                        viewModel.setTime(time)
                    }.show()
                }
            }
            else{
                viewModel.showToast(getString(R.string.error_no_time_in_this_date))
            }
        }else{
            viewModel.showToast(getString(R.string.error_pick_date_first))
            openDatePicker()
        }
    }

}