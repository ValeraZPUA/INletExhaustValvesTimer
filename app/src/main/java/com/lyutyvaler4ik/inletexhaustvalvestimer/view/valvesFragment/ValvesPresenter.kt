package com.lyutyvaler4ik.inletexhaustvalvestimer.view.valvesFragment

import com.lyutyvaler4ik.inletexhaustvalvestimer.models.ValveStore

class ValvesPresenter {

    fun saveData(unit: Int, firstInValue: Long, firstExValue: Long, secondInValue: Long, secondExValue: Long
    ) {
        ValveStore.addScreenValue(unit, firstInValue, firstExValue, secondInValue, secondExValue)
    }

    fun getData() {
        val list = ValveStore.getValuesList()
    }
}