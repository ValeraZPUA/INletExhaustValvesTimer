package edu.example.inletexhaustvalvestimer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.example.inletexhaustvalvestimer.view.valves.ValvesFragment


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
}

