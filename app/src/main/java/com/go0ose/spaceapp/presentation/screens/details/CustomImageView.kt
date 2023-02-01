package com.go0ose.spaceapp.presentation.screens.details

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.RectF
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import androidx.appcompat.widget.AppCompatImageView
import java.lang.Float.max
import java.lang.Float.min

@SuppressLint("ClickableViewAccessibility")
class CustomImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    private val scaleGestureDetector: ScaleGestureDetector
    private val gestureDetector: GestureDetector
    private var scaleFactor = 1f
    private var translateX = 0f
    private var translateY = 0f
    private val mMatrix = Matrix()

    init {
        scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
        gestureDetector = GestureDetector(context, MoveListener())
        setOnTouchListener { view, event ->
            scaleGestureDetector.onTouchEvent(event)
            gestureDetector.onTouchEvent(event)
            true
        }
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            scaleFactor *= detector.scaleFactor
            scaleFactor = max(0.1f, min(scaleFactor, 10.0f))
            invalidate()
            return true
        }
    }

    private inner class MoveListener : GestureDetector.SimpleOnGestureListener() {
        override fun onScroll(
            e1: MotionEvent,
            e2: MotionEvent,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            translateX -= distanceX
            translateY -= distanceY
            invalidate()
            return true
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mMatrix.reset()
        mMatrix.postScale(scaleFactor, scaleFactor, pivotX, pivotY)
        mMatrix.postTranslate(translateX, translateY)
        imageMatrix = mMatrix

        if (drawable != null) {
            val src = RectF(0f, 0f, drawable.intrinsicWidth.toFloat(), drawable.intrinsicHeight.toFloat())
            val dst = RectF(0f, 0f, width.toFloat(), height.toFloat())
            if (!src.contains(dst)) {
                mMatrix.reset()
                scaleFactor = 1f
                translateX = 0f
                translateY = 0f
                mMatrix.postScale(scaleFactor, scaleFactor, pivotX, pivotY)
                mMatrix.postTranslate(translateX, translateY)
                imageMatrix = mMatrix
            }
        }
    }
}
