package com.veyvolopayli.presentation.common

import android.text.InputFilter
import android.text.Spanned
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.shape.CornerFamily
import com.veyvolopayli.presentation.R
import java.text.DecimalFormat

fun Int.toUiPriceFrom(): String {
    val formatter = DecimalFormat("#,###")
    return "от ${ formatter.format(this) } ₽"
}

fun ShapeableImageView.roundOffImageCornersToDefault(): ShapeableImageView {
    val defaultRadius = resources.getDimension(R.dimen.default_corner_radius)
    val shapeAppearanceBuilder = shapeAppearanceModel.toBuilder()
        .setAllCorners(CornerFamily.ROUNDED, defaultRadius)
    shapeAppearanceModel = shapeAppearanceBuilder.build()
    return this
}

object CyrillicInputFilter: InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence {
        return source?.subSequence(start, end)?.filter {
            Character.UnicodeBlock.of(it) == Character.UnicodeBlock.CYRILLIC
        }?.toList()?.joinToString("") ?: ""
    }
}