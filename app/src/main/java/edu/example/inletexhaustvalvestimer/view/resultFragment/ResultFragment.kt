package edu.example.inletexhaustvalvestimer.view.resultFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import edu.example.inletexhaustvalvestimer.R
import edu.example.inletexhaustvalvestimer.models.ScreenValues
import edu.example.inletexhaustvalvestimer.models.ValveStore
import kotlinx.android.synthetic.main.fragment_result.*

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
