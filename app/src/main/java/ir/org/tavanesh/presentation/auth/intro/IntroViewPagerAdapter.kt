package ir.org.tavanesh.presentation.auth.intro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import ir.org.tavanesh.R
import ir.org.tavanesh.data.enitity.Intro
import kotlinx.android.synthetic.main.row_view_pager_intro.view.*

class IntroViewPagerAdapter(
    private val context: Context,
    private val intros: List<Intro>
) :
    PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view =
            layoutInflater.inflate(R.layout.row_view_pager_intro, container, false)

        define(view, intros[position])

        container.addView(view)
        return view
    }

    private fun define(view: View, intro: Intro) {
        view.txtDescription.text = intro.description
        view.ivIntro.setImageResource(intro.imageRes)
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return intros.size
    }

    override fun destroyItem(container: View, position: Int, some: Any) {
        (container as ViewPager).removeView(some as View)
    }

}