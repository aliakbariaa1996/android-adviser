package ir.org.tavanesh.core.utils

data class EsToolbar(
    val titleText:String? = null,
    val titleResource:Int? = null,
    val rightLead:ToolbarButton? = null,
    val leftLead:ToolbarButton? = null,
)

data class ToolbarButton(
    val iconResource:Int,
    val callback:(() -> Unit)
)