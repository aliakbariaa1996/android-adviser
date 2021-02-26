package ir.org.tavanesh.presentation.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.core.domain.user.entity.Education
import ir.org.tavanesh.core.domain.user.entity.Gender
import ir.org.tavanesh.data.mappers.toEducationNames
import ir.org.tavanesh.data.mappers.toGenderNames
import ir.org.tavanesh.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.*

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentRegisterBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getConfigs()

        viewModel.configs.observe( viewLifecycleOwner,  {
            it?.let{
                it.genders?.let{ genders->
                    setupGenderSinner(genders)
                }
                it.educations?.let{ educations ->
                    setupEducationSinner(educations)
                }
            }
        })
    }

    private fun setupGenderSinner(list: List<Gender>) {
        val adapter = object : ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            list.toGenderNames()
        ) {}
        sprGender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, v: View, position: Int, id: Long) {
                viewModel.setGender(list[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        sprGender.adapter = adapter
    }

    private fun setupEducationSinner(list: List<Education>) {
        val adapter = object : ArrayAdapter<String>(
            this.requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            list.toEducationNames()
        ) {}
        sprEducation.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, v: View, position: Int, id: Long) {
                viewModel.setEducation(list[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        sprEducation.adapter = adapter
    }
}