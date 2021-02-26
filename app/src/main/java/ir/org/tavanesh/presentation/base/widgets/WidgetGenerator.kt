package ir.org.tavanesh.presentation.base.widgets

import android.annotation.SuppressLint
import android.view.Gravity.*
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatRadioButton
import ir.org.tavanesh.R
import ir.org.tavanesh.core.domain.question.entity.Answer
import timber.log.Timber

@SuppressLint("UseCompatLoadingForDrawables")
fun checkBoxGenerator(linear: LinearLayout, answers: List<Answer>){
    linear.removeAllViews()
    answers.forEach {
        val widget = AppCompatCheckBox(linear.context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 20, 0, 0)
        widget.layoutParams = params
        widget.text = it.answer
        widget.gravity = CENTER
        widget.setTextAppearance(linear.context, R.style.TextPrimary)
        widget.tag = it.id

        widget.buttonDrawable = null
        widget.background = linear.context.resources.getDrawable(R.drawable.custom_btn_checkbox_shape)
        linear.addView(widget)
        widget.isChecked = it.isSelect
    }
}
@SuppressLint("UseCompatLoadingForDrawables")
fun radioButtonGenerator(radioGroup: RadioGroup, answers: List<Answer>){
    radioGroup.removeAllViews()
    answers.forEach {

        val widget = AppCompatRadioButton(radioGroup.context)
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 20, 0, 0)
        widget.layoutParams = params
        widget.text = it.answer
        widget.gravity = CENTER
        widget.setTextAppearance(radioGroup.context, R.style.TextPrimary)
        widget.tag = it.id

        widget.buttonDrawable = null
        widget.background = radioGroup.context.resources.getDrawable(R.drawable.custom_btn_checkbox_shape)
        radioGroup.addView(widget)
        widget.isChecked = it.isSelect
    }
}