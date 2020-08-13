package com.lyutyvaler4ik.inletexhaustvalvestimer.untils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.lyutyvaler4ik.inletexhaustvalvestimer.R
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("android:setResultTime")
fun setResult(view: TextView, time: Long) {
    val simpleDataFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
    view.text = simpleDataFormat.format(time)
}

@BindingAdapter("android:setCelNumber")
fun setCel(view: TextView, celNumber: Int) {
    view.text = view.context.getString(R.string.unit, celNumber.toString())
}