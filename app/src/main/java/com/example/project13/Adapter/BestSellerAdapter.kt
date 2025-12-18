package com.example.project13.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.example.project13.Activity.DetailActivity
import com.example.project13.Model.ItemsModel
import com.example.project13.databinding.ViewholderBestSellerBinding

class BestSellerAdapter(val items: MutableList<ItemsModel>): RecyclerView.Adapter<BestSellerAdapter.Viewholder>() {
    class Viewholder(val binding: ViewholderBestSellerBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BestSellerAdapter.Viewholder {
        val binding =
            ViewholderBestSellerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            tvTiltle.text = item.title
            tvPrice.text = "$${item.price}"
            tvRating.text = item.rating.toString()


                Glide.with(holder.itemView.context)

                    .load(item.picUrl[0])
                    .into(PicBestseller)
            root.setOnClickListener {
                val intent = Intent(holder.itemView.context, DetailActivity::class.java).apply {
                    putExtra("object",item)
                }
                ContextCompat.startActivity(holder.itemView.context,intent,null)
            }

        }
    }


    override fun getItemCount() = items.size

}