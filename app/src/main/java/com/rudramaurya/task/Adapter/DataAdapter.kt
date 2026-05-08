package com.rudramaurya.task.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rudramaurya.task.databinding.ItemSpBinding

class DataAdapter(
    private val list: ArrayList<String>
) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {

    inner class ViewHolder(
        val binding: ItemSpBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            ItemSpBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.binding.tvData.text =
            list[position]
    }

    override fun getItemCount(): Int {
        return list.size
    }
}