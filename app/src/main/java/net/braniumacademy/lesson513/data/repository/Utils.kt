package net.braniumacademy.lesson513.data.repository

import android.annotation.SuppressLint
import android.content.Context
import net.braniumacademy.lesson513.R
import java.text.DecimalFormat

@Suppress("unused")
object Utils {
    const val EXTRA_COUNTRY_INDEX = "EXTRA_COUNTRY_INDEX"
    @JvmStatic
    @SuppressLint("UseCompatLoadingForDrawables")
    fun getDrawableId(context: Context, drawableName: String): Int {
        @SuppressLint("DiscouragedApi") val id = context.resources.getIdentifier(
            drawableName.split("[.]".toRegex())[0],
            "drawable", context.packageName
        )
        return if (id != 0) {
            id
        } else {
            R.drawable.vietnam
        }
    }

    @JvmStatic
    fun getNumberFormatted(number: Float): String {
        val formatter = DecimalFormat("#,###.#")
        return formatter.format(number.toDouble())
    }
}