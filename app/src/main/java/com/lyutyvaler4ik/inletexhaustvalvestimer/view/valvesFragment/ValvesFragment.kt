package com.lyutyvaler4ik.inletexhaustvalvestimer.view.valvesFragment

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.os.SystemClock
import android.os.Vibrator
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer
import android.widget.TextView
import android.widget.Toast
import com.lyutyvaler4ik.inletexhaustvalvestimer.Constants

import com.lyutyvaler4ik.inletexhaustvalvestimer.R
import com.lyutyvaler4ik.inletexhaustvalvestimer.untils.PreferenceHelper
import com.lyutyvaler4ik.inletexhaustvalvestimer.view.resultFragment.ResultFragment
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
    private lateinit var vibro: Vibrator
    private val presenter = ValvesPresenter()

    private var unit = 1

    private var valvesQuantity = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_valves, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vibro = requireContext().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        title = view.findViewById(R.id.title)
        title.text = getString(R.string.unit, unit.toString())

        valvesQuantity =  arguments?.getInt(Constants.VALVES_QUANTITY)
            ?: PreferenceHelper.getSharedPreferences(requireContext()).getString(Constants.VALVES_QUANTITY, "0")!!.toInt()

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
            if (unit == valvesQuantity) {
                presenter.saveData(unit, firstInValue, firstExValue, secondInValue, secondExValue)
                activity!!
                    .supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, ResultFragment())
                    .commit()
                fart()
            } else if (firstInValue == 0L || firstExValue == 0L || secondInValue == 0L || secondExValue == 0L) {
                showToast(getString(R.string.empty_data))
            } else {
                presenter.saveData(unit, firstInValue, firstExValue, secondInValue, secondExValue)
                reset()
            }
        }
    }

    private fun fart() {
        val resID = resources.getIdentifier("fart", "raw", requireContext().packageName)
        //val mediaPlayer = MediaPlayer.create(requireContext(), resID).start()
        MediaPlayer.create(requireContext(), resID).start()
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

        for (chronometer in chronometersList) {
            chronometer.setBackgroundColor(requireContext().resources.getColor(R.color.green))
        }

        unit++
        title.text = getString(R.string.unit, unit.toString())
        if (unit == 9) {
            btnNext.text = getString(R.string.result)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        chronometerFirstIn.base = SystemClock.elapsedRealtime()
    }

    override fun onLongClick(v: View?): Boolean {
        when (v!!.id) {
            R.id.chronometerFirstIn -> {
                if (isStoppedFirstIn) {
                    resetChronometer(chronometerFirstIn)
                    isStartedFirstIn = false
                    firstInValue = 0
                }
            }
            R.id.chronometerSecondIn -> {
                if (isStoppedSecondIn) {
                    resetChronometer(chronometerSecondIn)
                    isStartedSecondIn = false
                    secondInValue = 0
                }
            }
            R.id.chronometerFirstEx -> {
                if (isStoppedFirstEx) {
                    resetChronometer(chronometerFirstEx)
                    isStartedFirstEx = false
                    firstExValue = 0
                }
            }
            R.id.chronometerSecondEx -> {
                if (isStoppedSecondEx) {
                    resetChronometer(chronometerSecondEx)
                    isStartedSecondEx = false
                    secondExValue = 0
                }
            }
        }
        return true
    }

    override fun onClick(v: View?) {
        vibro.vibrate(100, null)
        when (v!!.id) {
            R.id.chronometerFirstIn -> {
                if (!isStartedFirstIn) {
                    startChronometer(chronometerFirstIn)
                    isStartedFirstIn = true
                    isStoppedFirstIn = false
                } else {
                    if (isStoppedFirstIn) {
                        showToast(getString(R.string.restart_error))
                    } else {
                        stopChronometer(chronometerFirstIn)
                        firstInValue = SystemClock.elapsedRealtime() - chronometerFirstIn.base
                        isStoppedFirstIn = true
                    }
                }
            }
            R.id.chronometerSecondIn -> {
                if (!isStartedSecondIn) {
                    startChronometer(chronometerSecondIn)
                    isStartedSecondIn = true
                    isStoppedSecondIn = false
                } else {
                    if (isStoppedSecondIn) {
                        showToast(getString(R.string.restart_error))
                    } else {
                        stopChronometer(chronometerSecondIn)
                        secondInValue = SystemClock.elapsedRealtime() - chronometerSecondIn.base
                        isStoppedSecondIn = true
                    }
                }
            }
            R.id.chronometerFirstEx -> {
                if (!isStartedFirstEx) {
                    startChronometer(chronometerFirstEx)
                    isStartedFirstEx = true
                    isStoppedFirstEx = false
                } else {
                    if (isStoppedFirstEx) {
                        showToast(getString(R.string.restart_error))
                    } else {
                        stopChronometer(chronometerFirstEx)
                        firstExValue = SystemClock.elapsedRealtime() - chronometerFirstEx.base
                        isStoppedFirstEx = true
                    }
                }
            }
            R.id.chronometerSecondEx -> {
                if (!isStartedSecondEx) {
                    startChronometer(chronometerSecondEx)
                    isStartedSecondEx = true
                    isStoppedSecondEx = false
                } else {
                    if (isStoppedSecondEx) {
                        showToast(getString(R.string.restart_error))
                    } else {
                        stopChronometer(chronometerSecondEx)
                        secondExValue = SystemClock.elapsedRealtime() - chronometerSecondEx.base
                        isStoppedSecondEx = true
                    }
                }
            }
        }
    }

    private fun resetChronometer(chronometer: Chronometer) {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.setBackgroundColor(requireContext().resources.getColor(R.color.green))
    }

    private fun stopChronometer(chronometer: Chronometer) {
        chronometer.stop()
        chronometer.setBackgroundColor(requireContext().resources.getColor(R.color.red))
    }

    private fun startChronometer(chronometer: Chronometer) {
        chronometer.base = SystemClock.elapsedRealtime()
        chronometer.start()
        chronometer.setBackgroundColor(requireContext().resources.getColor(R.color.yellow))
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}
