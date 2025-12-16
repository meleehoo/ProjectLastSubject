package com.example.project13.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project13.R
import com.example.project13.databinding.ViewholderPiclistBinding

class PicListAdapter(val items: MutableList<String>, private val onImageSlected: (String) -> Unit): RecyclerView.Adapter<PicListAdapter.ViewHolder>() {


    private var selectedPosition = -1
    private var lastSelectedPosition = -1


    inner class ViewHolder(val binding: ViewholderPiclistBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PicListAdapter.ViewHolder {
        val binding= ViewholderPiclistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PicListAdapter.ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.picList.loadImage(item)
        holder.itemView.setOnClickListener {
            lastSelectedPosition=selectedPosition
            selectedPosition=position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
            onImageSlected(item)
        }
        if (selectedPosition == position){
            holder.binding.picListLayout.setBackgroundResource(R.drawable.brown_bg_selected)
        } else{
            holder.binding.picListLayout.setBackgroundResource(R.drawable.grey_bg)
        }


    }



    override fun getItemCount(): Int = items.size
    fun ImageView.loadImage(url: String) {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}


