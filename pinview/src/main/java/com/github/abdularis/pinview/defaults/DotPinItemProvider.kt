package com.github.abdularis.pinview.defaults

import android.view.LayoutInflater
import android.view.View
import android.view.animation.DecelerateInterpolator
import com.github.abdularis.pinview.PinItemProvider
import com.github.abdularis.pinview.PinView
import com.github.abdularis.pinview.R

class DotPinItemProvider(val layoutResId : Int) : PinItemProvider {

    override fun createPinItem(pinView : PinView, idx : Int) : View {
        return LayoutInflater.from(pinView.context).inflate(layoutResId, pinView, false)
    }

    override fun fillItem(pinView : PinView, view: View, idx: Int) {
        val dot = view.findViewById<View>(R.id.dot)
        dot.setBackgroundResource(R.drawable.dot)
        animateDot(dot, 3f)
    }

    override fun clearItem(pinView : PinView, view: View, idx: Int) {
        val dot = view.findViewById<View>(R.id.dot)
        dot.setBackgroundResource(R.drawable.dot)
        animateDot(dot, 1f)
    }

    private fun animateDot(dot : View, scaleFactor : Float) {
        val anim = dot.animate()
        anim.scaleX(scaleFactor).scaleY(scaleFactor)
        anim.duration = 200
        anim.interpolator = DecelerateInterpolator()
        anim.start()
    }
}