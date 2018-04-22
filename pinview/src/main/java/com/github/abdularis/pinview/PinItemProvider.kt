package com.github.abdularis.pinview

import android.view.View

interface PinItemProvider {
    fun createPinItem(pinView : PinView, idx : Int) : View
    fun fillItem(pinView : PinView, view: View, idx: Int)
    fun clearItem(pinView : PinView, view: View, idx: Int)
}