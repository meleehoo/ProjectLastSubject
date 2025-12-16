package com.example.project13.Adapter

import android.R.attr.text
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project13.R
import com.example.project13.databinding.ViewholderWeightBinding

class SelectWeightAdapter(val items: MutableList<String>): RecyclerView.Adapter<SelectWeightAdapter.Viewholder>() {
    private var selectedPosition = -1
    private var LastSelectedPostion = -1
    private lateinit var context: Context
    inner class Viewholder(val binding : ViewholderWeightBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SelectWeightAdapter.Viewholder {
        context = parent.context
        val binding = ViewholderWeightBinding.inflate(LayoutInflater.from(context),parent,false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: SelectWeightAdapter.Viewholder, position: Int) {
        holder.binding.tvWeight.text = items[position]
        holder.binding.root.setOnClickListener {
            LastSelectedPostion = selectedPosition
            notifyItemChanged(LastSelectedPostion)
            notifyItemChanged(selectedPosition)
        }
        if(selectedPosition == position){
            holder.binding.WeightLayout.setBackgroundResource(R.drawable.brown_bg_selected)
            holder.binding.tvWeight.setTextColor(context.resources.getColor(R.color.lightbrown))
        }else{
            holder.binding.WeightLayout.setBackgroundResource(R.drawable.white_bg)
            holder.binding.tvWeight.setTextColor(context.resources.getColor(R.color.grey))
        }
    }

    override fun getItemCount(): Int = items.size
}