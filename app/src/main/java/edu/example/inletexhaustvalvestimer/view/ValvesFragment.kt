package edu.example.inletexhaustvalvestimer.view

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Chronometer

import edu.example.inletexhaustvalvestimer.R
import kotlinx.android.synthetic.main.fragment_valves.*

class ValvesFragment : Fragment() {

    private lateinit var mChronometer: Chronometer

    private var isStartedFirst = false
    private var isStoppedFirst = false
    private var isStartedSecind = false
    private var isStartedThird = false
    private var isStartedFourtg = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_valves, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        title.text = "1st Valve"

        mChronometer = view.findViewById(R.id.chronometerFirst)

        mChronometer.setOnClickListener {
            if (!isStartedFirst) {
                Log.d("tag", "if")
                mChronometer.base = SystemClock.elapsedRealtime()
                mChronometer.start()
                isStartedFirst = true
                isStoppedFirst = false
            } else {
                Log.d("tag", "else")
                /*val elapsedMillis: Long =
                    SystemClock.elapsedRealtime() - mChronometer.getBase()
                Log.d("tag", elapsedMillis.toString())*/

                /*if (isStartedFirst) {
                    Toast.makeText(this, "To reset data make long click", Toast.LENGTH_LONG).show()
                }*/


                mChronometer.stop()
                isStoppedFirst = true

            }
        }

        mChronometer.setOnLongClickListener {
            //if ((SystemClock.elapsedRealtime() - mChronometer.base) !=0L) {
            if (isStoppedFirst) {
                mChronometer.base = SystemClock.elapsedRealtime()
                Log.d("tag", "OnLong")
                isStartedFirst = false
            }
            return@setOnLongClickListener true
        }

    }

    override fun onDestroy() {
        Log.d("tag", "destroy1")
        super.onDestroy()
        Log.d("tag", "destroy")
        mChronometer.base = SystemClock.elapsedRealtime()
    }
}
