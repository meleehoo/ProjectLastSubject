package com.example.project13.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.project13.Model.SliderModel
import com.example.project13.databinding.SliderItemContainerBinding

class SliderApdapter(
    private var sliderItems : List<SliderModel>,
    private var viewPager2 : ViewPager2
): RecyclerView.Adapter<SliderApdapter.SliderViewHolder>() {
    private lateinit var context : Context

    class SliderViewHolder (private val binding : SliderItemContainerBinding):
        RecyclerView.ViewHolder(binding.root){
        fun setImage(sliderItem : SliderModel,context  : Context) {
            Glide.with(context)
                .load(sliderItem.url)
                .apply { RequestOptions().transform(CenterInside()) }
                .into(binding.imageSlide)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderApdapter.SliderViewHolder {
        context = parent.context
        val binding = SliderItemContainerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderApdapter.SliderViewHolder, position: Int) {
       holder.setImage(sliderItems[position], context)

    }

    override fun getItemCount(): Int = sliderItems.size
}