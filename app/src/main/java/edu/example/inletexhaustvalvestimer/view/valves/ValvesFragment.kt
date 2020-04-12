package edu.example.inletexhaustvalvestimer.view.valves

import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast

import edu.example.inletexhaustvalvestimer.R
import kotlinx.android.synthetic.main.fragment_valves.*

class ValvesFragment : Fragment(), View.OnLongClickListener, View.OnClickListener {

    private lateinit var chronometerFirstIn: Chronometer
    private lateinit var chronometerSecondIn: Chronometer
    private lateinit var chronometerFirstEx: Chronometer
    private lateinit var chronometerSecondEx: Chronometer

    private lateinit var title: TextView

    private var isStartedFirstIn = false
    private var isStartedSecondIn = false
    private var isStartedFirstEx = false
    private var isStartedSecondEx = false

    private var isStoppedFirstIn = false
    private var isStoppedSecondIn = false
    private var isStoppedFirstEx = false
    private var isStoppedSecondEx = false

    private var firstInValue: Long = 0
    private var firstExValue: Long = 0
    private var secondInValue: Long = 0
    private var secondExValue: Long = 0

    private var chronometersList = arrayListOf<Chronometer>()

    private val presenter = ValvesPresenter()

    private var unit = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_valves, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title = view.findViewById(R.id.title)
        title.text = getString(R.string.unit, unit.toString())

        chronometerFirstIn = view.findViewById(R.id.chronometerFirstIn)
        chronometerSecondIn = view.findViewById(R.id.chronometerSecondIn)
        chronometerFirstEx = view.findViewById(R.id.chronometerFirstEx)
        chronometerSecondEx = view.findViewById(R.id.chronometerSecondEx)

        chronometersList.add(chronometerFirstIn)
        chronometersList.add(chronometerSecondIn)
        chronometersList.add(chronometerFirstEx)
        chronometersList.add(chronometerSecondEx)

        for (chronometer in chronometersList) {
            chronometer.setOnClickListener(this)
            chronometer.setOnLongClickListener(this)
        }

        btnNext.setOnClickListener {
            if (firstInValue == 0L || firstExValue == 0L || secondInValue == 0L || secondExValue == 0L) {
                showToast(getString(R.string.empty_data))
            } else {
                presenter.saveData(firstInValue, firstExValue, secondInValue, secondExValue)
                reset()
            }
        }
    }

    private fun reset() {
        for (chronometer in chronometersList) {
            chronometer.base = SystemClock.elapsedRealtime()
        }
        isStartedFirstIn = false
        isStartedSecondIn = false
        isStartedFirstEx = false
        isStartedSecondEx = false

        isStoppedFirstIn = false
        isStoppedSecondIn = false
        isStoppedFirstEx = false
        isStoppedSecondEx = false

        firstInValue = 0
        firstExValue = 0
        secondInValue = 0
        secondExValue = 0

        unit++
        title.text = getString(R.string.unit, unit.toString())
    }

    override fun onDestroy() {
        super.onDestroy()
        chronometerFirstIn.base = SystemClock.elapsedRealtime()
    }

    override fun onLongClick(v: View?): Boolean {
        when (v!!.id) {
            R.id.chronometerFirstIn -> {
                if (isStoppedFirstIn) {
                    chronometerFirstIn.base = SystemClock.elapsedRealtime()
                    isStartedFirstIn = false
                    firstInValue = 0
                }
            }
            R.id.chronometerSecondIn -> {
                if (isStoppedSecondIn) {
                    chronometerSecondIn.base = SystemClock.elapsedRealtime()
                    isStartedSecondIn = false
                    secondInValue = 0
                }
            }
            R.id.chronometerFirstEx -> {
                if (isStoppedFirstEx) {
                    chronometerFirstEx.base = SystemClock.elapsedRealtime()
                    isStartedFirstEx = false
                    firstExValue = 0
                }
            }
            R.id.chronometerSecondEx -> {
                if (isStoppedSecondEx) {
                    chronometerSecondEx.base = SystemClock.elapsedRealtime()
                    isStartedSecondEx = false
                    secondExValue = 0
                }
            }
        }
        return true
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.chronometerFirstIn -> {
                if (!isStartedFirstIn) {
                    chronometerFirstIn.base = SystemClock.elapsedRealtime()
                    chronometerFirstIn.start()
                    isStartedFirstIn = true
                    isStoppedFirstIn = false
                } else {
                    if (isStoppedFirstIn) {
                        showToast(getString(R.string.restart_error))
                    }
                    chronometerFirstIn.stop()
                    firstInValue = SystemClock.elapsedRealtime() - chronometerFirstIn.base
                    isStoppedFirstIn = true
                }
            }
            R.id.chronometerSecondIn -> {
                if (!isStartedSecondIn) {
                    chronometerSecondIn.base = SystemClock.elapsedRealtime()
                    chronometerSecondIn.start()
                    isStartedSecondIn = true
                    isStoppedSecondIn = false
                } else {
                    if (isStoppedSecondIn) {
                        showToast(getString(R.string.restart_error))
                    }
                    chronometerSecondIn.stop()
                    secondInValue = SystemClock.elapsedRealtime() - chronometerSecondIn.base
                    isStoppedSecondIn = true
                }
            }
            R.id.chronometerFirstEx -> {
                if (!isStartedFirstEx) {
                    chronometerFirstEx.base = SystemClock.elapsedRealtime()
                    chronometerFirstEx.start()
                    isStartedFirstEx = true
                    isStoppedFirstEx = false
                } else {
                    if (isStoppedFirstEx) {
                        showToast(getString(R.string.restart_error))
                    }
                    chronometerFirstEx.stop()
                    firstExValue = SystemClock.elapsedRealtime() - chronometerFirstEx.base
                    isStoppedFirstEx = true
                }
            }
            R.id.chronometerSecondEx -> {
                if (!isStartedSecondEx) {
                    chronometerSecondEx.base = SystemClock.elapsedRealtime()
                    chronometerSecondEx.start()
                    isStartedSecondEx = true
                    isStoppedSecondEx = false
                } else {
                    if (isStoppedSecondEx) {
                        showToast(getString(R.string.restart_error))
                    }
                    chronometerSecondEx.stop()
                    secondExValue = SystemClock.elapsedRealtime() - chronometerSecondEx.base
                    isStoppedSecondEx = true
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
