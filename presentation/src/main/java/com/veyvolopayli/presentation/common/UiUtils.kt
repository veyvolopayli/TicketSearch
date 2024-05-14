package com.veyvolopayli.presentation.common

import android.view.MotionEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.veyvolopayli.domain.model.ticket.Arrival
import com.veyvolopayli.domain.model.ticket.Departure
import com.veyvolopayli.presentation.R
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale


internal fun Int.toUiPriceFrom(): String = "от ${this.toUiPrice()}"

internal fun Int.toUiPrice(): String {
    val formatter = DecimalFormat("#,###")
    return "${ formatter.format(this) } ₽"
}

internal fun ShapeableImageView.roundOffImageCornersToDefault(): ShapeableImageView {
    val defaultRadius = resources.getDimension(R.dimen.default_corner_radius)
    val shapeAppearanceBuilder = shapeAppearanceModel.toBuilder()
        .setAllCorners(CornerFamily.ROUNDED, defaultRadius)
    shapeAppearanceModel = shapeAppearanceBuilder.build()
    return this
}

internal fun EditText.onRightDrawableClick(onClick: (et: EditText) -> Unit) {
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

internal fun EditText.onDone(callback: (editText: EditText) -> Unit) {
    setOnEditorActionListener { v, actionId, event ->
        return@setOnEditorActionListener if (actionId == EditorInfo.IME_ACTION_DONE) {
            callback(this)
            true
        } else {
            false
        }
    }
}

internal fun EditText.addCyrillicTextWatcherFilter() {
    addTextChangedListener { editable ->
        val filtered = editable.toString().filter { it.isWhitespace() || it.code in 0x0400..0x04FF }
        if (filtered != editable.toString()) {
            this.text.clear()
            this.setText(filtered)
        }
    }
}

internal fun LocalDate.formatShort(): String {
    val formatter = DateTimeFormatter.ofPattern("dd MMM, E", Locale("ru"))
    return formatter.format(this)
}

internal fun buildTravelTimeString(
    departure: Departure, arrival: Arrival, hasTransfer: Boolean
): String {
    val travelMinutes = java.time.Duration.between(departure.date, arrival.date).toMinutes()
    val travelHours = (travelMinutes / 60.0)
    val travelHoursString = if (travelHours != travelHours.toInt().toDouble()) {
        String.format(Locale.getDefault(), "%.1f", travelHours)
    } else {
        "${travelHours.toInt()}"
    }
    val travelTimeString = "${travelHoursString}ч в пути"
    val noTransferString = "/Без пересадок"
    return if (hasTransfer) travelTimeString else (travelTimeString + noTransferString)
}