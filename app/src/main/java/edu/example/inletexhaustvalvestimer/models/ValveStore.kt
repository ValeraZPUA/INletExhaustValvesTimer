package edu.example.inletexhaustvalvestimer.models


object ValveStore {

    var list = arrayListOf<ScreenValues>()

    fun addScreenValue(firstIn: Long, firstEx: Long, secondIn: Long, secondEx: Long ) {
        list.add(ScreenValues(firstIn, secondIn, firstEx, secondEx))
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