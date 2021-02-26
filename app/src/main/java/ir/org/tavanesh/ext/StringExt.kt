package ir.org.tavanesh.ext

import java.util.regex.Pattern

fun String.toToken(): String {
    return "Bearer $this"
}

fun String?.showable(): String {
    return this?.let { this } ?: run { "-" }
}

fun String.isPhoneNumber(): Boolean {
    val pattern = Pattern.compile("^09[0|1|2|3][0-9]{8}$")
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isValidPhoneNumber():Boolean{
    return this.length>=11
}
fun String.isValidNameFamily(): Boolean {
    return this.isNotEmpty()
}

fun String.isValidAddress(): Boolean {
    return this.isNotEmpty()
}

fun String.spacer(): String {
    val strB: StringBuilder = StringBuilder()
    strB.append(this)
    var three = 0

    for (index in this.length downTo 1) {
        three++
        if (three == 3 && index != 1) {
            strB.insert(index - 1, ",")
            three = 0
        }
    }
    return strB.toString()
}

fun String.moneyMaker(): String {
    return "${this.spacer()} تومان"
}

fun Int.withZeroString(): String {
    return if (this < 10)
        "0$this"
    else "$this"
}