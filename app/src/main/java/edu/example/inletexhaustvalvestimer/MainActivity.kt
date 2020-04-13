package edu.example.inletexhaustvalvestimer

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.example.inletexhaustvalvestimer.models.ValveStore
import edu.example.inletexhaustvalvestimer.view.valvesFragment.ValvesFragment


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragmentContainer,
                ValvesFragment()
            )
            .commit()
    }

    /*override fun onDestroy() {
        super.onDestroy()
        Log.d("tag", "onDestroy")
        ValveStore.clearData()
    }*/
}

