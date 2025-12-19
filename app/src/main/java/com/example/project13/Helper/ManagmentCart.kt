package com.example.project13.Helper

import android.content.Context
import android.widget.Toast
import com.example.project13.Data.Database.AppDatabase
import com.example.project13.Data.Entity.CartEntity
import com.example.project13.Model.ItemsModel

class ManagmentCart(private val context: Context) {

    private val cartDao = AppDatabase.getInstance(context).cartDao()

    fun insertFood(item: ItemsModel) {
        val entity = CartEntity(
            title = item.title,
            price = item.price,
            numberInCart = item.numberInCart,
            picUrl = item.picUrl[0]
        )
        cartDao.insert(entity)
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show()
    }

    fun getListCart(): ArrayList<ItemsModel> {
        val list = cartDao.getAll()
        val result = ArrayList<ItemsModel>()

        for (e in list) {
            result.add(
                ItemsModel(
                    title = e.title,
                    price = e.price,
                    numberInCart = e.numberInCart,
                    picUrl = arrayListOf(e.picUrl)
                )
            )
        }
        return result
    }

    fun minusItem(
        listFood: ArrayList<ItemsModel>,
        position: Int,
        listener: ChangeNumberItemsListener
    ) {
        val item = listFood[position]
        if (item.numberInCart == 1) {
            listFood.removeAt(position)
            cartDao.clear()              // đơn giản cho đồ án
            listFood.forEach { insertFood(it) }
        } else {
            item.numberInCart--
            insertFood(item)
        }
        listener.onChanged()
    }

    fun plusItem(
        listFood: ArrayList<ItemsModel>,
        position: Int,
        listener: ChangeNumberItemsListener
    ) {
        val item = listFood[position]
        item.numberInCart++
        insertFood(item)
        listener.onChanged()
    }

    fun clearCart() {
        cartDao.clear()
    }

    fun getTotalFee(): Double {
        return cartDao.getAll().sumOf { it.price * it.numberInCart }
    }
}
