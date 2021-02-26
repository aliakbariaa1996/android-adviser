package ir.org.tavanesh.core.utils

import android.os.Bundle

sealed class NavPage

data class Navigate(
    val destinationId: Int = 0,
    val bundle: Bundle? = null
) : NavPage()

data class PopBack(
    val number: Int = 1
) : NavPage()

data class Logout(
    val destinationId: Int = 0
): NavPage()

data class SlidingMenu(
    val isOpen: Boolean? = true
): NavPage()