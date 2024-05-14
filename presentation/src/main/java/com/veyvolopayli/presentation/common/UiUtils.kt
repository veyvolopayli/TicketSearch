package com.veyvolopayli.presentation.common

import android.icu.lang.UCharacter
import android.text.Editable
import android.text.InputFilter
import android.text.Spanned
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.veyvolopayli.presentation.R
import java.text.DecimalFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.regex.Matcher
import java.util.regex.Pattern


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

fun EditText.onDone(callback: (editText: EditText) -> Unit) {
    setOnEditorActionListener { v, actionId, event ->
        return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback(this)
            true
        } else {
            false
        }
    }
}

fun EditText.addCyrillicTextWatcherFilter() {
    addTextChangedListener { editable ->
        val filtered = editable.toString().filter { it.isWhitespace() || it.code in 0x0400..0x04FF }
        if (filtered != editable.toString()) {
            this.text.clear()
            this.setText(filtered)
        }
    }
}

fun LocalDate.formatShort(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM, E", Locale("ru"))
    return formatter.format(this)
}