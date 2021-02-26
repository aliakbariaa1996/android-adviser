package ir.org.tavanesh.presentation.exam.result

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.city.entity.City
import ir.org.tavanesh.core.utils.EsToolbar
import ir.org.tavanesh.core.utils.OBJECT
import ir.org.tavanesh.core.utils.ToolbarButton
import ir.org.tavanesh.data.mappers.toNames
import ir.org.tavanesh.databinding.FragmentExamResultBinding
import kotlinx.android.synthetic.main.fragment_exam_result.*
import timber.log.Timber

@AndroidEntryPoint
class ExamResultFragment : Fragment() {

    private  val viewModel: ExamResultViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentExamResultBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val feedback = arguments?.getString(OBJECT)
        txtFeedback.text = feedback

        viewModel.setToolbar(
            EsToolbar(
                titleResource = R.string.hint_exam_result,
                leftLead = ToolbarButton(
                    R.drawable.ic_close_gray,
                    callback = {
                        viewModel.navigateTo(R.id.action_examResultFragment_to_dashboardFragment)
                    }
                )
            )
        )

        viewModel.provinces.observe(viewLifecycleOwner, {
            it?.let {
                initProvinceSpinners(it)
            }
        })

        viewModel.cities.observe(viewLifecycleOwner, {
            it?.let {
                initCitySpinners(it)
            }
        })

        viewModel.setFirstExamConfig()

    }

    private fun initProvinceSpinners(list: List<City>){
        val adapter = object : ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            list.toNames()
        ) {}
        sprState.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, v: View, position: Int, id: Long) {
                viewModel.getCities(list[position].id)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        sprState.adapter = adapter

    }
    private fun initCitySpinners(list: List<City>){
        Timber.tag("TAG").d("lllT: ${list.size}")
        val adapter = object : ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            list.toNames()
        ) {}
        sprCity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, v: View, position: Int, id: Long) {
                viewModel.setCityProvince(list[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        sprCity.adapter = adapter
    }

}