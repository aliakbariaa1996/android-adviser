package ir.org.tavanesh.presentation.dialog

import android.content.Context
import ir.org.tavanesh.R
import ir.org.tavanesh.presentation.base.dialogs.BaseDialog
import kotlinx.android.synthetic.main.dialog_description.*

class DialogDescription(
    mContext: Context,
    private val description: String,
    cancelable:Boolean? = null,
    private val callback: (() -> Unit)? = null,
    private val buttonText: String? = null
) : BaseDialog(mContext, cancelable = cancelable) {

    override fun getDialogLayout() = R.layout.dialog_description

    override fun setUpDialogView() {
        buttonText?.let{
            btnMainText.text = it
        }

        txtDescription.text = description

        btnMain.setOnClickListener {
            callback?.let { call -> call() }
            dismiss()
        }

        btnClose.setOnClickListener {
            dismiss()
        }
    }

}