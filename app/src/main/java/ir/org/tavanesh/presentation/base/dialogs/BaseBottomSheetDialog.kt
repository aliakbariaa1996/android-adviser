package ir.org.tavanesh.presentation.base.dialogs

import android.content.Context
import android.os.Bundle
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class BaseBottomSheetDialog(
    context: Context,
) : BottomSheetDialog(context) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(getDialogLayout())
        val width = context.resources.displayMetrics.widthPixels
        window?.setLayout(
            (width / 1.0).toInt(),
            (ViewGroup.LayoutParams.MATCH_PARENT)
        )
        behavior.peekHeight = BottomSheetBehavior.PEEK_HEIGHT_AUTO
        setUpDialogView()
    }

    abstract fun getDialogLayout(): Int
    abstract fun setUpDialogView()

}
