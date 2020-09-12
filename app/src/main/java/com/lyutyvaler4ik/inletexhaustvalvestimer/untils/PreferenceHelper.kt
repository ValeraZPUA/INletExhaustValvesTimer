package com.lyutyvaler4ik.inletexhaustvalvestimer.untils

import android.content.Context
import android.content.SharedPreferences
import com.lyutyvaler4ik.inletexhaustvalvestimer.Constants

object PreferenceHelper {

    fun getSharedPreferences(context: Context) : SharedPreferences {
        return context.getSharedPreferences(Constants.SHARED_PREFERENCES, Context.MODE_PRIVATE)
    }
}