package com.github.abdularis.pinviewsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.github.abdularis.pinview.PinView

class MainActivity : AppCompatActivity() {

    var mPinView : PinView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mPinView = findViewById(R.id.pin_box_view)
        mPinView?.eventListener = object : PinView.EventListener {
            override fun onPinAdded(view: PinView) {

            }

            override fun onPinRemoved(view: PinView) {

            }

            override fun onPinCompleted(view: PinView) {
                Toast.makeText(this@MainActivity, "completed: " + view.pinContent, Toast.LENGTH_SHORT).show()
            }

        }
    }

    fun onAddClick(view : View) {
        mPinView?.insertPin('A')
    }

    fun onRemoveClick(view: View) {
        mPinView?.removePin()
    }
}
