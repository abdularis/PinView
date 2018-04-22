package com.github.abdularis.pinview.defaults

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.github.abdularis.pinview.PinView
import com.github.abdularis.pinview.R

class TextPinView : PinView {
    constructor(ctx: Context) : super(ctx) {
        pinItemProvider = TextPinItemProvider(R.layout.item_pin_text)
    }
    constructor(ctx: Context, attrs: AttributeSet): super(ctx, attrs) {
        val a : TypedArray = ctx.obtainStyledAttributes(attrs, R.styleable.TextPinView, 0, 0)
        val resId = a.getResourceId(R.styleable.TextPinView_textItemLayout, R.layout.item_pin_text)
        pinItemProvider = TextPinItemProvider(resId)
        a.recycle()
    }
}