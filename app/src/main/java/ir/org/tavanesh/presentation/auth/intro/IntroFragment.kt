package ir.org.tavanesh.presentation.auth.intro

import android.graphics.Color
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.viewpager.widget.ViewPager
import dagger.hilt.android.AndroidEntryPoint
import ir.org.tavanesh.R
import ir.org.tavanesh.databinding.FragmentIntroBinding
import kotlinx.android.synthetic.main.fragment_intro.*

@AndroidEntryPoint
class IntroFragment : Fragment() {
    private val viewModel: IntroViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentIntroBinding.inflate(inflater)
        binding.vm = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()

        viewModel.providePager().observe(viewLifecycleOwner, {
            it?.let { number ->
                viewPager.currentItem = number
            }
        })


    }

    private fun initViewPager() {
        val intros = viewModel.generateItems(requireContext())

        val introViewPagerAdapter = IntroViewPagerAdapter(requireContext(), intros)
        viewPager.adapter = introViewPagerAdapter
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener)

        addBottomDots(0)
    }


    private fun addBottomDots(currentPage: Int) {
        val dots = arrayListOf<TextView>()
        dots.add(TextView(context))
        dots.add(TextView(context))
        dots.add(TextView(context))
        layoutDots.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(context)
            dots[i].text = Html.fromHtml("&#8226;")
            dots[i].textSize = 35f
            dots[i].setTextColor(Color.parseColor("#B9E6F4"))
            layoutDots.addView(dots[i])
        }
        if (dots.isNotEmpty()) dots[currentPage].setTextColor(Color.GREEN)
    }

    private var viewPagerPageChangeListener: ViewPager.OnPageChangeListener = object :
        ViewPager.OnPageChangeListener {
        override fun onPageSelected(position: Int) {
            viewModel.setPagerPosition(position)
            addBottomDots(position)
            if (position == 2) {
                btnNext.text = getString(R.string.button_start)
            } else {
                btnNext.text = ""
            }
        }

        override fun onPageScrolled(arg0: Int, arg1: Float, arg2: Int) {}
        override fun onPageScrollStateChanged(arg0: Int) {}
    }

}