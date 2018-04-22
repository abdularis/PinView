package com.github.abdularis.pinview.defaults

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.github.abdularis.pinview.PinView
import com.github.abdularis.pinview.R

class DotPinView : PinView {

    constructor(ctx: Context) : super(ctx) {
        pinItemProvider = DotPinItemProvider(R.layout.item_pin_dot)
    }
    constructor(ctx: Context, attrs: AttributeSet): super(ctx, attrs) {
        val a : TypedArray = ctx.obtainStyledAttributes(attrs, R.styleable.DotPinView, 0, 0)
        val resId = a.getResourceId(R.styleable.DotPinView_dotItemLayout, R.layout.item_pin_dot)
        pinItemProvider = DotPinItemProvider(resId)
        a.recycle()
    }
}