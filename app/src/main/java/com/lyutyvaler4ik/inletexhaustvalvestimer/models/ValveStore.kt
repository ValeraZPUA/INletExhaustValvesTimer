package com.lyutyvaler4ik.inletexhaustvalvestimer.models


object ValveStore {

    private var list = arrayListOf<ScreenValues>()

    fun addScreenValue(unit: Int, firstIn: Long, firstEx: Long, secondIn: Long, secondEx: Long ) {
        list.add(ScreenValues(unit, firstIn, secondIn, firstEx, secondEx))
    }

    fun getValuesList() : ArrayList<ScreenValues> {
        return list
    }

    fun clearData() {
        if (list.size == 0) {
            list.clear()
        }
    }
}