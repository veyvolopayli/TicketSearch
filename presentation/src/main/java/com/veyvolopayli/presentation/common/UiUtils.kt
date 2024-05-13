package com.veyvolopayli.presentation.common

import android.text.InputFilter
import android.text.Spanned
import android.view.MotionEvent
import android.widget.EditText
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.veyvolopayli.presentation.R
import java.text.DecimalFormat

fun Int.toUiPriceFrom(): String = "от ${this.toUiPrice()}"

fun Int.toUiPrice(): String {
    val formatter = DecimalFormat("#,###")
    return "${ formatter.format(this) } ₽"
}

fun ShapeableImageView.roundOffImageCornersToDefault(): ShapeableImageView {
    val defaultRadius = resources.getDimension(R.dimen.default_corner_radius)
    val shapeAppearanceBuilder = shapeAppearanceModel.toBuilder()
        .setAllCorners(CornerFamily.ROUNDED, defaultRadius)
    shapeAppearanceModel = shapeAppearanceBuilder.build()
    return this
}

fun EditText.onRightDrawableClick(onClick: (et: EditText) -> Unit) {
    val endDrawable = compoundDrawablesRelative[2] ?: return
    val iconWidth = endDrawable.intrinsicWidth
    println(iconWidth)
    setOnTouchListener { v, event ->
        if (event.action == MotionEvent.ACTION_UP) {
            if (event.x > v.width - iconWidth) {
                performClick()
                onClick.invoke(this)
                return@setOnTouchListener true
            }
        }
        false
    }
}

class CyrillicInputFilter: InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        source ?: return null
        return if (source.matches(Regex("[a-zA-Z ]+"))) source else null
    }
}