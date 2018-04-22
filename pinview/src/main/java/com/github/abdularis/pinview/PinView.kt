package com.github.abdularis.pinview

import android.content.Context
import android.content.res.TypedArray
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.LinearLayout
import com.github.abdularis.pinview.defaults.TextPinItemProvider

open class PinView : LinearLayout {

    private var _pinCount : Int = 0
    var pinCount : Int
        get() = _pinCount
        set(value) {
            _pinCount = value
            initPinItems()
        }
    
    private var _pinContent : String = ""
    val isFull get() = pinCount == filledPinCount
    val filledPinCount get() = _pinContent.length
    var pinContent : String
        get() = _pinContent
        set(value) {
            _pinContent = value.substring(0, Math.min(pinCount, value.length))
            fillPinItems()
        }

    private var _itemSize : Int = 48
    var itemSize : Int
        get() = _itemSize
        set(value) {
            _itemSize = value
            layoutPinItems()
        }

    private var _itemSpacing = 6
    var itemSpacing : Int
        get() = _itemSpacing
        set(value) {
            _itemSpacing = value
            layoutPinItems()
        }

    var pinItemProvider : PinItemProvider? = null
        set(value) {
            field = value
            initPinItems()
        }

    var eventListener : EventListener? = null

    constructor(ctx: Context) : super(ctx) {
        orientation = HORIZONTAL
        initPinItems()
    }
    constructor(ctx: Context, attrs: AttributeSet): super(ctx, attrs) {
        orientation = HORIZONTAL

        val a : TypedArray = ctx.obtainStyledAttributes(attrs, R.styleable.PinView, 0, 0)
        _pinCount = a.getInteger(R.styleable.PinView_pinCount, pinCount)
        _itemSize = a.getDimensionPixelSize(R.styleable.PinView_boxSize, itemSize)
        _itemSpacing = a.getDimensionPixelSize(R.styleable.PinView_boxSpacing, itemSpacing)
        a.recycle()

        initPinItems()
    }

    override fun onSaveInstanceState(): Parcelable {
        val bundle = Bundle()
        bundle.putParcelable("instance_state", super.onSaveInstanceState())
        bundle.putString("pin_content", pinContent)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state is Bundle) {
            pinContent = state.getString("pin_content")
            super.onRestoreInstanceState(state.getParcelable("instance_state"))
            return
        }
        super.onRestoreInstanceState(state)
    }

    fun insertPin(pin : Char) {
        if (!isFull) {
            _pinContent += pin
            pinItemProvider?.fillItem(this, getChildAt(filledPinCount - 1), filledPinCount - 1)
            if (isFull) {
                eventListener?.onPinCompleted(this)
            } else {
                eventListener?.onPinAdded(this)
            }
        }
    }

    fun removePin() {
        if (filledPinCount > 0) {
            _pinContent = pinContent.substring(0, filledPinCount - 1)
            pinItemProvider?.clearItem(this, getChildAt(filledPinCount), filledPinCount)
            eventListener?.onPinRemoved(this)
        }
    }

    private fun initPinItems() {
        removeAllViews()
        if (pinItemProvider == null) return
        for (i in 0 until pinCount) {
            super.addView(pinItemProvider?.createPinItem(this, i))
        }
        fillPinItems()
        layoutPinItems()
    }

    private fun fillPinItems() {
        for (i in 0 until childCount) {
            if (i < filledPinCount) pinItemProvider?.fillItem(this, getChildAt(i), i)
            else pinItemProvider?.clearItem(this, getChildAt(i), i)
        }
    }

    private fun layoutPinItems() {
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val layoutParams = LayoutParams(itemSize, itemSize)
            if (i < childCount - 1) layoutParams.rightMargin = itemSpacing
            child.layoutParams = layoutParams
        }
    }

    interface EventListener {
        fun onPinAdded(view : PinView)
        fun onPinRemoved(view : PinView)
        fun onPinCompleted(view : PinView)
    }
}