package com.lyutyvaler4ik.inletexhaustvalvestimer

import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.lyutyvaler4ik.inletexhaustvalvestimer.untils.PreferenceHelper
import com.lyutyvaler4ik.inletexhaustvalvestimer.view.settingFragment.SettingFragment
import com.lyutyvaler4ik.inletexhaustvalvestimer.view.valvesFragment.ValvesFragment

class MainActivity : AppCompatActivity() {

    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPref = PreferenceHelper.getSharedPreferences(this)

        val isFirstStart = sharedPref.getBoolean(Constants.IS_FIRST_START, true)
        val fragment = if (isFirstStart) {
            sharedPref.edit().putBoolean(Constants.IS_FIRST_START, false).apply()
            SettingFragment()
        } else ValvesFragment()

        supportFragmentManager
            .beginTransaction()
            .add(
                R.id.fragmentContainer,
                fragment
            )
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
          R.id.item_settings ->  {
              supportFragmentManager
                  .beginTransaction()
                  .replace(R.id.fragmentContainer,
                      SettingFragment()
                  )
                  .commit()
          }
            R.id.item_about -> Toast.makeText(this, "in dev...", Toast.LENGTH_SHORT).show()
            R.id.item_close -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, getString(R.string.double_click_exit), Toast.LENGTH_SHORT).show()

        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}

