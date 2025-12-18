package com.example.project13.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project13.Helper.ChangeNumberItemsListener
import com.example.project13.Helper.ManagmentCart
import com.example.project13.Model.ItemsModel
import com.example.project13.databinding.ViewholderCartBinding
import kotlin.math.round

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    private val changeNumberItemsListener: ChangeNumberItemsListener
) : RecyclerView.Adapter<CartAdapter.Viewholder>() {

    class Viewholder(val binding: ViewholderCartBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val managmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val binding = ViewholderCartBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val item = listItemSelected[position]

        with(holder.binding) {
            tvTitle.text = item.title

            val priceEach = round(item.price * 100) / 100.0
            val totalEach = round((item.numberInCart * item.price) * 100) / 100.0

            feeEachTime.text = "$$priceEach"
            totalEachTime.text = "$$totalEach"
            tvCount.text = item.numberInCart.toString()

            if (item.picUrl.isNotEmpty()) {
                Glide.with(root.context)
                    .load(item.picUrl[0])
                    .into(pic)
            }

            btnPlusCart.setOnClickListener {
                managmentCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        notifyItemChanged(position)
                        changeNumberItemsListener.onChanged()
                    }
                })
            }

            btnMinusCart.setOnClickListener {
                managmentCart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                    override fun onChanged() {
                        // nếu item bị xoá khỏi list thì refresh toàn bộ cho chắc
                        notifyDataSetChanged()
                        changeNumberItemsListener.onChanged()
                    }
                })
            }
        }
    }

    override fun getItemCount(): Int = listItemSelected.size
}
