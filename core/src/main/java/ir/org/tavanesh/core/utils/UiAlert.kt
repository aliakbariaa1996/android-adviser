package ir.org.tavanesh.core.utils


sealed class UiAlert

data class Toast(
    val message:String
): UiAlert()

data class Snack(
    val message:String
): UiAlert()

data class Dialog(
    val message:String,
    val callback:(()->Unit)? = null
): UiAlert()