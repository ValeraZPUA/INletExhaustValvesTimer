package com.lyutyvaler4ik.inletexhaustvalvestimer.view.settingFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.lyutyvaler4ik.inletexhaustvalvestimer.Constants
import com.lyutyvaler4ik.inletexhaustvalvestimer.R
import com.lyutyvaler4ik.inletexhaustvalvestimer.databinding.FragmentSettingBinding
import com.lyutyvaler4ik.inletexhaustvalvestimer.untils.PreferenceHelper
import com.lyutyvaler4ik.inletexhaustvalvestimer.view.valvesFragment.ValvesFragment
import kotlinx.android.synthetic.main.fragment_setting.*


class SettingFragment : Fragment() {

    private lateinit var binding: FragmentSettingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_setting, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = PreferenceHelper.getSharedPreferences(requireContext())

        binding.valvesQuantity = sharedPref.getString(Constants.VALVES_QUANTITY, "0")

        btn_save.setOnClickListener {
            val cylinderQuantity = et_cylinder_quantity.text.toString()
            if (cylinderQuantity.toInt() < 1) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.more_then_1_cylinder),
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                sharedPref.edit().putString(Constants.VALVES_QUANTITY, cylinderQuantity).apply()
                navigateToValueFragment(cylinderQuantity.toInt())
            }
        }
    }

    private fun navigateToValueFragment(cylinderQuantity: Int) {
        val bundle = Bundle()
        bundle.putInt(Constants.VALVES_QUANTITY, cylinderQuantity)
        val fragment = ValvesFragment()
        fragment.arguments = bundle

        fragmentManager!!
            .beginTransaction()
            .replace(R.id.fragmentContainer,
                fragment
            )
            .commit()

       /* val transaction = fragmentManager!!.beginTransaction()
        transaction.replace(R.id.fragmentContainer, fragment)
        transaction.commit()*/
    }
}