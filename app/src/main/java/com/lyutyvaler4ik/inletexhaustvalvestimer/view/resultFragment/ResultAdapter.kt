package com.lyutyvaler4ik.inletexhaustvalvestimer.view.resultFragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.lyutyvaler4ik.inletexhaustvalvestimer.R
import com.lyutyvaler4ik.inletexhaustvalvestimer.databinding.ListItemBinding
import com.lyutyvaler4ik.inletexhaustvalvestimer.models.ScreenValues
import kotlin.collections.ArrayList

class ResultAdapter(private val resultList: ArrayList<ScreenValues>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ListItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.list_item,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return resultList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(resultList[position])
    }

    class ViewHolder(private val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(screenValues: ScreenValues) {
            binding.results = screenValues
        }
    }

    fun clearData() {
        resultList.clear()
        notifyDataSetChanged()
    }
}