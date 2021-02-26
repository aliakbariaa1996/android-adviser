package ir.org.tavanesh.ext

fun String.isMobile(): Boolean {
    return (this.length > 3)
}

fun String.isPassword(): Boolean {
    return (this.length > 3)
}

fun String.isVerifyCode():Boolean {
    return (this.length>3)
}

fun String.isValidConfirmPassword(str:String):Boolean{
    return (this == str && str.length>3 && this.length>3)
}

fun String?.isValidString():Boolean{
    return (this!=null && this.isNotBlank() && this.length>=2)
}
fun Int?.isValidInt():Boolean{
    return (this!=null && this>0)
}

fun String.isTitle():Boolean{
    return (this.isNotBlank())
}

fun String.isPlaque():Boolean{
    return (this.isNotBlank())
}

fun String.isAddress():Boolean{
    return (this.isNotBlank() && this.length > 2)
}

fun String.isDescription():Boolean{
    return (this.isNotBlank() && this.length > 2)
}


fun String.isUserIdentityNumber():Boolean{
    return (this.isNotBlank() && this.length > 2)
}

fun String.isAmount():Boolean{
    return (this.isNotBlank() && this.length > 3 && this.toIntOrNull()!=null)
}

fun String.isDayOfMonth():Boolean{
    val numeric = this.toIntOrNull()
    return (this.isNotBlank() && numeric!=null && numeric>0 && numeric<31)
}

fun String.isSheba():Boolean{
    return (this.isNotBlank())
}

fun String.isStringDate():Boolean{
    return (this.isNotBlank() && this != "")
}

fun String.isBillPaymentIdentity():Boolean{
    return (this.isNotBlank() && this.length > 3)
}