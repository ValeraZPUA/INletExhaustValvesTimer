package edu.example.inletexhaustvalvestimer.view.resultFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import edu.example.inletexhaustvalvestimer.R
import edu.example.inletexhaustvalvestimer.models.ScreenValues
import kotlinx.android.synthetic.main.list_item.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ResultAdapter(private val resultList: ArrayList<ScreenValues>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    private lateinit var context: Context
    private var simpleDataFormat = SimpleDateFormat("mm:ss", Locale.getDefault())
    private var unitNumber: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = resultList[position]
        unitNumber = position+1
        holder.itemView.cyl.text = context.getString(R.string.unit, unitNumber.toString())
        holder.itemView.firstIn.text = simpleDataFormat.format(result.firstInValve)
        holder.itemView.firstEx.text = simpleDataFormat.format(result.firstExValve)
        holder.itemView.secondIn.text = simpleDataFormat.format(result.secondInValve)
        holder.itemView.secondEx.text = simpleDataFormat.format(result.secondExValve)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}