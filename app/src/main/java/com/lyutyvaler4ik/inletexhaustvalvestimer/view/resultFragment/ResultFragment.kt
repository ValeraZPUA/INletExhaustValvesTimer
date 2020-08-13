package com.lyutyvaler4ik.inletexhaustvalvestimer.view.resultFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.lyutyvaler4ik.inletexhaustvalvestimer.R
import com.lyutyvaler4ik.inletexhaustvalvestimer.models.ScreenValues
import com.lyutyvaler4ik.inletexhaustvalvestimer.models.ValveStore

class ResultFragment : Fragment() {

    private lateinit var resultList: ArrayList<ScreenValues>
    private lateinit var adapter: ResultAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerView)

        resultList = ValveStore.getValuesList()
        val layoutManager = LinearLayoutManager(requireContext())
        adapter = ResultAdapter(resultList);
        recycler.adapter = adapter
        recycler.layoutManager = layoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
        adapter.clearData()
    }
}
