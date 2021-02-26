package ir.org.tavanesh.presentation.base.dialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import ir.org.tavanesh.R

abstract class BaseDialog(
    context: Context,
    private val matchHeight: Boolean? = false,
    private val cancelable: Boolean? = null
) :
    Dialog(context, R.style.BaseDialogTheme) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*val lp = window?.attributes
        lp?.windowAnimations = R.style.BaseDialogTheme*/
        setContentView(getDialogLayout())
        val width = context.resources.displayMetrics.widthPixels
        window?.setLayout(
            (width / 1.0).toInt(),
            if (matchHeight == true) ViewGroup.LayoutParams.MATCH_PARENT else ViewGroup.LayoutParams.WRAP_CONTENT
        )

        cancelable?.let {
            setCancelable(cancelable)
        } ?: run{
            setCancelable(true)
        }
        setUpDialogView()
    }

    abstract fun getDialogLayout(): Int
    abstract fun setUpDialogView()

}
