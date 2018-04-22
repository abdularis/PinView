package com.github.abdularis.pinview.defaults

import android.view.LayoutInflater
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.TextView
import com.github.abdularis.pinview.PinItemProvider
import com.github.abdularis.pinview.PinView
import com.github.abdularis.pinview.R

class TextPinItemProvider(val layoutResId : Int) : PinItemProvider {
    override fun createPinItem(pinView: PinView, idx: Int): View {
        return LayoutInflater.from(pinView.context).inflate(layoutResId, pinView, false)
    }

    override fun fillItem(pinView: PinView, view: View, idx: Int) {
        val tv = view.findViewById<TextView>(R.id.text_view)
        tv.text = pinView.pinContent[idx].toString()
        animate(tv, 0f, 1f)
    }

    override fun clearItem(pinView: PinView, view: View, idx: Int) {
        val tv = view.findViewById<TextView>(R.id.text_view)
        animate(tv, 1f, 0f)
    }

    private fun animate(dot : View, start : Float, end : Float) {
        dot.scaleX = start
        dot.scaleY = start
        val anim = dot.animate()
        anim.scaleX(end).scaleY(end)
        anim.duration = 200
        anim.interpolator = DecelerateInterpolator()
        anim.start()
    }
}