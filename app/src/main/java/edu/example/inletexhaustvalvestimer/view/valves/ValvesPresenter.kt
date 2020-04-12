package edu.example.inletexhaustvalvestimer.view.valves

import android.util.Log
import edu.example.inletexhaustvalvestimer.models.ValveStore

class ValvesPresenter {

    fun saveData(firstInValue: Long, firstExValue: Long, secondInValue: Long, secondExValue: Long
    ) {
        ValveStore.addScreenValue(firstInValue, firstExValue, secondInValue, secondExValue)
    }

    fun getData() {
        val list = ValveStore.getValuesList()
    }
}